package com.cattrax.services;

import com.cattrax.domain.*;
import com.cattrax.domain.enums.PracticeType;
import com.cattrax.domain.enums.RegistrationType;
import com.cattrax.domain.enums.Title;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell User on 22/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ComponentScan(basePackages = {"com.cattrax"})
public class UserServiceTest {

    private  org.apache.log4j.Logger logger= Logger.getLogger(UserServiceTest.class);

    @Autowired   private PatientService patientService;
    @Autowired   private UserService userService;
    @Autowired   private PracticeService practiceService;
    @Autowired   private CattraxReferralService referralService;


    @Test
    public void testGetAllUsers(){
        List<User> cattraxUsers = userService.getAllUsers();
        assert cattraxUsers.size() > 1;
    }
    @Test
    public void testGetUserById(){
        User cattraxUser1= userService.getUserById(1);
        assert cattraxUser1 != null;
        assert cattraxUser1.getId() == 1;

    }
    @Test
    public void testSaveOrUpdateUser(){
        saveOneUser();
    }
    @Test
    public void deleteUser(){
        userService.deleteUser(1);
        assert userService.getUserById(1) == null;
    }

    private void saveOneUser(){
        User cattraxUser1= new User();
        cattraxUser1.setUserTitle(Title.MR);
        cattraxUser1.setFirstNames("John ");
        cattraxUser1.setLastName("Smith");
        cattraxUser1.setPhoneNumber("09-434535");
        cattraxUser1.setUserEmail("johnsmithwwr@cattrax.com");
        cattraxUser1.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        cattraxUser1.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));

        cattraxUser1.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("3445");
        jamesReg.setRegType(RegistrationType.GP);
        cattraxUser1.setProfessionalRegistration(jamesReg);

        logger.debug("Saving user with name:\t"+ cattraxUser1.getFirstNames());
        userService.saveOrUpdateUser(cattraxUser1);

    }

    @Test
    public void testAddReferralToUser(){
        User cattraxUser1= new User();
        cattraxUser1.setUserTitle(Title.MR);
        cattraxUser1.setFirstNames("John ");
        cattraxUser1.setLastName("Smith");
        cattraxUser1.setPhoneNumber("09-434535");
        cattraxUser1.setUserEmail("johnsmithwwr@cattrax.com");
        cattraxUser1.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        cattraxUser1.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));
        cattraxUser1.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("3445");
        jamesReg.setRegType(RegistrationType.GP);
        cattraxUser1.setProfessionalRegistration(jamesReg);

        List<CattraxReferral> referrals = referralService.listReferrals();

        cattraxUser1.addReferralIssued(referrals.get(0));

        //logger.debug("Saving user with name:\t"+ cattraxUser1.getFirstNames());

        User savedUser = userService.saveOrUpdateUser(cattraxUser1);

        assert savedUser.getId()!= null; //assert that it is saved correct in DB.
        assert savedUser.getReferralsIssued().contains(referrals.get(0));
        assert referrals.get(0).getReferredBy().getId() == savedUser.getId();

    }

    @Test
    public void testRemoveReferralFromUser(){
        User cattraxUser1= new User();
        cattraxUser1.setUserTitle(Title.MR);
        cattraxUser1.setFirstNames("John ");
        cattraxUser1.setLastName("Smith");
        cattraxUser1.setPhoneNumber("09-434535");
        cattraxUser1.setUserEmail("johnsmithwwr@cattrax.com");
        cattraxUser1.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        cattraxUser1.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));
        cattraxUser1.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("3445");
        jamesReg.setRegType(RegistrationType.GP);
        cattraxUser1.setProfessionalRegistration(jamesReg);

        List<CattraxReferral> referrals = referralService.listReferrals();

        cattraxUser1.addReferralIssued(referrals.get(0));

        //logger.debug("Saving user with name:\t"+ cattraxUser1.getFirstNames());

        User savedUser = userService.saveOrUpdateUser(cattraxUser1);

        assert savedUser.getId()!= null; //assert that it is saved correct in DB.
        assert savedUser.getReferralsIssued().contains(referrals.get(0));
        assert referrals.get(0).getReferredBy().getId() == savedUser.getId();

        assert savedUser.getReferralsIssued().size() == 1;

        cattraxUser1.removeReferralIssued(referrals.get(0));

        User savedUserAfterDeletion = userService.saveOrUpdateUser(cattraxUser1);

        assert savedUserAfterDeletion.getId() != null;
        assert (!savedUserAfterDeletion.getReferralsIssued().contains(referrals.get(0)));
        assert savedUser.getReferralsIssued().size() == 0;


    }

    @Test
    public void testAddRemovePatientReferred(){
        User cattraxUser1= new User();
        cattraxUser1.setUserTitle(Title.MR);
        cattraxUser1.setFirstNames("John ");
        cattraxUser1.setLastName("Smith");
        cattraxUser1.setPhoneNumber("09-434535");
        cattraxUser1.setUserEmail("johnsmithwwr@cattrax.com");
        cattraxUser1.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        cattraxUser1.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));
        cattraxUser1.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("3445");
        jamesReg.setRegType(RegistrationType.GP);
        cattraxUser1.setProfessionalRegistration(jamesReg);

        List<Patient> patients = patientService.listPatients();
        Patient patient1= patients.get(0);

        cattraxUser1.addPatientReferred(patient1);

        User savedUser = userService.saveOrUpdateUser(cattraxUser1);

        assert savedUser != null;
        assert savedUser.getId() != null;

        assert savedUser.getPatientsReferred().contains(patient1);
        assert savedUser.getPatientsReferred().size() == 1;

        assert patient1.getReferredBy() == savedUser;

        logger.info("Successful test for adding patient..");

        cattraxUser1.removePatientReferred(patient1);

        User savedUserAfterDeletion = userService.saveOrUpdateUser(cattraxUser1);

        assert !savedUserAfterDeletion.getPatientsReferred().contains(patient1);
        assert savedUserAfterDeletion.getPatientsReferred().size() == 0;
        assert patient1.getReferredBy() == null;

        logger.info("Successful test after removing patient ");

    }
    //if a part of code fetches two bags, it can not fetch both in EAGER way. So one has to be lazy and other
    //has to be eager. If both lazy then we get error, can not lazily initialize. or can not simultaneously fetch two bags...

    //This is the Dao level Test
    @Test
    public void testAddRemovePatientRegistered(){
        User cattraxUser1= new User();
        cattraxUser1.setUserTitle(Title.MR);
        cattraxUser1.setFirstNames("John ");
        cattraxUser1.setLastName("Smith");
        cattraxUser1.setPhoneNumber("09-434535");
        cattraxUser1.setUserEmail("johnsmithwwr@cattrax.com");
        cattraxUser1.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("1/24 Pine Street, Mt Eden");
        jamesAddress.setPostCode(1041);
        cattraxUser1.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));
        cattraxUser1.setWorksIn(jamesWorksIn);
        Registration jamesReg = new Registration();
        jamesReg.setRegNumber("3445");
        jamesReg.setRegType(RegistrationType.GP);
        cattraxUser1.setProfessionalRegistration(jamesReg);

        Practice currentPractice = practiceService.getAllPractices().get(0);
        cattraxUser1.setCurrentPractice(currentPractice);

        List<Patient> patients = patientService.listPatients();
        Patient patient1= patients.get(0);

        cattraxUser1.addPatientRegistered(patient1);

        User savedUser = userService.saveOrUpdateUser(cattraxUser1);

        assert savedUser != null;
        assert savedUser.getId() != null;

        assert savedUser.getPatientsRegistered().contains(patient1);
        assert savedUser.getPatientsRegistered().size() == 1;

        assert patient1.getRegisteredBy() == savedUser;

        logger.debug("Successful test for registering patient ...");
        cattraxUser1.removePatientRegistered(patient1);

        User savedUserAfterDeletion = userService.saveOrUpdateUser(cattraxUser1);

        assert !savedUserAfterDeletion.getPatientsRegistered().contains(patient1);
        assert savedUserAfterDeletion.getPatientsRegistered().size() == 0;
        assert patient1.getReferredBy() == null;
        logger.debug("Successful test after removing patient registration ... ");
    }

    //Test passed: patient transferred from current practice to the toPractice.
    @Test
    public void testTransferPatient(){
        Patient patient1 = patientService.getPatientById(1);

        patient1.setRegisteredAt(practiceService.getPracticeById(1));

        logger.info("current practice:\t"+ patient1.getRegisteredAt().getPracticeName());//Spec Savers

        Practice toPractice = new Practice();
        toPractice.setPracticeName("OPSM");
        toPractice.setPracticeAddress(null);
        toPractice.setPracticeType(PracticeType.PRIVATE);
        toPractice.setPracticeEmail("opsm@gmail.com");
        toPractice.setPracticeFaxNumber("12123555");
        toPractice.setPracticePhoneNumber("232342342");

        boolean transferred = userService.transferPatient(patient1, toPractice);

        assert transferred == true;
        assert !patient1.getRegisteredAt().getPracticeName().equals("Spec Savers");

        assert patient1.getRegisteredAt().getPracticeName().equals("OPSM");

        logger.info("Patient Transferred successfully...");
    }

}