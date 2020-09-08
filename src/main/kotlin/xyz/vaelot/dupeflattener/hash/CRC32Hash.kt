package xyz.vaelot.dupeflattener.hash

import java.security.MessageDigest
import java.util.zip.CRC32

class CRC32Hash : Hash() {
    class CRC32Digest: MessageDigest("CRC") {
        private var crc = CRC32()
        override fun engineReset() { crc.reset() }
        override fun engineUpdate(input: Byte) { crc.update(byteArrayOf(input)) }
        override fun engineUpdate(input: ByteArray?, offset: Int, len: Int) { crc.update(input, offset, len) }
        override fun engineDigest(): ByteArray {
            val l = crc.value
            return byteArrayOf(
                l.and(0xFF000000).shr(24).toByte(),
                l.and(0x00FF0000).shr(16).toByte(),
                l.and(0x0000FF00).shr(8).toByte(),
                l.and(0x000000FF).toByte()
            )
        }
    }
    private var crc32 = CRC32Digest()
    override fun flush() {
        crc32 = CRC32Digest()
    }
    override fun update(input: ByteArray) { crc32.update(input) }
    override fun binDigest(): ByteArray { return crc32.digest() }
}