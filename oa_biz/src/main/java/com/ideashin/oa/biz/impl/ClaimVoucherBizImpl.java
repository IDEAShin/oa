package com.ideashin.oa.biz.impl;

import com.ideashin.oa.biz.ClaimVoucherBiz;
import com.ideashin.oa.dao.ClaimVoucherDao;
import com.ideashin.oa.dao.ClaimVoucherItemDao;
import com.ideashin.oa.dao.DealRecordDao;
import com.ideashin.oa.entity.ClaimVoucher;
import com.ideashin.oa.entity.ClaimVoucherItem;
import com.ideashin.oa.entity.DealRecord;
import com.ideashin.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/8 14:01
 * @Blog: ideashin.com
 */
@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    private DealRecordDao dealRecordDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        /** 将待处理人设置为创建者 **/
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        /** 设置状态为新建状态**/
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);

        claimVoucherDao.insert(claimVoucher);
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }

    public ClaimVoucher get(Integer id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(Integer cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    public List<DealRecord> getRecord(Integer cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    public List<ClaimVoucher> getForSelf(String csn) {
        return claimVoucherDao.selectByCreateSn(csn);
    }

    public List<ClaimVoucher> getForDeal(String ndsn) {
        return claimVoucherDao.selectByNextDealSn(ndsn);
    }
}
