package com.ideashin.oa.dao;

import com.ideashin.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/7 11:07
 * @Blog: ideashin.com
 */
public interface ClaimVoucherItemDao {
    void insert(ClaimVoucherItem claimVoucherItem);

    void update(ClaimVoucherItem claimVoucherItem);

    void delete(Integer id);

    /**
     * 根据报销单编号查询报销单
     * @param cvid
     * @return
     */
    List<ClaimVoucherItem> selectByClaimVoucher(Integer cvid);
}
