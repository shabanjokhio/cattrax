package com.cattrax.services.impl;

import com.cattrax.dao.impl.PatientDaoServiceImpl;
import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;
import com.cattrax.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientDaoServiceImpl patientDaoService;

    //CREATE
    @Override
    public Patient registerOrEditPatient(Patient patient) {
        return (Patient) patientDaoService.saveOrUpdate(patient);
    }

    //RETREIVE
    @Override
    public Patient getPatientById(Integer id) {
        return patientDaoService.getById(id);
    }

    //RETREIVE
    @Override
    public List<Patient> listPatients() {
        return patientDaoService.listAll();
    }

    //DELETE
    @Override
    public void deletePatient(Integer patientId) {
        patientDaoService.delete(patientId);
    }


    @Override
    public Patient searchPatientByNhi(String nhi) {
        return patientDaoService.getPatientByNHI(nhi);
    }

    @Override
    //String dob is converted to Data dob
    public Patient searchPatientByNamesAndDoB(String firstNames, String lastName, String dob) throws ParseException {
        return patientDaoService.searchPatientByFirstNameLastNameAndDOB(firstNames, lastName, new SimpleDateFormat("dd/mm/yyyy").parse(dob));
    }

    @Override
    public Practice getPracticeThisPatientIsRegisteredAt(Integer patientId) {
        return patientDaoService.getById(patientId).getRegisteredAt();
    }

    @Override
    public User getUserThisPatientIsReferredBy(Integer patientId) {
        return patientDaoService.getById(patientId).getReferredBy();
    }

    @Override
    public User getUserThisPatientIsRegisteredBy(Integer patientId) {
        return patientDaoService.getById(patientId).getRegisteredBy();
    }

    @Override
    public List<Patient> searchPatientByFirstNames(String firstNames) {
        return patientDaoService.searchPatientByFirstNames(firstNames);
    }

    @Override
    public List<Patient> searchPatientByLastName(String lastName) {
        return patientDaoService.searchPatientByLastName(lastName);
    }

    @Override
    public List<Patient> searchPatientByDOB(Date dob) {
        return patientDaoService.searchPatientByDOB(dob);
    }

    /**
     * search patients by user registered by:
     *
     * @param registeredBy
     * @return
     */
    @Override
    public List<Patient> searchPatientsByUserRegisteredBy(User registeredBy) {
        return patientDaoService.searchPatientsByUserRegisteredBy(registeredBy);
    }

    /**
     * search patients registered at the practice:
     *
     * @param registeredAt
     * @return
     */
    @Override
    public List<Patient> searchPatientsByPracticeRegisteredAt(Practice registeredAt) {
        return patientDaoService.searchPatientsByPracticeRegisteredAt(registeredAt);
    }

}
