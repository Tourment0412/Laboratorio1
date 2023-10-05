open module co.uniquindio.programacionIII.alquilafacil {
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.controls;
	requires lombok;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires org.apache.pdfbox;

	requires transitive javafx.graphics;
	requires transitive java.persistence;

	exports co.edu.uniquindio.programacionIII.alquilafacil.aplication;
	exports co.edu.uniquindio.programacionIII.alquilafacil.services;
	exports co.edu.uniquindio.programacionIII.alquilafacil.model;
}
