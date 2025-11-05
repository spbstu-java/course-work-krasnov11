package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallCount {
    int value();
}
