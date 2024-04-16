module com.cthiebaud.javafx {
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive javafx.web;
    requires thymeleaf;
    requires jdk.jsobject;

    opens com.cthiebaud.javafx to javafx.web;

    exports com.cthiebaud.javafx;
}
