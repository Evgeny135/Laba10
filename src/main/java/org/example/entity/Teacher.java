package org.example.entity;

public class Teacher {
    private int id;
    private String name;
    private Subject subject;
    private int hoursPerWeek;
    private int countStudents;

    public Teacher(int id, String name, Subject subject, int hoursPerWeek, int countStudents) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.hoursPerWeek = hoursPerWeek;
        this.countStudents = countStudents;
    }

    public Teacher(String name, Subject subject, int hoursPerWeek, int countStudents) {
        this.name = name;
        this.subject = subject;
        this.hoursPerWeek = hoursPerWeek;
        this.countStudents = countStudents;
    }

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(int countStudents) {
        this.countStudents = countStudents;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", hoursPerWeek=" + hoursPerWeek +
                ", countStudents=" + countStudents +
                '}';
    }
}
