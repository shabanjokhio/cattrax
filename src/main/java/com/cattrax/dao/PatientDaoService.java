package com.cattrax.dao;

import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell User on 27/02/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

public interface PatientDaoService extends CRUDDaoService <Patient>{

    Patient searchPatientByFirstNameLastNameAndDOB(String firstNames, String lastName, Date dob);
    Patient getPatientByNHI(String nhi);

    List<Patient> searchPatientByFirstNames(String firstNames);
    List<Patient> searchPatientByLastName(String lastName);
    List<Patient> searchPatientByDOB(Date dob); //patientDOB

    /**
     * search patients by user registered by:
     * @param registeredBy
     * @return: This method should be in UserService
     */
    List<Patient> searchPatientsByUserRegisteredBy(User registeredBy);//find list of patients registered by this user:

    /**
     * search patients registered at the practice:
     * @param registeredAt
     * @return
     * This method should be in UserService
     */
    List<Patient> searchPatientsByPracticeRegisteredAt(Practice registeredAt);



}
