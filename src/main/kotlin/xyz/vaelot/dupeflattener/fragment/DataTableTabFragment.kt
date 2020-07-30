package xyz.vaelot.dupeflattener.fragment

import javafx.scene.layout.AnchorPane
import tornadofx.Fragment

class DataTableTabFragment: Fragment("Data Table")  {
    override val root : AnchorPane by fxml("/fxml/tab/DataTableTabFragment.fxml")
}