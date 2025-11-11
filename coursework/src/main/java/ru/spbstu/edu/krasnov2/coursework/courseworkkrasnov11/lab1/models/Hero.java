package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models;

public class Hero implements HeroPosition {

    private final String _name;
    private HeroMove _moveStrategy;
    private double _xPos;
    private double _yPos;

    public Hero(String name, double xPos, double yPos, HeroMove moveStrategy){
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
        _moveStrategy.move(this, x, y);
        _xPos = x;
        _yPos = y;
    }

    public String getName() {
        return _name;
    }

    @Override
    public double getXPos() {
        return _xPos;
    }

    @Override
    public double getYPos() {
        return _yPos;
    }
}
