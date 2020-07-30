package xyz.vaelot.dupeflattener.fragment

import javafx.scene.layout.AnchorPane
import tornadofx.Fragment

class DuplicatesTabFragment: Fragment("Duplicates")  {
    override val root : AnchorPane by fxml("/fxml/tab/DuplicatesTabFragment.fxml")
}