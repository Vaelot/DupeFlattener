package xyz.vaelot.dupeflattener

import xyz.vaelot.dupeflattener.def.Job
import xyz.vaelot.dupeflattener.def.Struct

import java.io.Serializable
import java.util.concurrent.LinkedBlockingQueue

object Report : Serializable {
    private var fileSearchQueue: LinkedBlockingQueue<Job>? = null
    private var comparisonQueue: LinkedBlockingQueue<Job>? = null
    private var qSize: Int = 10
        set(value) {
            field = value
            dataStruct?.let { initialize(it) }
        }

    private var dataStruct: Struct? = null

    fun initialize(st: Struct) {
        fileSearchQueue = LinkedBlockingQueue(qSize)
        comparisonQueue = LinkedBlockingQueue(qSize)
        dataStruct = st
    }

    fun terminate() {
        fileSearchQueue = null
        comparisonQueue = null
        dataStruct = null
    }
}