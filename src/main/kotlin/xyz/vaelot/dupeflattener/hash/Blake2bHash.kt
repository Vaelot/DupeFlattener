package xyz.vaelot.dupeflattener.hash

import org.kocakosm.jblake2.Blake2b

class Blake2bHash : IHash, Hash() {
    private var b2 = Blake2b(64)
    override fun flush() { b2 = Blake2b(64) }
    override fun update(input: ByteArray) { b2.update(*input) }
    override fun binDigest(): ByteArray { return b2.digest() }
}