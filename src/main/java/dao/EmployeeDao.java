package dao;

import model.Employee;
import java.util.List;

public interface EmployeeDao {
    void create(Employee employee);
    Employee findById(Integer id);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(Employee employee);

}
