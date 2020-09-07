package xyz.vaelot.dupeflattener.hash

class SumHash : Hash() {
    var sum: Int = 0
    override fun flush() { sum = 0 }
    override fun update(input: ByteArray) {
        input.forEach { sum += it.toInt() }
    }
    override fun binDigest(): ByteArray {
        return ByteArray(sum)

    }
}