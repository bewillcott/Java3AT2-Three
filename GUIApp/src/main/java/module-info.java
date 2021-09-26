module GUI_Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires CommonLibrary;

    opens com.bewsoftware.tafe.java3.at2.three.gui to javafx.fxml;
    exports com.bewsoftware.tafe.java3.at2.three.gui;
}
