package com.ideashin.oa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: Shin
 * @Date: 2019/8/5 14:02
 * @Blog: ideashin.com
 */
public class ClaimVoucher {
    private Integer id;

    private String cause;

    private String createSn;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createTime;

    private String nextDealSn;

    private Double totalAmount;

    private String status;
}
