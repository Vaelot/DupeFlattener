package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.ds.HashTableDS
import xyz.vaelot.dupeflattener.ds.IDS
import xyz.vaelot.dupeflattener.hash.CRC32Hash
import xyz.vaelot.dupeflattener.hash.IHash
import xyz.vaelot.dupeflattener.node.INode
import xyz.vaelot.dupeflattener.node.Node
import xyz.vaelot.dupeflattener.scan.ArrayListScan
import xyz.vaelot.dupeflattener.scan.IScan
import java.io.File
import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object Manager : Serializable {
    @JvmStatic private val serialVersionUID: Long = 1L

    var ds: IDS = HashTableDS()
    @Transient var scanClass: KClass<out IScan> = ArrayListScan::class
    @Transient var nodeClass: KClass<out INode> = Node::class
    @Transient var hashClass: KClass<out IHash> = CRC32Hash::class

    fun hashFromFile(d: Any): ByteArray {
        val h = hashClass.createInstance()
        h.flush()
        when (d) {
            is File -> d
            is String -> File(d)
            else -> throw IllegalArgumentException()
        }.inputStream()
            .buffered(4096)
            .use {
                for (buf in it) h.update(ByteArray(0) + buf)
            }

        return h.binDigest()
    }
}