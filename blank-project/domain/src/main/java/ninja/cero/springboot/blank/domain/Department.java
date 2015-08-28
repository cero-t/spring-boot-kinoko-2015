package ninja.cero.springboot.blank.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Department {
    public Integer deptno;
    public String dname;
    public String loc;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
