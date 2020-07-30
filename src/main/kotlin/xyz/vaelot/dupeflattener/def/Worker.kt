package xyz.vaelot.dupeflattener.def

class Worker: Runnable {
    companion object {
        private var es: ArrayList<Thread>? = null
        private var num: Int = 1
        private var status: WorkerStatus = WorkerStatus.JOIN
    }

    fun start() {
        es = arrayListOf()
        for (_ in 1..num) es!!.add(Thread(this))
        es!!.forEach { it.start() }
        synchronized(status) { status = WorkerStatus.START }
    }
    fun resume() {
        synchronized(status) { status = WorkerStatus.START }
    }
    fun suspend() {
        synchronized(status) { status = WorkerStatus.SUSPEND }
    }
    fun stop() {
        synchronized(status) { status = WorkerStatus.STOP }
    }
    fun join() {
        es!!.forEach { it.join() }
        es = null
        synchronized(status) { status = WorkerStatus.JOIN }
    }

    override fun run() {
        try {

        } catch (e: InterruptedException) {
        }
    }
}