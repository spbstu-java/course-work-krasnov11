package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab2;

public interface ValueProvider {
    Object getValue(Class<?> c) throws ValueProviderException;
}
