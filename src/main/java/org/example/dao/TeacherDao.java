package org.example.dao;

import org.example.ConnectionManager;
import org.example.entity.DayOfWeek;
import org.example.entity.Subject;
import org.example.entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class TeacherDao {
    private static final TeacherDao INSTANCE = new TeacherDao();

    public void addedNewTeacher(Teacher teacher){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement= connection.prepareStatement("""
        INSERT INTO teachers (name, subject, hours_per_week, count_students) VALUES 
        (?,?,?,?)
""");

            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setInt(2,teacher.getSubject().getId());
            preparedStatement.setInt(3,teacher.getHoursPerWeek());
            preparedStatement.setInt(4,teacher.getCountStudents());

            int i = preparedStatement.executeUpdate();

            //teacher.setId(preparedStatement.getGeneratedKeys().getInt(0));

            if (i==0){
                System.out.println("Произошла ошибка или данный преподаватель уже сущестивует");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> getInfoTeacherWorkInDayAndClassroom(DayOfWeek dayOfWeek, int classRoom){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement = connection.prepareStatement("""
            SELECT Teachers.id,Teachers.name, Teachers.count_students,Teachers.hours_per_week,Subjects.id AS subid,Subjects.name AS subname,classroom 
            FROM Teachers JOIN Subjects ON Teachers.subject = Subjects.id 
            WHERE Subjects.day = ? AND Subjects.classroom = ?
            """);

            preparedStatement.setString(1,dayOfWeek.getName());
            preparedStatement.setInt(2,classRoom);

            ResultSet resultSet = preparedStatement.executeQuery();
            Teacher teacher = null;
            List<Teacher> teacherList = new ArrayList<>();
            while (resultSet.next()){
                int teacherId = resultSet.getInt("id");
                String teacherName =  resultSet.getString("name");
                int subjectId = resultSet.getInt("subid");
                String subjectName = resultSet.getString("subname");
                int subjectClassRoom = resultSet.getInt("classroom");
                int hoursPerWeek  = resultSet.getInt("hours_per_week");
                int countStudent = resultSet.getInt("count_students");
                Subject subject = new Subject(subjectId, subjectName, dayOfWeek, subjectClassRoom);
                teacher = new Teacher(teacherId, teacherName, subject,
                        hoursPerWeek,countStudent);
                teacherList.add(teacher);
            }
            return teacherList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Teacher> getInfoTeacherNotWorkThisDayOfWeek(DayOfWeek dayOfWeek){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement = connection.prepareStatement("""
            SELECT DISTINCT Teachers.id,Teachers.name , Teachers.count_students,Teachers.hours_per_week,Subjects.id AS subid,Subjects.name AS subname,classroom
                                                             FROM Teachers JOIN Subjects ON Teachers.subject = Subjects.id
                                                             WHERE Subjects.day != ?
            """);

            preparedStatement.setString(1,dayOfWeek.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            Teacher teacher = null;
            List<Teacher> teacherList = new ArrayList<>();
            while (resultSet.next()){
                int teacherId = resultSet.getInt("id");
                String teacherName =  resultSet.getString("name");
                int subjectId = resultSet.getInt("subid");
                String subjectName = resultSet.getString("subname");
                int subjectClassRoom = resultSet.getInt("classroom");
                int hoursPerWeek  = resultSet.getInt("hours_per_week");
                int countStudent = resultSet.getInt("count_students");
                teacher = new Teacher(teacherId, teacherName,new Subject(subjectId,subjectName,dayOfWeek,subjectClassRoom),
                       hoursPerWeek,countStudent);
                teacherList.add(teacher);
            }
            return teacherList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private TeacherDao(){}

    public static TeacherDao getInstance() {
        return INSTANCE;
    }
}
