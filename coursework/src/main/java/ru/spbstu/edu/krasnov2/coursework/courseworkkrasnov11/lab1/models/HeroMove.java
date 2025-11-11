package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models;

import javafx.scene.canvas.GraphicsContext;

public interface HeroMove {
    void move(HeroPosition hero, double x, double y);
    String getMoveName();
}

