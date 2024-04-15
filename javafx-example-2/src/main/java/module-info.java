module com.cthiebaud.javafx {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    opens com.cthiebaud.javafx to javafx.fxml;

    exports com.cthiebaud.javafx;
}
