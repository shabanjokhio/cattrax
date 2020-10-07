package com.cattrax.services.impl;

import com.cattrax.dao.impl.CattraxReferralDaoServiceImpl;
import com.cattrax.domain.CattraxReferral;
import com.cattrax.domain.Patient;
import com.cattrax.domain.User;
import com.cattrax.services.CattraxReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dell User on 15/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@Service
public class CattraxReferralServiceImpl implements CattraxReferralService {

    @Autowired CattraxReferralDaoServiceImpl referralDaoService;
    @Autowired CattraxScoringEngineImpl  cattraxScoringEngine;

    public static final double THRESHOLD_VALUE = 50;

    @Override
    public List<CattraxReferral> listReferrals() {
        return referralDaoService.listAll();
    }

    @Override
    public CattraxReferral saveOrEditReferral(CattraxReferral referral) {
        return referralDaoService.saveOrUpdate(referral);
    }

    @Override
    public CattraxReferral getReferralById(Integer referralId) {
        return referralDaoService.getById(referralId);
    }

    @Override
    public void deleteReferral(Integer referralId) {
        referralDaoService.delete(referralId);
    }

    /**
     * Method to get Patient for which this referral has been referred.
     * @param referralId
     * @return
     */
    @Override
    public Patient getPatientReferred(Integer referralId) {
        return referralDaoService.getById(referralId).getPatientReferred();
    }

    @Override
    public User getReferredBy(Integer referralId) {
        return referralDaoService.getById(referralId).getReferredBy();
    }

    @Override
    public double calculateCPACScore(CattraxReferral referral) {
        return cattraxScoringEngine.calculateCPACScore(referral);
    }
    @Override
    public boolean eligibleForPublicFunding(CattraxReferral referral) {
        return cattraxScoringEngine.isEligibleForPublicFundedCataractSurgery(referral, THRESHOLD_VALUE);
    }

    @Override
    public boolean eligibleForSouthernCrossFunding(CattraxReferral referral) {
        return cattraxScoringEngine.isEligibleForSouthernCrossFunding(referral);
    }
    @Override
    public boolean eligibleForOtherInsurance(CattraxReferral referral) {
        return cattraxScoringEngine.isEligibleForOtherInsuranceFunding(referral);
    }

    // getters and setters:
    public void setReferralDaoService(CattraxReferralDaoServiceImpl referralDaoService) {
        this.referralDaoService = referralDaoService;
    }
    public void setCattraxScoringEngine(CattraxScoringEngineImpl cattraxScoringEngine) {
        this.cattraxScoringEngine = cattraxScoringEngine;
    }
    public CattraxReferralDaoServiceImpl getReferralDaoService() {
        return referralDaoService;
    }
    public CattraxScoringEngineImpl getCattraxScoringEngine() {
        return cattraxScoringEngine;
    }


}