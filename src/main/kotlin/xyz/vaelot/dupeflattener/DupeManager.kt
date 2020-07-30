package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.def.Struct
import xyz.vaelot.dupeflattener.def.Worker
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object DupeManager {
    private val ioTask: () -> Unit = fun() {
        try {
        } catch (e: InterruptedException) {
        }
    }
    private val computeTask: () -> Unit = fun() {
        try {
        } catch (e: InterruptedException) {
        }
    }
    private val uiWorker: Thread? = null

    fun start(st: Struct) {
        ioWorker = Worker()
        ioWorker!!.submit(ioTask)

        computeWorker = Executors.newFixedThreadPool(computeNum)
        computeWorker!!.submit(computeTask)

        Report.initialize(st)
    }
    fun suspend() {
        ioWorker

    }
    fun stop() {
        ioWorker = null
        computeWorker = null
        Report.terminate()
    }
}