package com.ideashin.oa.dao;

import com.ideashin.oa.entity.DealRecord;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/7 11:04
 * @Blog: ideashin.com
 * 处理记录实体
 */
public interface DealRecordDao {
    void insert(DealRecord dealRecord);

    /**
     * 根据报销单id查询所有流程
     * @param cvid
     * @return
     */
    List<DealRecord> selectByClaimVoucher(Integer cvid);

}
