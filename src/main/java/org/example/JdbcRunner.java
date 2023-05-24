package org.example;

import org.example.dao.SubjectsDao;
import org.example.dao.TeacherDao;
import org.example.entity.DayOfWeek;
import org.example.entity.Subject;
import org.example.entity.Teacher;

import java.util.List;
import java.util.Optional;

public class JdbcRunner {
    public static void main(String[] args) {
        TeacherDao teacherDao = TeacherDao.getInstance();
        SubjectsDao subjectsDao = SubjectsDao.getInstance();

        System.out.println("Преподаватели работающие в пятницу в 210 аудитории");
        List<Teacher> teacherList = teacherDao.getInfoTeacherWorkInDayAndClassroom(DayOfWeek.FRIDAY, 210);
        for (Teacher teacher :
                teacherList) {
            System.out.println(teacher);
        }
        System.out.println();

        System.out.println("Вывести преподавателей,которые не работают в понедельник");
        List<Teacher> teacherList1 = teacherDao.getInfoTeacherNotWorkThisDayOfWeek(DayOfWeek.MONDAY);
        System.out.println(teacherList1);

        System.out.println();

        System.out.println("Получить день недели в который проводится 2 занятия ");
        List<String> daoOfWeekFromSubjectCount = subjectsDao.getDayOfWeekFromSubjectCount(2);
        System.out.println(daoOfWeekFromSubjectCount);

        System.out.println();

        System.out.println("Получить день недели, в который занято 2 аудитории ");
        List<String> dayOfWeek = subjectsDao.getDayCountsClassroom(2);
        System.out.println(dayOfWeek);

        subjectsDao.replaceSubjectsFromDayOfWeek("Инженерная графика",DayOfWeek.MONDAY,DayOfWeek.TUESDAY);

        Subject subject =new Subject(12,"Высшая математика",DayOfWeek.WEDNESDAY,333);

        Teacher teacher =  new Teacher("Новиков Анатолий Иванович",subject,6,50);

        teacherDao.addedNewTeacher(teacher);

    }
}
