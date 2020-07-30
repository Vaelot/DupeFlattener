package xyz.vaelot.dupeflattener.fragment

import javafx.scene.layout.AnchorPane
import tornadofx.Fragment

class OverviewTabFragment: Fragment("Overview")  {
    override val root : AnchorPane by fxml("/fxml/tab/OverviewTabFragment.fxml")
}