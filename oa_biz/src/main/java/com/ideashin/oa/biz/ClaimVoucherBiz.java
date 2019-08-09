package com.ideashin.oa.biz;

import com.ideashin.oa.entity.ClaimVoucher;
import com.ideashin.oa.entity.ClaimVoucherItem;
import com.ideashin.oa.entity.DealRecord;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/8 13:51
 * @Blog: ideashin.com
 */
public interface ClaimVoucherBiz {

    /**
     * 保存报销单
     * @param claimVoucher 报销单
     * @param items 报销单条目集合
     */
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**
     * 修改报销单
     * @param claimVoucher 报销单
     * @param items 报销单条目集合
     */
    void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**
     * 获取报销单对象
     * @param id 报销单编号
     * @return
     */
    ClaimVoucher get(Integer id);

    /**
     * 获取所有报销单条目
     * @param cvid 报销单条目id
     * @return
     */
    List<ClaimVoucherItem> getItems(Integer cvid);

    /**
     * 获取审核记录
     * @param cvid 审核记录id
     * @return
     */
    List<DealRecord> getRecord(Integer cvid);

    /**
     * 获取个人报销单
     * @param csn 创建者编号
     * @return
     */
    List<ClaimVoucher> getForSelf(String csn);

    /**
     * 获取待处理报销单
     * @param ndsn 待处理人编号
     * @return
     */
    List<ClaimVoucher> getForDeal(String ndsn);

    /**
     * 提交
     * @param id
     */
    void submit(Integer id);
}
