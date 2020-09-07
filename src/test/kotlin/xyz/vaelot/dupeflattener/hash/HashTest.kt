package xyz.vaelot.dupeflattener.hash

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class HashTest {
    companion object {
        @JvmStatic
        fun HashEmptyProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Blake2bHash(), "786a02f742015903c6c6fd852552d272912f4740e15847618a86e217f71f5419d25e1031afee585313896444934eb04b903a685b1448b755d56f701afe9be2ce"),
                Arguments.of(CRC32Hash(), "00000000"),
                Arguments.of(MD5Hash(), "d41d8cd98f00b204e9800998ecf8427e"),
                Arguments.of(SHA1Hash(), "da39a3ee5e6b4b0d3255bfef95601890afd80709"),
                Arguments.of(SHA2Hash(), "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"),
                Arguments.of(SumHash(), "00000000")
            )
        }
        @JvmStatic
        fun HashTestProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Blake2bHash(), "a71079d42853dea26e453004338670a53814b78137ffbed07603a41d76a483aa9bc33b582f77d30a65e6f29a896c0411f38312e1d66e0bf16386c86a89bea572"),
                Arguments.of(CRC32Hash(), "d87f7e0c"),
                Arguments.of(MD5Hash(), "098f6bcd4621d373cade4e832627b4f6"),
                Arguments.of(SHA1Hash(), "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3"),
                Arguments.of(SHA2Hash(), "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"),
                Arguments.of(SumHash(), "00000000")
            )
        }
    }

    @ParameterizedTest
    @DisplayName("Hash Group Test with Empty String")
    @MethodSource("HashEmptyProvider")
    fun testWithEmpty(it: Hash, expected: String) {
        Assertions.assertEquals(expected, it.hexDigest())
        it.flush()
    }

    @ParameterizedTest
    @DisplayName("Hash Group Test with `test` string")
    @MethodSource("HashTestProvider")
    fun testWithTest(it: Hash, expected: String) {
        it.update(byteArrayOf(116,101,115,116)) // test
        Assertions.assertEquals(expected, it.hexDigest())
        it.flush()
    }
}