package com.ideashin.oa.biz.impl;

import com.ideashin.oa.biz.ClaimVoucherBiz;
import com.ideashin.oa.dao.ClaimVoucherDao;
import com.ideashin.oa.dao.ClaimVoucherItemDao;
import com.ideashin.oa.dao.DealRecordDao;
import com.ideashin.oa.dao.EmployeeDao;
import com.ideashin.oa.entity.ClaimVoucher;
import com.ideashin.oa.entity.ClaimVoucherItem;
import com.ideashin.oa.entity.DealRecord;
import com.ideashin.oa.entity.Employee;
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
    @Autowired
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        /** 将待处理人设置为创建者 */
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        /** 设置状态为新建状态 */
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);

        claimVoucherDao.insert(claimVoucher);
        for (ClaimVoucherItem item : items) {
            /** 设置报销条目创建人id */
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }

    public void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        /** 因为修改完还是要提交的，所以还是处理人本身和新创建状态 */
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);

        claimVoucherDao.update(claimVoucher);
        /** 有传递过来的集合，以及本来存在的集合，所以需要进行判断 */
        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        /** 遍历数据库中的条目，和新添加的条目比较，如果不相等，说明已经更新删除了*/
        for (ClaimVoucherItem old : olds) {
            boolean isHave = false;
            for (ClaimVoucherItem item : items) {
                if (item.getId() != null && item.getId().equals(old.getId())) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                claimVoucherItemDao.delete(old.getId());
            }
        }

        /** 遍历条目集合，如果id>0说明数据库中有数据，更新，反之添加 */
        for (ClaimVoucherItem item : items) {
            /** 设置报销条目创建人id */
            item.setClaimVoucherId(claimVoucher.getId());
            if (item.getId() != null && item.getId() > 0) {
                claimVoucherItemDao.update(item);
            } else {
                claimVoucherItemDao.insert(item);
            }
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

    public void submit(Integer id) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());

        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(), Contant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordDao.insert(dealRecord);
    }
}
