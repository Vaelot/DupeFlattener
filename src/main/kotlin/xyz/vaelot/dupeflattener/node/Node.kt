package xyz.vaelot.dupeflattener.node

import xyz.vaelot.dupeflattener.Manager
import java.io.File

class Node(override val first: File) : INode {
    override val path: MutableList<String>
    override val size: Long by lazy { first.length() }
    override val hash: ByteArray by lazy { Manager.hashFromFile(first) }

    init {
        require (first.isFile) { "File ${first.path} not exists." }
        path = arrayListOf(first.path)
    }

    override fun add(p: File) {
        require (p.isFile) { "File $p not exists." }
        path.add(p.path)
    }

    @Deprecated("compareTo with String is not recommended.",
        ReplaceWith("compareTo(File(that))", "java.io.File"))
    fun compareTo(that: String): Int = compareTo(File(that))

    override fun compareTo(other: File): Int {
        require (other.isFile) { "File $other not exists." }

        // first, check size

        val thatSize = other.length()
        if (size != thatSize) return size.compareTo(thatSize)

        // no check hash, because hash is useless for byte comparison.

        first.inputStream()
            .buffered(4096)
            .use { tit ->
            other.inputStream()
                .buffered(4096)
                .use { oit ->
                    while (true) {
                        val t = tit.read()
                        val o = oit.read()
                        if (t < 0) return if (o < 0) 0 else -1
                        if (o < 0) return 1
                        if (t != o) return (t.toByte()).compareTo(o.toByte())
                    }
                }
            }
    }

    override fun equals(other: Any?): Boolean {
        val otherFile = when (other) {
            is String -> File(other)
            is File -> other
            else -> return false
        }
        require (otherFile.isFile) { "File $otherFile not exists." }

        // first, check size
        if (size != otherFile.length()) return false

        // second, check hash
        val thatHash = Manager.hashFromFile(otherFile)
        if (!hash.contentEquals(thatHash)) return false

        // last, real-data check
        first.inputStream()
            .buffered(4096)
            .use { tit ->
                otherFile.inputStream()
                    .buffered(4096)
                    .use { oit ->
                        while (true) {
                            val t = tit.read()
                            val o = oit.read()
                            if (t < 0) return o < 0
                            if (o < 0) return false
                            if (t != o) return false
                        }
                    }
            }
    }

    override fun hashCode(): Int { return hash.sum() }
}
