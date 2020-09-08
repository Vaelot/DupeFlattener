package xyz.vaelot.dupeflattener.node

import xyz.vaelot.dupeflattener.Manager
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

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

        val thisFileChannel = FileInputStream(firstFile).channel
        val otherFileChannel = FileInputStream(otherFile).channel
        val thisFileBuffer = ByteBuffer.allocateDirect(16384)
        val otherFileBuffer = ByteBuffer.allocateDirect(16384)

        while (true) {
            if (!thisFileBuffer.hasRemaining()) {
                val thisFileReadSize = thisFileChannel.read(thisFileBuffer)
                val otherFileReadSize = otherFileChannel.read(otherFileBuffer)
                if (thisFileReadSize != otherFileReadSize) return closeAndReturn(
                    thisFileChannel,
                    otherFileChannel,
                    thisFileReadSize.compareTo(otherFileReadSize)
                )
                if (thisFileReadSize == -1) return closeAndReturn(thisFileChannel, otherFileChannel, 0)
            }
            val compareToResult = thisFileBuffer.compareTo(otherFileBuffer)
            if (compareToResult != 0) return closeAndReturn(thisFileChannel, otherFileChannel, compareToResult)
        }
    }

    private fun closeAndReturn(a: FileChannel, b: FileChannel, ret: Int): Int {
        a.close()
        b.close()
        return ret
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
        val thisFileChannel = FileInputStream(firstFile).channel
        val otherFileChannel = FileInputStream(otherFile).channel
        val thisFileBuffer = ByteBuffer.allocateDirect(16384)
        val otherFileBuffer = ByteBuffer.allocateDirect(16384)

        while (true) {
            if (!thisFileBuffer.hasRemaining()) {
                val thisFileReadSize = thisFileChannel.read(thisFileBuffer)
                val otherFileReadSize = otherFileChannel.read(otherFileBuffer)
                if (thisFileReadSize != otherFileReadSize) return false
                if (thisFileReadSize == -1) return true
            }
            if (thisFileBuffer.compareTo(otherFileBuffer) != 0) return false
        }
    }

    override fun hashCode(): Int { return hash.sum() }
}
