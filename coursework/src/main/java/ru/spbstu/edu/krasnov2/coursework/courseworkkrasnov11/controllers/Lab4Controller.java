package ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.controllers;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.helpers.ExecutableCmd;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.helpers.StdoutRedirectExecuter;
import ru.spbstu.edu.krasnov2.coursework.courseworkkrasnov11.lab4.Lab4FormatException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Lab4Controller {
    public TextArea txtOutput;
    public TextField txtInput1;
    public TextField txtInput2;
    public TextField txtInput3;
    public TextField txtInput4;
    public TextField txtInput5;
    public TextField txtInput6;

    private final StdoutRedirectExecuter exec;

    public Lab4Controller(){
        exec = new StdoutRedirectExecuter(this::appendText);
    }


    /// выполнит метод, сделав редирект stdio
    private void execute(ExecutableCmd executableCmd){

        try {
            exec.execute(executableCmd);
        } catch (IOException e) {
            appendText("Error has occurred while executing: " + e);
        }
    }

    private List<Integer> GetIntegers(String text) throws Lab4FormatException {
        var list = new ArrayList<Integer>();

        if (text != null && !text.isEmpty()){
            var items = text.split(" ");
            for (var item : items){
                var strValue = item.trim();
                if (strValue.isEmpty())
                    continue;

                var intValue = Integer.parseInt(strValue);

                list.add(intValue);
            }
        }

        return list;
    }

    private List<String> GetStrings(String text) throws Lab4FormatException {
        var list = new ArrayList<String>();

        if (text != null && !text.isEmpty()){
            var items = text.split(" ");
            for (var item : items){
                var strValue = item.trim();
                if (!strValue.isEmpty())
                    list.add(strValue);
            }
        }

        return list;
    }

    // вывод массива
    private static String arrayAsString(Iterable<?> list){

        var afterFirst = false;
        var sb = new StringBuilder();
        sb.append("[");

        for (var item : list){
            if (afterFirst)
                sb.append(", ");
            else {
                afterFirst = true;
            }

            if (item instanceof String)
                sb.append('\"').append(item).append('\"');
            else
                sb.append(item);
        }
        sb.append("]");

        return sb.toString();
    }

    // метод, возвращающий среднее значение списка целых чисел
    private static double intAvg(List<Integer> list){
        return list
                .stream()
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    // метод, приводящий все строки в списке в верхний регистр
    // и добавляющий к ним префикс «_new_»
    private static List<String> upperConcatWithPrefix(List<String> strings){
        return strings
                .stream()
                .map(s -> "_new_" + s.toUpperCase())
                .toList();
    }

    // метод, возвращающий список квадратов всех встречающихся
    // только один раз элементов списка
    private static List<Integer> onlyOnePresentToSquare(List<Integer> numbers){
        return numbers
                .stream()
                .filter(x -> numbers
                        .stream()
                        .filter(y -> y.equals(x))
                        .count() == 1)
                .map(n -> n * n)
                .toList();
    }

    // метод, принимающий на вход коллекцию и возвращающий ее последний элемент
    // или кидающий исключение, если коллекция пуста
    private static <T> T getLastOrThrow(Collection<T> items){
        return items
                .stream()
                .reduce((n1, n2) -> n2)
                .orElseThrow();
    }

    // метод, принимающий на вход массив целых чисел, возвращающий сумму
    // чётных чисел или 0, если чётных чисел нет
    private static Integer sumEven(List<Integer> array){
        return array
                .stream()
                .filter(n -> n % 2 == 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    // метод, преобразовывающий все строки в списке в Map,
    // где первый символ – ключ, оставшиеся – значение
    private static Map<Character, String> asSuperMap(List<String> list){
        return list
                .stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1)
                ));
    }

    // записать в UI элемент данные
    private void appendText(String text){
        javafx.application.Platform.runLater(() -> {
            txtOutput.appendText(text);
            txtOutput.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void btnRun1_onMouseClicked() {
        execute(() -> {
            // метод, возвращающий среднее значение списка целых чисел
            System.out.println("\n\n---AVG---");

            List<Integer> integers = null;
            try{
                integers = GetIntegers(txtInput1.getText());
            }
            catch (Lab4FormatException | NumberFormatException e1){
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (integers == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(integers));
            var avg = intAvg(integers);
            System.out.printf("AVG = %f%n%n", avg);
        });
    }

    public void btnRun2_onMouseClicked() {

        execute(() -> {

            // метод, приводящий все строки в списке в верхний регистр
            // и добавляющий к ним префикс «_new_»
            System.out.println("\n\n---Upper New---");

            List<String> strings = null;
            try {
                strings = GetStrings(txtInput2.getText());
            } catch (Lab4FormatException | NumberFormatException e1) {
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (strings == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(strings));
            var str = upperConcatWithPrefix(strings);
            System.out.printf("Upper New: %s%n%n", arrayAsString(str));
        });
    }

    public void btnRun3_onMouseClicked() {
        execute(() -> {

            // метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
            System.out.println("\n\n---only one presented to square---");

            List<Integer> integers = null;
            try{
                integers = GetIntegers(txtInput3.getText());
            }
            catch (Lab4FormatException | NumberFormatException e1){
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (integers == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(integers));
            var sqtrList = onlyOnePresentToSquare(integers);
            System.out.printf("Squares: %s%n%n", arrayAsString(sqtrList));
        });
    }

    public void btnRun4_onMouseClicked() {
        execute(() -> {
            // метод, принимающий на вход коллекцию и возвращающий ее последний элемент
            // или кидающий исключение, если коллекция пуста
            System.out.println("\n\n---Last element---");

            List<Integer> integers = null;
            try{
                integers = GetIntegers(txtInput4.getText());
            }
            catch (Lab4FormatException | NumberFormatException e1){
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (integers == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(integers));
            System.out.printf("Last: %s%n", getLastOrThrow(integers));
            try {
                getLastOrThrow(List.of(new Integer[0]));
            }
            catch (NoSuchElementException ex) {
                System.out.println(ex.toString());
            }
            System.out.println();
        });
    }

    public void btnRun5_onMouseClicked() {
        execute(() -> {
            // метод, принимающий на вход массив целых чисел, возвращающий сумму
            // чётных чисел или 0, если чётных чисел нет
            System.out.println("\n\n---Even Integers---");

            List<Integer> integers = null;
            try{
                integers = GetIntegers(txtInput5.getText());
            }
            catch (Lab4FormatException | NumberFormatException e1){
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (integers == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(integers));
            System.out.printf("Even sum: %s%n", sumEven(integers));
            var empty = new Integer[0];
            System.out.printf("Origin array: %s%n", arrayAsString(List.of(empty)));
            System.out.printf("Even sum: %s%n%n", sumEven(List.of(empty)));

        });
    }

    public void btnRun6_onMouseClicked() {
        execute(() -> {
            // метод, преобразовывающий все строки в списке в Map,
            // где первый символ – ключ, оставшиеся – значение
            System.out.println("\n\n---Map---");

            List<String> strings = null;
            try {
                strings = GetStrings(txtInput6.getText());
            } catch (Lab4FormatException | NumberFormatException e1) {
                System.out.printf("Error has occurred while tying to get data. %s%n", e1);
            }

            if (strings == null)
                return;

            System.out.printf("Origin array: %s%n", arrayAsString(strings));

            Map<Character, String> map = null;
            try {
                map = asSuperMap(strings);
            }
            catch (Exception e){
                System.out.printf("Error has occurred while tying to convert to map. %s%n", e);
            }

            if (map == null)
                return;

            for (var key : map.keySet()){
                System.out.printf("'%c' : '%s'%n", key, map.get(key));
            }
        });
    }
}
