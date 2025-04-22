module universite_paris8.iut.fguerreiromarques.loupmouton24 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;

    opens universite_paris8.iut.fguerreiromarques.loupmouton24 to javafx.fxml;
    exports universite_paris8.iut.fguerreiromarques.loupmouton24;
}