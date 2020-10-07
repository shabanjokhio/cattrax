package com.cattrax.repositories;

import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Dell User on 4/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

public interface PatientRepository  extends CrudRepository <Patient, Integer>{
    List<Patient> findByPatientFirstNames(String lastName);
    List<Patient> findByLastName(String firstNames);
    List<Patient> findByPatientDOB(Date dob); //patientDOB

    List<Patient> findByPatientFirstNamesAndPatientDOB(String firstNames, Date dob);
    Patient findByPatientFirstNamesAndLastNameAndPatientDOB(String firstNames, String lastName, Date dob);

    Patient findByPatientNHI(String nhi);
//  List<Patient> findByRegisteredAt(String practiceName); //TODO: Test this method later:

    List<Patient> findByRegisteredBy(User registeredBy);//find list of patients registered by this user:

    List<Patient> findByRegisteredAt(Practice registeredAt);
    //registeredAt
}
