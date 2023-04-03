
import dao.EmployeeDaoImpl;
import model.Employee;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "0990";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id=?")
        ) {

            statement.setInt(1, 1);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");
                Employee e = new Employee(id, firstName, lastName, gender, age, cityId);
                System.out.println("Задание 1. Получить и вывести в консоль полные данные об одном из работников");
                System.out.println(e);
                System.out.println();
            }

            EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(connection);

            System.out.println("Задание 2. Добавление сотрудника и получение полного списка");
            employeeDao.create(new Employee("Denis", "Radulov", "mzh", 32));
            employeeDao.findAll().forEach(System.out::println);
            System.out.println();

            Employee employee = employeeDao.findById(3);
            employee.setFirstName("UPDATE NAME");
            employeeDao.update(employee);

            System.out.println("Обновление сотрудника и получение сотрудника по id");
            System.out.println(employeeDao.findById(3));
            System.out.println();

            employeeDao.deleteById(5);
            System.out.println("Проверка удаления");
            employeeDao.findAll().forEach(System.out::println);

        }
    }
}
