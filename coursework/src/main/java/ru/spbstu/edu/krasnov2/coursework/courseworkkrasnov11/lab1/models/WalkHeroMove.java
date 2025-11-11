package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WalkHeroMove implements HeroMove {

    private final GraphicsContext gc;

    public WalkHeroMove(GraphicsContext gc){
        this.gc = gc;
    }

    @Override
    public void move(HeroPosition hero, double x, double y) {

        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.beginPath();
        gc.moveTo(hero.getXPos(), hero.getYPos());
        gc.lineTo(x, y);
        gc.stroke();

        gc.setFill(Color.BLUE);
        gc.fillText(String.format("walk(%.0f, %.0f)", x, y), x, y);
    }

    @Override
    public String getMoveName() {
        return "Walk";
    }
}
