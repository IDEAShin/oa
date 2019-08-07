package com.ideashin.oa.biz;

import com.ideashin.oa.entity.Employee;

/**
 * @Author: Shin
 * @Date: 2019/8/6 17:56
 * @Blog: ideashin.com
 */
public interface GlobalBiz {

    /**
     * 登录
     * @param sn
     * @param password
     * @return
     */
    Employee login(String sn, String password);

    /**
     * 修改密码
     * @param employee
     */
    void changePassword(Employee employee);
}
