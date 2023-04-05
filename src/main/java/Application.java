import dao.EmployeeDaoImpl;
import model.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

        employeeDao.create(new Employee("Petr", "Pervyh", "m", 30));
        System.out.println("Получение полного списка сотрудников с только что добавленным");
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("Получение сотрудника по id");
        Employee employee = employeeDao.findById(5);
        System.out.println(employee);

        employee.setGender("M");
        System.out.println("Обновление сотрудника");
        System.out.println(employeeDao.findById(5));

        employeeDao.delete(employee);
        System.out.println("Проверка на удаление");
        employeeDao.findAll().forEach(System.out::println);
    }

}