package xyz.vaelot.dupeflattener.hash

interface IHash {
    // fun hashFromFile(f: File): ByteArray
    fun flush()
    fun update(input: ByteArray)
    fun binDigest(): ByteArray
    fun hexDigest(): String
    fun base64Digest(): String
}