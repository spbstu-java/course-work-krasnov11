package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.ex1.models;

import javafx.scene.canvas.GraphicsContext;

public interface HeroMove {
    void move(GraphicsContext gc, Hero hero, double x, double y);
}

