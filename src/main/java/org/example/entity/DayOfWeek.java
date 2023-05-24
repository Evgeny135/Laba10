package org.example.entity;

public enum DayOfWeek {

    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота");
    private String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
