package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.hash.*
import xyz.vaelot.dupeflattener.ds.*
import xyz.vaelot.dupeflattener.node.*
import xyz.vaelot.dupeflattener.scan.*
import java.io.File
import java.io.FileInputStream
import java.io.Serializable
import java.nio.ByteBuffer
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object Manager : Serializable {
    @JvmStatic private val serialVersionUID: Long = 1L

    var ds: IDS = HashTableDS()
    @Transient var scanClass: KClass<out IScan> = ArrayListScan::class
    @Transient var nodeClass: KClass<out INode> = Node::class
    @Transient var hashClass: KClass<out IHash> = CRC32Hash::class

    fun hashFromFile(f: File): ByteArray {
        val h = hashClass.createInstance()
        val fc = FileInputStream(f).channel
        val buf = ByteBuffer.allocateDirect(16384)

        h.flush()

        while (true) {
            if (!buf.hasRemaining()) {
                val cl = fc.read(buf)
                if (cl < 0) break
            }
            val tmp = ByteArray(1024)
            buf.get(tmp)
            h.update(tmp)
        }

        fc.close()
        h.flush()

        return h.binDigest()
    }
}