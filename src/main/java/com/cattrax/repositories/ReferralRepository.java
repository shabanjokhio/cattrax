package com.cattrax.repositories;

import com.cattrax.domain.CattraxReferral;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dell User on 14/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public interface ReferralRepository extends CrudRepository <CattraxReferral, Integer> {
    /*CattraxReferral findByPatientReferred(Patient patientReferred);
    CattraxReferral findByReferredBy(User user);*/
}
