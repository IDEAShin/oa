package com.ideashin.oa.biz.impl;

import com.ideashin.oa.biz.GlobalBiz;
import com.ideashin.oa.dao.EmployeeDao;
import com.ideashin.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Shin
 * @Date: 2019/8/6 17:58
 * @Blog: ideashin.com
 */
@Service("globalBizImpl")
public class GlobalBizImpl implements GlobalBiz {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee login(String sn, String password) {
        Employee employee = employeeDao.select(sn);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }

    public void changePassword(Employee employee) {
        employeeDao.update(employee);
    }
}
