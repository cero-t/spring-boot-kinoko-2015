package ninja.cero.springboot.blank.web.controller.converter;

import ninja.cero.springboot.blank.domain.Employee;
import ninja.cero.springboot.blank.web.controller.io.EmployeesIn;
import ninja.cero.springboot.blank.web.controller.io.EmployeesOut;
import ninja.cero.springboot.blank.web.entity.EmployeeCondition;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeesConverter {
    public Employee toEmployee(EmployeesIn in) {
        Employee employee = new Employee();

        employee.ename = in.ename;
        employee.job = in.job;
        employee.mgr = in.mgr;
        employee.hiredate = in.hiredate;
        employee.sal = in.sal;
        employee.comm = in.comm;
        employee.deptno = in.deptno;

        return employee;
    }

    public EmployeesOut toEmployeeOut(Employee employee) {
        EmployeesOut out = new EmployeesOut();

        if (employee != null) {
            out.empno = employee.empno;
            out.ename = employee.ename;
            out.job = employee.job;
            out.mgr = employee.mgr;
            out.hiredate = employee.hiredate;
            out.sal = employee.sal;
            out.comm = employee.comm;
        }

        return out;
    }

    public List<EmployeesOut> toEmployeeListOut(List<Employee> employees) {
        return employees.stream()
                .map(this::toEmployeeOut)
                .collect(Collectors.toList());
    }

    public EmployeeCondition toEmployeeCondition(String name, LocalDate hiredateFrom, LocalDate hiredateTo, Integer deptno) {
        EmployeeCondition condition = new EmployeeCondition();

        condition.name = name;
        condition.hiredateFrom = hiredateFrom;
        condition.hiredateTo = hiredateTo;
        condition.deptno = deptno;

        return condition;
    }
}
