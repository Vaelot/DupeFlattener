package xyz.vaelot.dupeflattener

import org.apache.commons.cli.Option
import org.apache.commons.cli.Options

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val opt = Options()
        val predefined = arrayOf(
            Option("m", "mode", true,
                "Select mode for operation."),
            Option("t", "threads", true,
                "Set thread number of thread pool."),
            Option("l", "link", true,
                "Select options for found duplicated files."),
            Option("b", "barrier", false,
                "Wait for each stage complete."),
            Option("b", "barrier", false,
                "Wait for each stage complete.")
        )
        predefined.forEach { opt.addOption(it) }
    }
}