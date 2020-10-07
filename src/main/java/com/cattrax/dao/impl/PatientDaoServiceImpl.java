package com.cattrax.dao.impl;

import com.cattrax.dao.PatientDaoService;
import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;
import com.cattrax.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

@Service
@Profile("spring-data-jpa")
public class PatientDaoServiceImpl implements PatientDaoService {
    private PatientRepository patientRepository;

    @Override
    public List<Patient> listAll() {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add); //fun with Java 8
        return patients;
    }

    @Override
    public Patient getById(Integer id) {
        return patientRepository.findOne(id);
    }

    @Override
    public Patient saveOrUpdate(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Integer id) {
        patientRepository.delete(id);
    }

    //OTHER METHODS: TODO: unit test following methods:
    @Override
    public Patient searchPatientByFirstNameLastNameAndDOB(String firstNames, String lastName, Date dob) {
        return patientRepository.findByPatientFirstNamesAndLastNameAndPatientDOB(firstNames,lastName, dob);
    }

    @Override
    public Patient getPatientByNHI (String nhi){
        return patientRepository.findByPatientNHI(nhi);
    }

    @Override
    public List<Patient> searchPatientByFirstNames(String firstNames) {
        return patientRepository.findByPatientFirstNames(firstNames);
    }

    @Override
    public List<Patient> searchPatientByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }

    @Override
    public List<Patient> searchPatientByDOB(Date dob) {
        return patientRepository.findByPatientDOB(dob);
    }

    /**
     * search patients by user registered by:
     *
     *  @param registeredBy
     * @return
     */
    @Override
    public List<Patient> searchPatientsByUserRegisteredBy(User registeredBy) {
        return patientRepository.findByRegisteredBy(registeredBy);
    }

    /**
     * search patients registered at the practice:
     *
     * @param registeredAt
     * @return
     */
    @Override
    public List<Patient> searchPatientsByPracticeRegisteredAt(Practice registeredAt) {
        List<Patient> registeredPatients= new ArrayList<>();

        Iterable<Patient> allPatients = patientRepository.findAll();
        for (Patient p: allPatients){
            if(p.getRegisteredAt() != null){
                Practice practice =  p.getRegisteredAt();
                if(practice.getPracticeName() == registeredAt.getPracticeName())
                    registeredPatients.add(p);
            }
        }
        return registeredPatients;
    }



    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public PatientRepository getPatientRepository() {
        return patientRepository;
    }
    public PatientDaoService getPatientDaoRepositoryService(){
        return this;
    }


}
