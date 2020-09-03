package xyz.vaelot.dupeflattener

import org.apache.commons.cli.Options
import org.apache.commons.cli.Option

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val opt = Options()
        val predefined = arrayOf(
            Option("m", "mode", true,
                "Select mode for operation."),
            Option("s", "size", true,
                "Set size limit of job pending queue."),
            Option("t", "threads", true,
                "Set thread number of thread pool."),
            Option("l", "link", true,
                "Select options for found duplicated files.")
        )
        predefined.forEach { opt.addOption(it) }
    }
}