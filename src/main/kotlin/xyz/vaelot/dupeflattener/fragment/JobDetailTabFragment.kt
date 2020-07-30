package xyz.vaelot.dupeflattener.fragment

import javafx.scene.layout.AnchorPane
import tornadofx.Fragment

class JobDetailTabFragment: Fragment("Job Details")  {
    override val root : AnchorPane by fxml("/fxml/tab/JobDetailTabFragment.fxml")
}