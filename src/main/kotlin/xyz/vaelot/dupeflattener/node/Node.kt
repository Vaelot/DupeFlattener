package xyz.vaelot.dupeflattener.node

import xyz.vaelot.dupeflattener.Manager
import java.io.File

class Node(p: String) : INode {
    override val path: MutableList<String>
    override val firstFile: File = File(p)
    override val size: Long
        get () = firstFile.length()
    override val hash: ByteArray by lazy {
        Manager.hashFromFile(firstFile)
    }

    init {
        require (firstFile.isFile) { "File $p not exists." }
        path = arrayListOf(p)
    }

    override fun add(p: String) {
        require (File(p).isFile) { "File $p not exists." }
        path.add(p)
    }

    override fun compareTo(other: Any?): Int {
        val otherFile = when (other) {
            is String -> File(other)
            is File -> other
            is Node -> other.firstFile
            else -> throw IllegalArgumentException("Argument is not File convertible.")
        }
        require (otherFile.isFile) { "File $other not exists." }

        // first, check size

        val otherFileLength = otherFile.length()
        if (size != otherFileLength) return size.compareTo(otherFileLength)

        // no check hash, because hash is useless for byte comparison.

        firstFile.inputStream()
            .buffered(4096)
            .use { tit ->
            otherFile.inputStream()
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
            is Node -> other.firstFile
            else -> throw IllegalArgumentException("Argument is not File convertible.")
        }

        require (otherFile.isFile) { "File $otherFile not exists." }

        // first, check size
        if (size != otherFile.length()) return false

        // second, check hash
        val otherFileHash = when (other) {
            is Node -> other.hash
            else -> Manager.hashFromFile(otherFile)
        }
        if (!hash.contentEquals(otherFileHash)) return false

        // last, real-data check
        firstFile.inputStream()
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
