package xyz.vaelot.dupeflattener.hash

import java.io.File
import java.io.FileInputStream
import java.nio.channels.FileChannel
import java.nio.ByteBuffer
import java.util.*

abstract class Hash: IHash {
    final override fun hashFromFile(f: File): ByteArray {
        val fc = FileInputStream(f).channel
        val buf = ByteBuffer.allocateDirect(16384)

        flush()

        while (true) {
            if (!buf.hasRemaining()) {
                val cl = fc.read(buf)
                if (cl < 0) break
            }
            val tmp = ByteArray(1024)
            buf.get(tmp)
            update(tmp)
        }

        fc.close()
        flush()

        return binDigest()
    }
    final override fun hexDigest(): String {
        return binDigest().joinToString("") { "%02x".format(it) }
    }
    final override fun base64Digest(): String {
        return Base64.getEncoder().encodeToString(binDigest())
    }
}