package ninja.cero.springboot.blank.web.dao;

import ninja.cero.springboot.blank.domain.Employee;
import ninja.cero.springboot.blank.web.entity.EmployeeCondition;
import ninja.cero.sqltemplate.core.SqlTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDao {
    @Autowired
    protected SqlTemplate sqlTemplate;

    public Employee selectById(Integer id) {
        return sqlTemplate.forObject("sql/EmployeeDao/selectById.sql", Employee.class, id);
    }

    public List<Employee> selectByCondition(EmployeeCondition condition) {
        return sqlTemplate.forList("sql/EmployeeDao/selectByCondition.sql", Employee.class, condition);
    }

    public List<Employee> selectAll() {
        return sqlTemplate.forList("sql/EmployeeDao/selectAll.sql", Employee.class);
    }

    public int insert(Employee employee) {
        return sqlTemplate.update("sql/EmployeeDao/insert.sql", employee);
    }

    public int updateById(Employee employee) {
        return sqlTemplate.update("sql/EmployeeDao/updateById.sql", employee);
    }
}
