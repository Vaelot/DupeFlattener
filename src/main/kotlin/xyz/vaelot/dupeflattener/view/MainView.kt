package xyz.vaelot.dupeflattener.view

import javafx.scene.layout.BorderPane

import tornadofx.*
import xyz.vaelot.dupeflattener.fragment.*

class MainView: View() {
    override val root : BorderPane by fxml("/fxml/MainView.fxml")

    init {
        root.center?.add<SettingsTabFragment>()
        root.center?.add<OverviewTabFragment>()
        root.center?.add<DuplicatesTabFragment>()
        root.center?.add<DataTableTabFragment>()
        root.center?.add<JobDetailTabFragment>()
    }
}