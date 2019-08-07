package com.ideashin.oa.biz;

import com.ideashin.oa.entity.Employee;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 18:47
 * @Blog: ideashin.com
 */
public interface EmployeeBiz {
    void add(Employee employee);

    void edit(Employee employee);

    void remove(String sn);

    Employee get(String sn);

    List<Employee> getAll();
}
