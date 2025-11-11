package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.helpers.StdoutRedirectExecuter;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.Caller;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.SimpleValueProvider;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.TestCallClass;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2.ValueProviderException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Lab2Controller {
    public TextArea textOut;
    public Button btnCall;
    public Button btnClear;
    private final StdoutRedirectExecuter executer;

    public Lab2Controller(){
        executer = new StdoutRedirectExecuter((str) -> textOut.appendText(str));
    }

    public void btnClear_onMouseClicked(MouseEvent mouseEvent) {
        textOut.setText("");
    }

    public void btnCall_onMouseClicked(MouseEvent mouseEvent) {

        try {
            executer.execute(() -> {
                var valueProvider = new SimpleValueProvider();
                valueProvider.addValue(12);
                valueProvider.addValue("Hello");

                var caller = new Caller(valueProvider);

                textOut.clear();

                try {
                    caller.executeCalls(new TestCallClass());
                } catch (InvocationTargetException | IllegalAccessException | ValueProviderException e) {
                    var err = String.format("Error has occurred while trying to exec caller.ExecuteCalls.%n%s", e);
                    textOut.setText(err);
                }
            });
        } catch (IOException e) {
            textOut.appendText("Error has occurred while executing: " + e);
        }
    }
}
