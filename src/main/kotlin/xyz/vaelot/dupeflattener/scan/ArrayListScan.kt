package xyz.vaelot.dupeflattener.scan

import java.io.File

class ArrayListScan(override val root: File) : IScan {
    init {
        require (root.isDirectory) { "$root does not exists." }
    }
    override fun start(): ArrayList<File> {
        return root.walk(FileWalkDirection.TOP_DOWN)
            .filter { it.canRead() && it.isFile }
            .toList() as ArrayList<File>
    }
}