package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.Caller;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.SimpleValueProvider;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.TestCallClass;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.ValueProviderException;

import java.lang.reflect.InvocationTargetException;

public class Lab2Controller {
    public TextArea textOut;
    public Button btnCall;
    public Button btnClear;

    public void btnClear_onMouseClicked(MouseEvent mouseEvent) {
        textOut.setText("");
    }

    public void btnCall_onMouseClicked(MouseEvent mouseEvent) {
        var valueProvider = new SimpleValueProvider();
        valueProvider.addValue(12);
        valueProvider.addValue("Hello");

        var caller = new Caller(valueProvider);

        textOut.setText("");

        try {
            var txt = caller.executeCalls(new TestCallClass());
            textOut.setText(txt);
        } catch (InvocationTargetException | IllegalAccessException | ValueProviderException e) {
            var err = String.format("Error has occurred while trying to exec caller.ExecuteCalls.%n%s", e);
            textOut.setText(err);
        }
    }
}
