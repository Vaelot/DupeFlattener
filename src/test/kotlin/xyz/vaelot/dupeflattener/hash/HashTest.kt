package xyz.vaelot.dupeflattener.hash

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HashTest {
    val b = Blake2bHash()
    val c = CRC32Hash()
    val m = MD5Hash()
    val s1 = SHA1Hash()
    val s2 = SHA2Hash()

    @Test
    fun `Blake2bHash work properly`() {
        Assertions.assertEquals(
            "786a02f742015903c6c6fd852552d272912f4740e15847618a86e217f71f5419d25e1031afee585313896444934eb04b903a685b1448b755d56f701afe9be2ce",
            b.hexDigest()
        )
        b.flush()
        b.update(byteArrayOf(116,101,115,116)) // 'test'
        Assertions.assertEquals(
            "a71079d42853dea26e453004338670a53814b78137ffbed07603a41d76a483aa9bc33b582f77d30a65e6f29a896c0411f38312e1d66e0bf16386c86a89bea572",
            b.hexDigest()
        )
        b.flush()
    }

    @Test
    fun `CRC32Hash work properly`() {
        Assertions.assertEquals(
            "00000000",
            c.hexDigest()
        )
        c.flush()
        c.update(byteArrayOf(116,101,115,116)) // 'test'
        Assertions.assertEquals(
            "d87f7e0c",
            c.hexDigest()
        )
        c.flush()
    }
    @Test
    fun `MD5Hash work properly`() {
        Assertions.assertEquals(
            "d41d8cd98f00b204e9800998ecf8427e",
            m.hexDigest()
        )
        m.flush()
        m.update(byteArrayOf(116,101,115,116)) // 'test'
        Assertions.assertEquals(
            "098f6bcd4621d373cade4e832627b4f6",
            m.hexDigest()
        )
        m.flush()
    }
    @Test
    fun `SHA1Hash work properly`() {
        Assertions.assertEquals(
            "da39a3ee5e6b4b0d3255bfef95601890afd80709",
            s1.hexDigest()
        )
        s1.flush()
        s1.update(byteArrayOf(116,101,115,116)) // 'test'
        Assertions.assertEquals(
            "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3",
            s1.hexDigest()
        )
        s1.flush()
    }
    @Test
    fun `SHA2Hash work properly`() {
        Assertions.assertEquals(
            "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            s2.hexDigest()
        )
        s2.flush()
        s2.update(byteArrayOf(116,101,115,116)) // 'test'
        Assertions.assertEquals(
            "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
            s2.hexDigest()
        )
        s2.flush()
    }
}