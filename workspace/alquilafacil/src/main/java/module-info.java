module co.uniquindio.programacionIII.alquilafacil {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.uniquindio.programacionIII.alquilafacil.aplication to javafx.fxml;
    exports co.uniquindio.programacionIII.alquilafacil.aplication;
}
