package com.ideashin.oa.dao;

import com.ideashin.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/5 14:06
 * @Blog: ideashin.com
 */
@Repository("departmentDao")
public interface DepartmentDao {

    void insert(Department department);

    void update(Department department);

    void delete(String sn);

    Department select(String sn);

    List<Department> selectAll();

}
