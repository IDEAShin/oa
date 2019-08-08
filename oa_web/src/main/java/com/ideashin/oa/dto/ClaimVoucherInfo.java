package com.ideashin.oa.dto;

import com.ideashin.oa.entity.ClaimVoucher;
import com.ideashin.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @Author: Shin
 * @Date: 2019/8/8 14:20
 * @Blog: ideashin.com
 */
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
