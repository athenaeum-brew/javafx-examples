module com.cthiebaud.javafx {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires transitive com.cthiebaud.mythos;
    requires org.apache.httpcomponents.httpcore;
    requires transitive org.apache.httpcomponents.httpclient;
    requires javafx.base;

    opens com.cthiebaud.javafx to javafx.graphics;

    exports com.cthiebaud.javafx;
}
