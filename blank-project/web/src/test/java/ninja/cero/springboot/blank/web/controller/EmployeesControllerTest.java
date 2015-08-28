package ninja.cero.springboot.blank.web.controller;

import ninja.cero.springboot.blank.test.TestContextConfig;
import ninja.cero.springboot.blank.web.BlankWeb;
import ninja.cero.springboot.blank.web.controller.io.EmployeesIn;
import ninja.cero.springboot.blank.web.controller.io.EmployeesOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BlankWeb.class, TestContextConfig.class})
@Transactional
public class EmployeesControllerTest {
    @Autowired
    EmployeesController controller;

    @Test
    public void testGetAll() {
        List<EmployeesOut> employees = controller.getEmployees();
        System.out.println(employees);
    }

    @Test
    public void testGetById() {
        EmployeesOut employee = controller.getEmployee(7369);
        System.out.println(employee);
    }

    @Test
    public void testGetByCondition() {
        List<EmployeesOut> employees = controller.searchEmployee(Optional.<String>empty(), LocalDate.of(1981, 12, 1), null, Optional.of(20));
        System.out.println(employees);
    }

    @Test
    public void testInsert() {
        EmployeesIn in = new EmployeesIn();
        in.ename = "TEST";
        in.job = "CLERK";
        in.mgr = 7362;
        in.hiredate = LocalDate.of(2015, 8, 26);
        in.sal = BigDecimal.valueOf(700);
        in.comm = null;
        in.deptno = 20;

        controller.postEmployee(in);
    }

    @Test
    public void testUpdate() {
        EmployeesIn in = new EmployeesIn();
        in.ename = "SMITH2";
        in.job = "SALESMAN";
        in.mgr = 7698;
        in.hiredate = LocalDate.of(1980, 12, 18);
        in.sal = BigDecimal.valueOf(900);
        in.comm = null;
        in.deptno = 30;

        controller.putEmployee(in, 7369);
    }
}
