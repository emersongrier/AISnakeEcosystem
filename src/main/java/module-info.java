module com.example.gamepractice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamepractice to javafx.fxml;
    exports com.example.gamepractice;
}