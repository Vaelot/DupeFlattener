package xyz.vaelot.dupeflattener.view

import tornadofx.*

class MainView: View() {
    override val root = vbox {
        button("Press me")
        label("Waiting")
    }
}