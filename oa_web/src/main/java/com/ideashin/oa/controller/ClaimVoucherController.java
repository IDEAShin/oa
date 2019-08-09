package com.ideashin.oa.controller;

import com.ideashin.oa.biz.ClaimVoucherBiz;
import com.ideashin.oa.dto.ClaimVoucherInfo;
import com.ideashin.oa.entity.Employee;
import com.ideashin.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: Shin
 * @Date: 2019/8/8 14:31
 * @Blog: ideashin.com
 */
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("items", Contant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info) {//与上面的info对应
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.save(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping(value = "/to_edit", params = "id")
    public String toEdit(int id, Map<String, Object> map) {
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherBiz.get(id));
        info.setItems(claimVoucherBiz.getItems(id));
        map.put("info", info);
        return "claim_voucher_edit";
    }

    @RequestMapping("/edit")
    public String edit(HttpSession session, ClaimVoucherInfo info) {//与上面的info对应
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherBiz.edit(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/detail")
    public String detail(int id, Map<String, Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put(("records"), claimVoucherBiz.getRecord(id));
        return "claim_voucher_detail";
    }

    /**
     * 当前用户报销单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/self")
    public String self(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    /**
     * 待处理报销单
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        map.put("list", claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/submit")
    public String submit(Integer id) {
        claimVoucherBiz.submit(id);
        return "redirect:deal";
    }

}
