module com.mycompany.lab3program2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core; // si usás Hibernate como proveedor

    opens com.mycompany.lab3program2 to javafx.fxml, org.hibernate.orm.core;
    exports com.mycompany.lab3program2;
}
