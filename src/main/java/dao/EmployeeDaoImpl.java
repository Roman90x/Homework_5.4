package dao;

import lombok.RequiredArgsConstructor;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao{

    private final Connection connection;
    private static String INSERT_QUERY = "INSERT INTO employee (first_name, last_name, gender, age) VALUES (?,?,?,?)";
    private static String SELECT_BY_ID_QUERY = "SELECT * FROM employee WHERE id=?";
    private static String SELECT_ALL_QUERY = "SELECT * FROM employee";
    private static String UPDATE_QUERY = "UPDATE employee SET first_name=?, last_name=?, gender=?, age=? WHERE id=?";
    private static String DELETE_QUERY = "DELETE FROM employee WHERE id=?";

    @Override
    public void create(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4,employee.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return employeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(employeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }


    @Override
    public void update(Employee employee) {
        Employee createEmployee = findById(employee.getId());

        if (createEmployee != null) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getGender());
                statement.setInt(4,employee.getAge());
                statement.setInt(5,employee.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Employee createEmployee = findById(id);

        if (createEmployee != null) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
                statement.setInt(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Employee employeeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        int age = resultSet.getInt("age");
        int cityId = resultSet.getInt("city_id");

        return new Employee(id, firstName, lastName, gender ,age, cityId);


    }
}
