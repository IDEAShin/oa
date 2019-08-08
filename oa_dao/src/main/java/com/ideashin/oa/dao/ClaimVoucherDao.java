package com.ideashin.oa.dao;

import com.ideashin.oa.entity.ClaimVoucher;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/7 11:06
 * @Blog: ideashin.com
 */
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);

    void update(ClaimVoucher claimVoucher);

    void delete(String sn);

    ClaimVoucher select(Integer id);

    /**
     * 创建者所有的报销单
     * @param csn
     * @return
     */
    List<ClaimVoucher> selectByCreateSn(String csn);

    /**
     * 处理人所能处理的所有报销单
     * @param ndsn
     * @return
     */
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
