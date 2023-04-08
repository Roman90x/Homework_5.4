import dao.CityDao;
import dao.CityDaoImpl;
import dao.EmployeeDaoImpl;
import model.City;
import model.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        CityDaoImpl cityDao = new CityDaoImpl();

        City city = cityDao.findById(cityDao.create(new City(null, "Tomsk", null)));

        employeeDao.create(new Employee(null, "Petr", "Pervyh", "m", 30, city));
        System.out.println("Получение города");
        cityDao.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("Получение сотрудника");
        employeeDao.findAll().forEach(System.out::println);
        System.out.println();

    }

}