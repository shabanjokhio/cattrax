package com.cattrax.dao.impl;

import com.cattrax.dao.CRUDDaoService;
import com.cattrax.domain.CattraxReferral;
import com.cattrax.repositories.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class CattraxReferralDaoServiceImpl implements CRUDDaoService<CattraxReferral> {
    private ReferralRepository referralRepository;

    @Autowired
    public void setReferralRepository(ReferralRepository referralRepository) {
        this.referralRepository = referralRepository;
    }

    @Override
    public List<CattraxReferral> listAll() {
        List<CattraxReferral> referrals = new ArrayList<>();
        referralRepository.findAll().forEach(referrals::add);
        return referrals;
    }

    @Override
    public CattraxReferral getById(Integer id) {
        return referralRepository.findOne(id);
    }

    @Override
    public CattraxReferral saveOrUpdate(CattraxReferral domainObject) {
        return referralRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        referralRepository.delete(id);

    }

}
