package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1;

import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab1.models.HeroMove;

public class HeroMoveInfo {

    private final String name;
    private final HeroMove heroMove;

    public HeroMoveInfo(String name, HeroMove heroMove){
        this.name = name;
        this.heroMove = heroMove;
    }

    @Override
    public String toString(){
        return name;
    }

    public HeroMove getHeroMove() {
        return heroMove;
    }
}
