package xyz.vaelot.dupeflattener.hash

import org.apache.commons.codec.digest.DigestUtils
import java.security.MessageDigest

class SHA1Hash : Hash() {
    private var sha1: MessageDigest = DigestUtils.getSha1Digest()
    override fun flush() { sha1 = DigestUtils.getSha1Digest() }
    override fun update(input: ByteArray) { sha1.update(input) }
    override fun binDigest(): ByteArray { return sha1.digest() }
}