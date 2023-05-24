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

public class SubjectsDao {
    private static final SubjectsDao INSTANCE = new SubjectsDao();

    private List<String> dayOfWeekList = new ArrayList<>();

    public List<String> getDayOfWeekFromSubjectCount(int count) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT day
                    FROM Subjects
                    GROUP BY day
                    HAVING count(name)=?
                    """);

            preparedStatement.setInt(1, count);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> dayOfWeek = new ArrayList<>();
            while (resultSet.next()) {
                dayOfWeekList.add(resultSet.getString("day"));
            }
            return dayOfWeekList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getDayCountsClassroom(int count){
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT day
                                        FROM Subjects
                                        GROUP BY day
                                        HAVING count(classroom)=?
                    """);

            preparedStatement.setInt(1, count);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> dayOfWeek = new ArrayList<>();
            while (resultSet.next()) {
                dayOfWeekList.add(resultSet.getString("day"));
            }
            return dayOfWeekList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void replaceSubjectsFromDayOfWeek(String name, DayOfWeek day, DayOfWeek dayOfWeek){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement preparedStatement = connection.prepareStatement("""
        UPDATE subjects SET
                            day = ?
        WHERE name = ? AND day = ?;
""");
            preparedStatement.setString(1,dayOfWeek.getName());
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,day.getName());
            int i = preparedStatement.executeUpdate();
            if (i ==0){
                System.out.println("Предмета не найдено или он уже на заданном дне недели");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private SubjectsDao() {
    }

    public static SubjectsDao getInstance() {
        return INSTANCE;
    }
}
