package com.cattrax.services;

import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;

import java.text.ParseException;
import java.util.Date;
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
public interface PatientService {

    List<Patient> listPatients();
    Patient registerOrEditPatient(Patient patient);
    Patient getPatientById(Integer id);
    void deletePatient(Integer patientId);
    Patient searchPatientByNhi(String nhi);

    Patient searchPatientByNamesAndDoB(String firstName, String lastName, String dob) throws ParseException;

    Practice getPracticeThisPatientIsRegisteredAt(Integer patientId);
    User getUserThisPatientIsReferredBy(Integer patientId);
    User getUserThisPatientIsRegisteredBy(Integer patientId);

    List<Patient> searchPatientByFirstNames(String firstNames);
    List<Patient> searchPatientByLastName(String lastName);
    List<Patient> searchPatientByDOB(Date dob); //patientDOB

    /**
     * search patients by user registered by:
     * @param registeredBy
     * @return
     */
    List<Patient> searchPatientsByUserRegisteredBy(User registeredBy);//find list of patients registered by this user:

    /**
     * search patients registered at the practice:
     * @param registeredAt
     * @return
     */
    List<Patient> searchPatientsByPracticeRegisteredAt(Practice registeredAt);

}
