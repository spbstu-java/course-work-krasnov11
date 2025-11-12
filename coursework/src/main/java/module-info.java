module ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires annotations;

    opens ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11 to javafx.fxml;
    exports ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11;
    exports ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;
    opens ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers to javafx.fxml;
}