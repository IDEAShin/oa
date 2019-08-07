package com.ideashin.oa.dao;

import com.ideashin.oa.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 18:16
 * @Blog: ideashin.com
 */
@Repository("employeeDao")
public interface EmployeeDao {

    void insert(Employee employee);

    void update(Employee employee);

    void delete(String sn);

    Employee select(String sn);

    List<Employee> selectAll();

}
