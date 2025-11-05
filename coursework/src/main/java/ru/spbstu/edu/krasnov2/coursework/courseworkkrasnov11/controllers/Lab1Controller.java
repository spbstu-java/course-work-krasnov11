package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.HeroMoveInfo;
import javafx.collections.FXCollections;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models.*;

public class Lab1Controller {
    public Canvas canvas1;

    @FXML
    private ComboBox<HeroMoveInfo> cbxHeroMoveType;

    private Hero hero;

    @FXML
    private void initialize() {
        var gc = canvas1.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, 800, 600);

        var heroes = FXCollections.observableArrayList(
                new HeroMoveInfo("Пеший ход", new WalkHeroMove()),
                new HeroMoveInfo("Бегом", new RunHeroMove()),
                new HeroMoveInfo("Полет", new FlyHeroMove())
        );

        cbxHeroMoveType.setItems(heroes);
        cbxHeroMoveType.setValue(heroes.getFirst());
        cbxHeroMoveType.setOnAction(e -> hero.setMoveStrategy(cbxHeroMoveType.getValue().getHeroMove()));

        hero = new Hero(canvas1.getGraphicsContext2D(), "DarkDuck", 0, 0, heroes.getFirst().getHeroMove());

        canvas1.setOnMouseClicked(me -> hero.move(me.getX(), me.getY()));
    }
}
