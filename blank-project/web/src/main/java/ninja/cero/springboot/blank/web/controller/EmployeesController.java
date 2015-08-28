package ninja.cero.springboot.blank.web.controller;

import ninja.cero.springboot.blank.domain.Employee;
import ninja.cero.springboot.blank.framework.exception.ApplicationException;
import ninja.cero.springboot.blank.web.config.Errors;
import ninja.cero.springboot.blank.web.controller.converter.EmployeesConverter;
import ninja.cero.springboot.blank.web.controller.io.EmployeesIn;
import ninja.cero.springboot.blank.web.controller.io.EmployeesOut;
import ninja.cero.springboot.blank.web.entity.EmployeeCondition;
import ninja.cero.springboot.blank.web.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeesController {
    @Autowired
    protected EmployeesConverter converter;

    @Autowired
    protected EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<EmployeesOut> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return converter.toEmployeeListOut(employees);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public EmployeesOut getEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployee(id);
        return converter.toEmployeeOut(employee);
    }

    @RequestMapping(value = "/employees/search", method = RequestMethod.GET)
    public List<EmployeesOut> searchEmployee(@RequestParam("name") Optional<String> name,
                                             @RequestParam(value = "hiredateFrom", required = false) LocalDate hiredateFrom,
                                             @RequestParam(value = "hiredateTo", required = false) LocalDate hiredateTo,
                                             @RequestParam("deptno") Optional<Integer> deptno) {
        EmployeeCondition condition = converter.toEmployeeCondition(name.orElse(null),
                hiredateFrom, hiredateTo, deptno.orElse(null));
        List<Employee> employees = employeeService.searchEmployee(condition);
        return converter.toEmployeeListOut(employees);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public void postEmployee(@RequestBody @Valid EmployeesIn in) {
        Employee employee = converter.toEmployee(in);
        employeeService.createEmployee(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public void putEmployee(@RequestBody EmployeesIn in, @PathVariable("id") Integer id) {
        Employee employee = converter.toEmployee(in);
        employee.empno = id;
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/employees/ex")
    public void causeException() {
        throw new ApplicationException(Errors.PAYMENT_ERROR);
    }
}
