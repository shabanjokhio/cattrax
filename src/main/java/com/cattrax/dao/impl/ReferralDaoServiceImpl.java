package com.cattrax.dao.impl;

import com.cattrax.dao.ReferralDoaService;
import com.cattrax.domain.CattraxReferral;
import com.cattrax.repositories.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell User on 14/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public class ReferralDaoServiceImpl implements ReferralDoaService {
    @Autowired
    private ReferralRepository referralRepository;

    public ReferralRepository getReferralRepository() {
        return referralRepository;
    }
    public void setReferralRepository(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    @Override
    public List<CattraxReferral> listAllCattraxReferrals(){
        List<CattraxReferral> referrals = new ArrayList<>();
        referralRepository.findAll().forEach(referrals::add);
        return referrals;
    }
    @Override
    public CattraxReferral getReferralById(Integer id) {
        return referralRepository.findOne(id);
    }

    @Override
    public CattraxReferral saveOrUpdateReferral(CattraxReferral referral) {
        return referralRepository.save(referral);
    }

    @Override
    public void deleteReferral(Integer id) {
        referralRepository.delete(id);
    }
}
