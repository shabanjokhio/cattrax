package com.cattrax.services;

import com.cattrax.domain.CattraxReferral;
import com.cattrax.domain.Patient;
import com.cattrax.domain.User;

import java.util.List;

/**
 * Created by Dell User on 7/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public interface CattraxReferralService {
    //These methods will call CattraxDaoService methods:

    List<CattraxReferral> listReferrals();
    CattraxReferral saveOrEditReferral(CattraxReferral patient);
    CattraxReferral getReferralById(Integer referralId);
    void deleteReferral(Integer referralId);

    Patient getPatientReferred(Integer referralId);
    User getReferredBy(Integer referralId);

    //Methods below related to calculating Cattrax scoring
    double calculateCPACScore(CattraxReferral referral);
    boolean eligibleForPublicFunding(CattraxReferral referral);
    boolean eligibleForSouthernCrossFunding(CattraxReferral referral);
    boolean eligibleForOtherInsurance(CattraxReferral referral);

}
