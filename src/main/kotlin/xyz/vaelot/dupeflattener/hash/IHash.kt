package xyz.vaelot.dupeflattener.hash

interface IHash {
    fun flush()
    fun update(input: ByteArray)
    fun binDigest(): ByteArray
    fun hexDigest(): String
    fun base64Digest(): String
}