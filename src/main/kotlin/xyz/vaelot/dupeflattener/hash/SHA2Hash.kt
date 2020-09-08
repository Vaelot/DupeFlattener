package xyz.vaelot.dupeflattener.hash

import org.apache.commons.codec.digest.DigestUtils
import java.security.MessageDigest

class SHA2Hash : Hash() {
    private var sha2: MessageDigest = DigestUtils.getSha256Digest()
    override fun flush() { sha2 = DigestUtils.getSha256Digest() }
    override fun update(input: ByteArray) { sha2.update(input) }
    override fun binDigest(): ByteArray { return sha2.digest() }
}