package com.ideashin.oa.biz.impl;

import com.ideashin.oa.dao.DepartmentDao;
import com.ideashin.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 14:31
 * @Blog: ideashin.com
 */
@Service("departmentBiz")
public class DepartmentBizImpl implements com.ideashin.oa.biz.DepartmentBiz {

    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String sn) {
        departmentDao.delete(sn);
    }

    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
