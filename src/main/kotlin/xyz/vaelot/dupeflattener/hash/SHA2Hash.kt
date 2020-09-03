package xyz.vaelot.dupeflattener.hash

import org.apache.commons.codec.digest.DigestUtils

class SHA2Hash : Hash() {
    var sha2 = DigestUtils.getSha256Digest()
    override fun flush() { sha2 = DigestUtils.getSha256Digest() }
    override fun update(input: ByteArray) { sha2.update(input) }
    override fun binDigest(): ByteArray { return sha2.digest() }
}