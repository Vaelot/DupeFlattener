package xyz.vaelot.dupeflattener.view

import javafx.scene.layout.BorderPane

import tornadofx.*

class MainView: View() {
    override val root : BorderPane by fxml("/fxml/MainView.fxml")
}