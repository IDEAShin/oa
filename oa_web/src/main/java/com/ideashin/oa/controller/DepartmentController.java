package com.ideashin.oa.controller;

import com.ideashin.oa.biz.DepartmentBiz;
import com.ideashin.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: Shin
 * @Date: 2019/8/5 14:40
 * @Blog: ideashin.com
 */
@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentBiz departmentBiz;

    @RequestMapping("/list")
    public String list(Map<String, Object> map) { //map是java的类型，不依赖与springmvc

        map.put("list", departmentBiz.getAll());
        return "department_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {//通常不需要传递，但是如果使用springmvc提供的form标签，那么就必须封装一个deprtment对象，或者map
        map.put("department", new Department());
        return "department_add";
    }

    @RequestMapping("/add")
    public String add(Department department) {
        departmentBiz.add(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_edit", params = "sn") //设置过滤，指定request中必须包含某些参数值是，才让该方法处理。
    public String toEdit(String sn, Map<String, Object> map) {
        map.put("department", departmentBiz.get(sn));
        return "department_edit";
    }

    @RequestMapping("/edit")
    public String edit(Department department) {
        departmentBiz.edit(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn) {
         departmentBiz.remove(sn);
        return "redirect:list";
    }

}
