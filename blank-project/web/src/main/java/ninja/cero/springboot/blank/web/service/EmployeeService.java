package ninja.cero.springboot.blank.web.service;

import ninja.cero.springboot.blank.domain.Employee;
import ninja.cero.springboot.blank.web.dao.EmployeeDao;
import ninja.cero.springboot.blank.web.entity.EmployeeCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
    @Autowired
    protected EmployeeDao employeeDao;

    public Employee getEmployee(Integer id) {
        return employeeDao.selectById(id);
    }

    public List<Employee> searchEmployee(EmployeeCondition condition) {
        return employeeDao.selectByCondition(condition);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.selectAll();
    }

    @Transactional(readOnly = false)
    public int createEmployee(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Transactional(readOnly = false)
    public int updateEmployee(Employee employee) {
        return employeeDao.updateById(employee);
    }
}
