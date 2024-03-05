module com.example.bishal_amgai_lab2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.bishal_amgai_lab2 to javafx.fxml;
    exports com.example.bishal_amgai_lab2;
}