package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.ex1.HeroMoveInfo;
import javafx.collections.FXCollections;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.ex1.models.*;

public class HelloController {
    public Tab exercise1;
    public Tab exercise2;
    public Tab exercise3;
    public Tab exercise4;
    public Canvas canvas1;

    @FXML
    private ComboBox<HeroMoveInfo> cbxHeroMoveType;

    private Hero hero;

    @FXML
    private void initialize() {
        initExercise1();
    }

    private void initExercise1() {

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
        cbxHeroMoveType.setOnAction(e -> {
            hero.setMoveStrategy(cbxHeroMoveType.getValue().getHeroMove());
        });

        hero = new Hero(canvas1.getGraphicsContext2D(), "DarkDuck", 0, 0, heroes.getFirst().getHeroMove());

        canvas1.setOnMouseClicked(me -> {
            hero.move(me.getX(), me.getY());
        });
    }
}
