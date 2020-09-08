package xyz.vaelot.dupeflattener.hash

import java.util.*

abstract class Hash: IHash {
    final override fun hexDigest(): String {
        return binDigest().joinToString("") { "%02x".format(it) }
    }
    final override fun base64Digest(): String {
        return Base64.getEncoder().encodeToString(binDigest())
    }
}