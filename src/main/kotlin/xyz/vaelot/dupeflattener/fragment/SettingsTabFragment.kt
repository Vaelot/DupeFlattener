package xyz.vaelot.dupeflattener.fragment

import javafx.scene.control.SplitPane
import tornadofx.Fragment

class SettingsTabFragment: Fragment("Target Directories / Settings") {
    override val root : SplitPane by fxml("/fxml/tab/SettingsTabFragment.fxml")
}