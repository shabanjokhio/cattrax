package com.cattrax.services;

import com.cattrax.domain.CattraxReferral;

/**
 * Created by Dell User on 22/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public interface CattraxScoringEngine {
    /**
     *
     * @param referral
     * @return: CPAC score that will tell us the eligiblity for the public funding
     */
    double calculateCPACScore(CattraxReferral referral);

    /**
     * @param referral
     * @param threshold
     * @return: returns true if the patient is eligible for the public funded Cataract Surgery
     * surgery for the given DHB
     */
    boolean isEligibleForPublicFundedCataractSurgery(CattraxReferral referral, double threshold);

    /**
     * @param referral
     * @return: Returns true if the patient is eligible for the southern cross funded Cataract surgery
     */
    boolean isEligibleForSouthernCrossFunding(CattraxReferral referral);

    /**
     * @param referral
     * @return: Returns true if the patient is elighible for other insurance funded Cataract surgery
     */
    boolean isEligibleForOtherInsuranceFunding(CattraxReferral referral);

}
