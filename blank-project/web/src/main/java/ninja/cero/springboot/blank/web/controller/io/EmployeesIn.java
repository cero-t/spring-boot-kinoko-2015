package ninja.cero.springboot.blank.web.controller.io;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeesIn {
    @NotNull
    public String ename;

    public String job;

    public Integer mgr;

    public LocalDate hiredate;

    public BigDecimal sal;

    public BigDecimal comm;

    public Integer deptno;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
