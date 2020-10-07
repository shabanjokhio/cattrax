package com.cattrax.services;

import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;

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
public interface PracticeService {
    List<Practice> getAllPractices();
    Practice getPracticeById(Integer practiceId);
    Practice saveOrUpdatePractice(Practice practice);
    void deletePracticce(Integer practiceId);

 //   Patient getPatientByNHI();

    List<Patient> getRegisteredPatients(Practice practice);



}
