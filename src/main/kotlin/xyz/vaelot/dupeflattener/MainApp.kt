package xyz.vaelot.dupeflattener

import tornadofx.App
import tornadofx.launch

import xyz.vaelot.dupeflattener.view.MainView

class MainApp: App(MainView::class)

fun main(args: Array<String>) {
    launch<MainApp>(args)
}
