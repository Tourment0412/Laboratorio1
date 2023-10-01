open module co.uniquindio.programacionIII.alquilafacil {
	requires javafx.fxml;
	requires javafx.swing;
	requires transitive javafx.graphics;
	requires javafx.controls;

	requires java.persistence;
	requires lombok;
	requires java.sql;

	exports co.edu.uniquindio.programacionIII.alquilafacil.aplication;
}
