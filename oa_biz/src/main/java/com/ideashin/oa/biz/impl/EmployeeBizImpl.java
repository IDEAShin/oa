package com.ideashin.oa.biz.impl;

import com.ideashin.oa.biz.EmployeeBiz;
import com.ideashin.oa.dao.DepartmentDao;
import com.ideashin.oa.dao.EmployeeDao;
import com.ideashin.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 22:32
 * @Blog: ideashin.com
 */
@Service("employeeBiz")
public class EmployeeBizImpl implements EmployeeBiz {

    @Autowired
    private EmployeeDao employeeDao;

    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
