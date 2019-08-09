package com.ideashin.oa.dao;

import com.ideashin.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 根据部门编号和职务查询员工：两个参数，需要使用注解
     * @param dsn
     * @param post
     * @return
     */
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}
