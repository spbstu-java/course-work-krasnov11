package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.helpers.MessageHelper;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab3.Lab3DuplicateException;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab3.Lab3FormatException;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab3.WordDictionary;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

public class Lab3Controller {
    public Button btnOpenFile;
    public TextArea txtOutput;
    public TextArea txtDictionary;
    public TextArea txtInput;

    public void btnOpenFile_onMouseClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть словарь");

        var extFilter = new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)", "*.txt");
        var f = fileChooser.getExtensionFilters();
        f.addAll();
        f.add(extFilter);

        var currentDir = System.getProperty("user.dir");
        fileChooser.setInitialDirectory(new File(currentDir));

        var file = fileChooser.showOpenDialog(null);
        if (file != null) {
            loadFileToTextArea(file);
        }
    }

    private void loadFileToTextArea(File file) {
        try {
            var content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            txtDictionary.clear();
            txtDictionary.setText(content);

        } catch (IOException e) {
            MessageHelper.showError("Не удалось прочитать файл","Ошибка чтения файла: " + e.getMessage());
        }
    }


    public void inputTest_Typed(KeyEvent keyEvent) {
        var ch = keyEvent.getCharacter();
        if (Objects.equals(ch, "\n") || Objects.equals(ch, "\r")){

            translate();
        }
    }

    private void translate() {
        var dictionary = new WordDictionary();
        try {
            try (var reader = new StringReader(txtDictionary.getText())) {
                dictionary.Read(reader);
            }
        } catch (Lab3FormatException ex) {
            MessageHelper.showError("Неверный формат словаря", String.format("Dictionary has format error%n%s", ex));
        } catch (Lab3DuplicateException ex) {
            MessageHelper.showError("Найдены дубликаты записей", String.format("Duplicate items in dictionary%n%s", ex));
        }

        var translated = dictionary.translate(txtInput.getText());

        txtOutput.setText(translated);
    }

    public void btnTranslate_Clicked() {
        translate();
    }
}
