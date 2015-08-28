package ninja.cero.springboot.blank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    public Integer empno;
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
