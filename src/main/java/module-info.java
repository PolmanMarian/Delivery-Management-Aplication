module com.example.pt_2022_30222_polman_marian_assignment_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens BusinessLogicLayer to javafx.fxml;
    exports BusinessLogicLayer;

    opens PresentationLayer to javafx.fxml;
    exports PresentationLayer;

    opens com.example.pt_2022_30222_polman_marian_assignment_4 to javafx.fxml;
    exports com.example.pt_2022_30222_polman_marian_assignment_4;
}