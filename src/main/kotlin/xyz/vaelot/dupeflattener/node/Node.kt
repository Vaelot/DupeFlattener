package xyz.vaelot.dupeflattener.node

import xyz.vaelot.dupeflattener.Manager
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import kotlin.reflect.full.createInstance

class Node(p: String) : INode {
    override lateinit var path: MutableList<String>
    override lateinit var first: File
    override val size: Long by lazy { first.length() }
    override val hash: ByteArray by lazy { Manager.hashClass.createInstance().hashFromFile(first) }

    init {
        val first = File(p)
        require (first.isFile) { "File $p not exists." }
        path = arrayListOf(p)
    }

    override fun add(p: String) {
        require (File(p).isFile) { "File $p not exists." }
        path.add(p)
    }

    fun compareTo(p: String): Int { return compareTo(File(p)) }
    fun compareTo(f: File): Int {
        require (f.isFile) { "File $f not exists." }

        // first, check size

        val fl = f.length()

        if (size < fl) return -1
        if (size > fl) return 1

        // size is same

        val tc = FileInputStream(first).channel
        val oc = FileInputStream(f).channel
        val tb = ByteBuffer.allocateDirect(16384)
        val ob = ByteBuffer.allocateDirect(16384)

        while (true) {
            if (!tb.hasRemaining()) {
                val tl = tc.read(tb)
                val ol = oc.read(ob)
                if (tl > ol) return closeAndReturn(tc, oc, 1)
                if (tl < ol) return closeAndReturn(tc, oc, -1)
                if (tl == -1) return closeAndReturn(tc, oc, 0)
            }
            val res = tb.compareTo(ob)
            if (res != 0) return closeAndReturn(tc, oc, res)
        }
    }

    private fun closeAndReturn(a: FileChannel, b: FileChannel, ret: Int): Int {
        a.close()
        b.close()
        return ret
    }

    fun isIdentical(p: String): Boolean { return isIdentical(File(p)) }
    fun isIdentical(f: File): Boolean {
        require (f.isFile) { "File $f not exists." }

        // first, check size
        if (size != f.length()) return false
        // second, check hash
        val fHash = Manager.hashClass.createInstance().hashFromFile(f)
        if (!hash.contentEquals(fHash)) return false

        // last, real-data check

        val tc = FileInputStream(first).channel
        val oc = FileInputStream(f).channel
        val tb = ByteBuffer.allocateDirect(16384)
        val ob = ByteBuffer.allocateDirect(16384)

        while (true) {
            if (!tb.hasRemaining()) {
                val tl = tc.read(tb)
                val ol = oc.read(ob)
                if (tl != ol) return false
                if (tl == -1) return true
            }
            if (tb.compareTo(ob) != 0) return false
        }
    }
}
