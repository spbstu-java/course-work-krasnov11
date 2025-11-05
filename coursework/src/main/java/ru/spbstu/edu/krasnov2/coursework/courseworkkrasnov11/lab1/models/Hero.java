package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models;

import javafx.scene.canvas.GraphicsContext;

public class Hero {

    private final String _name;
    private final GraphicsContext gc;
    private HeroMove _moveStrategy;
    private double _xPos;
    private double _yPos;

    public Hero(GraphicsContext gc, String name, double xPos, double yPos, HeroMove moveStrategy){
        this.gc = gc;
        setMoveStrategy(moveStrategy);
        _name = name;
        _xPos = xPos;
        _yPos = yPos;
    }

    public void setMoveStrategy(HeroMove moveStrategy){
        if (moveStrategy == null)
            throw new IllegalArgumentException("moveStrategy is null");
        _moveStrategy = moveStrategy;
    }

    public void move(double x, double y){
        _moveStrategy.move(gc,this, x, y);
    }

    public String getName() {
        return _name;
    }

    public double getXPos() {
        return _xPos;
    }

    public void setXPos(double xPos) { this._xPos = xPos; }

    public double getYPos() {
        return _yPos;
    }

    public void setYPos(double yPos) {
        this._yPos = yPos;
    }
}
