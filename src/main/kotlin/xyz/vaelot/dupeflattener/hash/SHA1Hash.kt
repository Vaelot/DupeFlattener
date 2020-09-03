package xyz.vaelot.dupeflattener.hash

import org.apache.commons.codec.digest.DigestUtils

class SHA1Hash : Hash() {
    var sha1 = DigestUtils.getSha1Digest()
    override fun flush() { sha1 = DigestUtils.getSha1Digest() }
    override fun update(input: ByteArray) { sha1.update(input) }
    override fun binDigest(): ByteArray { return sha1.digest() }
}