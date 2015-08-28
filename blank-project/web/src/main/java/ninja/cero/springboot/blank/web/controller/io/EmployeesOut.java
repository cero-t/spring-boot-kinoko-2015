package ninja.cero.springboot.blank.web.controller.io;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeesOut {
    public Integer empno;

    public String ename;

    public String job;

    public Integer mgr;

    public LocalDate hiredate;

    public BigDecimal sal;

    public BigDecimal comm;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
