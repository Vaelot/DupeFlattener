package xyz.vaelot.dupeflattener.scan

import java.io.File

class ArrayListScan(override val root: File) : IScan, ArrayList<File>() {
    init {
        require (root.isDirectory) { "$root does not exists." }
        root.walk(FileWalkDirection.TOP_DOWN)
            .filter { it.canRead() && it.isFile }
            .forEach { this.add(it) }
    }
}