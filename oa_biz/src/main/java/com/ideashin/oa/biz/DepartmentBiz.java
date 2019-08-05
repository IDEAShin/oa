package com.ideashin.oa.biz;

import com.ideashin.oa.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 14:27
 * @Blog: ideashin.com
 */
public interface DepartmentBiz {
    void add(Department department);

    void edit(Department department);

    void remove(String sn);

    Department get(String sn);

    List<Department> getAll();
}
