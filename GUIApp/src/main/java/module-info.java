
module GUI_Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires CommonLibrary;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.base;

    opens com.bewsoftware.tafe.java3.at2.three.gui to javafx.graphics;
    opens com.bewsoftware.tafe.java3.at2.three.gui.view to javafx.fxml, javafx.graphics;

}
