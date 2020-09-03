package xyz.vaelot.dupeflattener.hash

import org.apache.commons.codec.digest.DigestUtils

class MD5Hash : Hash() {
    var md5 = DigestUtils.getMd5Digest()
    override fun flush() { md5 = DigestUtils.getMd5Digest() }
    override fun update(input: ByteArray) { md5.update(input) }
    override fun binDigest(): ByteArray { return md5.digest() }
}