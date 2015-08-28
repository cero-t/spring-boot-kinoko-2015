package ninja.cero.springboot.blank.web.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;

public class EmployeeCondition {
    public String name;
    public LocalDate hiredateFrom;
    public LocalDate hiredateTo;
    public Integer deptno;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
