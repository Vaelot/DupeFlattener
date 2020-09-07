package xyz.vaelot.dupeflattener.hash

import java.io.File

interface IHash {
    fun hashFromFile(f: File): ByteArray
    fun flush()
    fun update(input: ByteArray)
    fun binDigest(): ByteArray
    fun hexDigest(): String
    fun base64Digest(): String
}