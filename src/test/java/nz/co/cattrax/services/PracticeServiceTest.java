package com.cattrax.services;

import com.cattrax.domain.Address;
import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;
import com.cattrax.domain.enums.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class PracticeServiceTest {

    private Logger logger = Logger.getLogger(PracticeServiceTest.class);

    @Autowired private PracticeService practiceService;
    @Autowired private UserService userService;
    @Autowired private PatientService patientService;

    @Test
    public void testGetAllPractices(){
        List<Practice> registeredPractices  = practiceService.getAllPractices();
        assert registeredPractices.size() == 1;
    }

    @Test
    public void testGetPracticeById(){
        Practice p = practiceService.getPracticeById(1);
        assert p != null;
        assert p.getId() ==1;
        assert p.getPracticeName() == "Spec Savers";
    }
    @Test
    public void testSaveOrUpdatePractice(){
        savePractice();
        assert practiceService.getPracticeById(2).getPracticeName() == "Spec Savers2";
    }
    @Test
    public void testDeletePracticce(){
        practiceService.deletePracticce(1);
        assert practiceService.getPracticeById(1) == null;
    }
    private void savePractice(){
        logger.info("Creating practice");
        Practice p1= new Practice();
        Address p1Add= new Address();
        p1Add.setAddressLine1("1/24 Pine Street");
        p1Add.setAddressLine2("Mount Eden");
        p1Add.setPostCode(1041);
        p1.setPracticeAddress(p1Add);
        p1.setPracticeName("Spec Savers2");
        p1.setPracticeEmail("specsaver@2hotmail.com");
        p1.setPracticeFaxNumber("34453");
        p1.setPracticePhoneNumber("09-22345564234");
        p1.setPracticeType(PracticeType.OPTOMETRY);

        User newUser1 = new User();
        newUser1.setFirstNames("Muhammad");
        newUser1.setLastName("Shaban2");
        newUser1.setUserTitle(Title.DR);
        newUser1.setRoleType(RoleType.REFERRER);
        newUser1.setPhoneNumber("3445345");
        newUser1.setUserEmail("shaban@mail.com");
        newUser1.setUserPassword("my-sec-pw");
        Address newUser1Addr= new Address();
        newUser1Addr.setPostCode(1041);
        newUser1.setUserAddress(newUser1Addr);

        userService.saveOrUpdateUser(newUser1);

        User newUser2 = new User();
        newUser2.setId(1);
        newUser2.setFirstNames("Eddie");
        newUser2.setLastName("Rosser");
        newUser2.setUserTitle(Title.MR);
        newUser2.setRoleType(RoleType.PRACTICE_ADMIN);
        newUser2.setPhoneNumber("65645674");
        newUser2.setUserEmail("mshaban2@mail.com");
        newUser2.setUserPassword("eddie-pw");

        Address newUser2Addr= new Address();
        newUser2Addr.setPostCode(1042);
        newUser2.setUserAddress(newUser2Addr);

        userService.saveOrUpdateUser(newUser2);

        p1.addUser(newUser1);
        p1.addUser(newUser2);

        logger.info("Saving practice");
        practiceService.saveOrUpdatePractice(p1);
        logger.info("Saved practice with id:\t"+p1.getId());
    }

    //Test passed successfully. Date: 10/4/2017.
    @Test
    public void testAddRemoveRegisteredPatientsAtPractice(){
        Patient patient1= new Patient();
        logger.info("Creating new patient with ");
        patient1.setPatientFirstNames("Muhammad Ali");
        patient1.setLastName("Jokhio");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("03/10/1982"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MSH11111");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("Vero Insurance");
        patient1.setPatientEmail("muhammad.ali@mail.com");
        patient1.setPatientPhoneNumber("09-5514731");

        patient1.setPatientAddress(new Address("8 Daphne Road",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.YES);

        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));

        patientService.registerOrEditPatient(patient1);

        logger.info("Saved patient with id- "+patient1.getId());

        //Let this patient be registered at: SpecSaver
        Practice practice1= practiceService.getPracticeById(1);
        practice1.addRegisteredPatient(patient1);

        assert patient1.getRegisteredAt().getPracticeName()== "Spec Savers";
        assert practice1.getRegisteredPatients().contains(patient1);

        //Now removing the patient:
        practice1.removeRegisteredPatient(patient1);
        //this patient should not be in the list:
        assert practice1.getRegisteredPatients().contains(patient1) == false;
        assert patient1.getRegisteredAt() == null;

    }

}