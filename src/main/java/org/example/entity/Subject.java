package org.example.entity;

public class Subject {
    private int id;

    private String name;
    private DayOfWeek dayOfWeek;
    private int classRoom;

    public Subject(int id, String name, DayOfWeek dayOfWeek, int classRoom) {
        this.id = id;
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.classRoom = classRoom;
    }

    public Subject(String name, DayOfWeek dayOfWeek, int classRoom) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.classRoom = classRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", dayOfWeek=" + dayOfWeek +
                ", classRoom=" + classRoom +
                '}';
    }
}
