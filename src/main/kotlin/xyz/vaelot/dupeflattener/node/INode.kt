package xyz.vaelot.dupeflattener.node

import java.io.File

interface INode: Comparable<File> {
    val path: MutableList<String>
    val first: File
    val size: Long
    val hash: ByteArray

    fun add(p: File)
}