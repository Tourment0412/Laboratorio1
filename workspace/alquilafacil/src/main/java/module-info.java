module co.uniquindio.programacionIII.alquilafacil {
	requires javafx.fxml;
	requires javafx.graphics;
	requires lombok;

	opens co.edu.uniquindio.programacionIII.alquilafacil.aplication to javafx.fxml;

	exports co.edu.uniquindio.programacionIII.alquilafacil.aplication;
}
