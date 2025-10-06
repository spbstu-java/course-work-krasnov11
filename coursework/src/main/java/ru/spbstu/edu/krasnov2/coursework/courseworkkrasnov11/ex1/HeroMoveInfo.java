package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.ex1;

import javafx.util.Callback;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.ex1.models.HeroMove;

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
