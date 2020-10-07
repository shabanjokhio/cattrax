package com.cattrax.services;

import com.cattrax.domain.*;
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

public class PatientServiceTest {
    private  org.apache.log4j.Logger logger= Logger.getLogger(PatientServiceTest.class);

    @Autowired   private PatientService patientService;
    @Autowired   private UserService userService;
    @Autowired   private PracticeService practiceService;
    @Autowired   private CattraxReferralService referralService;

    @Test
    public void testListAllPatients(){
        List<Patient> patients = patientService.listPatients();
        logger.info(patients.size());
        assert patients.size() > 0 ;
    }

    @Test
    public void testGetPatientById(){
        Patient p = patientService.getPatientById(1);
        assert p != null;
        assert p.getId() == 1;
    }

    @Test
    public void testRegisterPatient(){
        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("Muhammad");
        patient1.setLastName("Idrees");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("DFSD23234");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.COOK_ISLAND_MAORI);
        patient1.setHealthInsuranceProvider("Vero Insurance ");
        patient1.setPatientEmail("mij@mail.com");
        patient1.setPatientPhoneNumber("09-55023423");

        patient1.setPatientAddress(new Address("1/24 Pine Street",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);

        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));
        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());

        assert patient1.getId() == 4;
        logger.debug("end testRegisterPatient()");
    }
    @Test
    public void testDeletePatient(){
        patientService.deletePatient(1);
        assert patientService.getPatientById(1) == null;
    }

    //test passed:
    @Test
    public void testAddAndRemoveReferralsForPatient(){
        //Steps to replicate:
        /**
         * Create a patient object.
         * get referrals from the referral service from bootstrap:
         * add referral to the patient object,
         * Save the patient object
         * retrieve patient object. check if it is not null
         * get referrals related to the patient.
         * check if patient has same number of the referrals.
         */
        List<CattraxReferral> referrals = referralService.listReferrals();

        Patient firstPatient = new Patient();
        firstPatient.setPatientFirstNames("Blah Blah");
        firstPatient.setLastName("Xyz Xyz");
        firstPatient.setPatientEmail("mshaban@mail.com");
        firstPatient.setPatientNHI("sdf23423");

        try {
            firstPatient.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        firstPatient.setPatientGender(Gender.MALE);
        firstPatient.setPatientEthnicity(Ethnicity.CHINESE);
        firstPatient.setPatientSmoking(SmokingAnswer.YES);
        firstPatient.setPatientPhoneNumber("1234234");
        Address add= new Address();
        add.setPostCode(1041);

        firstPatient.setPatientAddress(add);

        CattraxReferral referral1= referrals.get(0);
        CattraxReferral referral2 = referrals.get(1);

        firstPatient.addReferral(referral1);
        firstPatient.addReferral(referral2);

        Patient savedPatient = patientService.registerOrEditPatient(firstPatient);

        // Patient savedPatient = patientService.getPatientById(1);

        assert savedPatient.getId() != null;
        assert savedPatient.getReferrals() != null;
        assert savedPatient.getReferrals().size() == 2;
        //test removal process:

        Patient patient11= patientService.getPatientById(1);

        patient11.removeReferral(referral1);
        assert patient11.getReferrals().size() == 1;
        assert referral1.getPatientReferred()  != patient11;

    }
    @Test
    public void testSearchPatientByNamesAndDoB() throws ParseException{
        String dob = "02/10/1980";
        Patient patient = patientService.searchPatientByNamesAndDoB("Muhammad",
                "Shaban", dob);

        assert patient.getPatientFirstNames().equals("Muhammad") == true;
        assert patient.getLastName().equals("Shaban") == true;

    }

    @Test
    public void testGetPracticeThisPatientIsRegisteredAt(){
       //TODO: associate the practice to the patient at the CattraxLoader class
       //Practice practice= patientService.getPracticeThisPatientIsRegisteredAt(3);
       //Steps to replicate: create a patient, create a practice, register the patient at the practice, test using assertions:
        //Step 1: Create patient:
        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("Maitham");
        patient1.setLastName("Habib");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/10/1979"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MTH1234");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("Vero Insurance ");
        patient1.setPatientEmail("mhabib@mail.com");
        patient1.setPatientPhoneNumber("09-5502342323");

        patient1.setPatientAddress(new Address("Botany",
                "Botany",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);

        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        Practice practice = practiceService.getPracticeById(1);

        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());


        Practice practice1 = patient1.getRegisteredAt();

       assert practice1.getId() == 1;
       assert practice1.getPracticeName() == "Spec Savers";

        Practice savedPractice = patientService.getPracticeThisPatientIsRegisteredAt(patient1.getId());
        assert savedPractice.getPracticeName() == "Spec Savers";

       logger.debug("Test successful...");
    }
    @Test
    public void testGetUserThisPatientIsReferredBy(){
        //steps to replicate: create user, create patient, refer patient by user and verify by assertions:
        //TODO: QED.
        User allan= new User();
        allan.setUserTitle(Title.DR);
        allan.setFirstNames("Allan");
        allan.setLastName("Smith");
        allan.setPhoneNumber("09-12341543");
        allan.setUserEmail("allan@specsaver.com");
        allan.setUserPassword("allan-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("8-Daphne Road, Mt Eden");
        jamesAddress.setPostCode(1042);
        allan.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(2));

        allan.setWorksIn(jamesWorksIn);
        Registration allanReg = new Registration();
        allanReg.setRegNumber("1231111");
        allanReg.setRegType(RegistrationType.GP);
        allan.setProfessionalRegistration(allanReg);

        logger.debug("Saving user with name:\t"+ allan.getFirstNames());
        userService.saveOrUpdateUser(allan);

        //create new patient.
        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("John");
        patient1.setLastName("Smith");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1981"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MSHSD3715");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.NIUEAN);
        patient1.setHealthInsuranceProvider("Other Insurance");
        patient1.setPatientEmail("muhammad.shaban@mail.com");
        patient1.setPatientPhoneNumber("09-5504714");

        patient1.setPatientAddress(new Address("5/60 Grange Road",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);
        patient1.setReferredBy(allan);
        //patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(allan);

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(2));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());

       //User getUserThisPatientIsReferredBy(Integer patientId); inside patient service

        User savedUser= patientService.getUserThisPatientIsReferredBy(patientService.getPatientById(4).getId());

        assert savedUser != null;
        assert savedUser.getFirstNames() == "Allan";

    }
    @Test
    public void testGetUserThisPatientIsRegisteredBy(){
        //steps to replicate: create user, create patient, refer patient by user and verify by assertions:
        //TODO: QED.
        User john= new User();
        john.setUserTitle(Title.PROF);
        john.setFirstNames("John");
        john.setLastName("Hosking");
        john.setPhoneNumber("09-112233445");
        john.setUserEmail("allan@opsm.com");
        john.setUserPassword("john-secret-password");
        Address jamesAddress = new Address();
        jamesAddress.setAddressLine1("");
        jamesAddress.setPostCode(1010);
        john.setUserAddress(jamesAddress);

        List<Practice> jamesWorksIn = new ArrayList<>();
        jamesWorksIn.add(practiceService.getPracticeById(1));

        john.setWorksIn(jamesWorksIn);
        Registration johnReg = new Registration();
        johnReg.setRegNumber("1231111");
        johnReg.setRegType(RegistrationType.OPHTHALMOGIST);
        john.setProfessionalRegistration(johnReg);

        logger.debug("Saving user with name:\t"+ john.getFirstNames());
        userService.saveOrUpdateUser(john);

        //create new patient.
        Patient patient1= new Patient();
        logger.info("Saving patient with");
        patient1.setPatientFirstNames("Donald");
        patient1.setLastName("Trump");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1950"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("DNLD1112");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        patient1.setHealthInsuranceProvider("AMI Insurance");
        patient1.setPatientEmail("dtrump@mail.com");
        patient1.setPatientPhoneNumber("09-51111111");

        patient1.setPatientAddress(new Address("5/60 Grange Road",
                " Mount Eden",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);
        patient1.setReferredBy(john);
        patient1.setRegisteredBy(john);

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());

        //User getUserThisPatientIsReferredBy(Integer patientId); inside patient service

        User savedUser= patientService.getUserThisPatientIsRegisteredBy(patientService.getPatientById(4).getId());

        assert savedUser != null;
        assert savedUser.getFirstNames() == "John";
        assert savedUser.getLastName() == "Hosking";
        logger.info("Test passed... ");

    }
    @Test
    public void testSearchPatientByFirstNames(){
        logger.info("testSearchPatientByFirstNames()");
        logger.info("create patients with the same name and test the method");

        Patient patient1= new Patient();
        logger.info("Saving patient with");
        patient1.setPatientFirstNames("Donald");
        patient1.setLastName("Trump");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1950"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("DNLD122212");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        patient1.setHealthInsuranceProvider("AMI Insurance");
        patient1.setPatientEmail("dtrump1@mail.com");
        patient1.setPatientPhoneNumber("09-51111111");

        patient1.setPatientAddress(new Address("6/60 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);
        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());


        Patient patient2= new Patient();
        logger.info("Saving patient with");
        patient2.setPatientFirstNames("Donald");
        patient2.setLastName("Duck");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1960"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient2.setPatientNHI("DNLD1223333");
        patient2.setPatientGender(Gender.FEMALE);
        patient2.setPatientEthnicity(Ethnicity.CHINESE);
        patient2.setHealthInsuranceProvider("AMI Insurance");
        patient2.setPatientEmail("dtrump2@mail.com");
        patient2.setPatientPhoneNumber("09-51111122");

        patient2.setPatientAddress(new Address("6/60 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.NO);
        patient2.setReferredBy(userService.getUserById(2));
        patient2.setRegisteredBy(userService.getUserById(2));

        //Practice p = new Practice();
        patient2.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient2);
        logger.info("Saved patient with id- "+patient2.getId());


        Patient patient3= new Patient();
        logger.info("Saving patient with");
        patient3.setPatientFirstNames("Muhammad");
        patient3.setLastName("Shaban");
        try {
            patient3.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1950"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient3.setPatientNHI("DDD222");
        patient3.setPatientGender(Gender.MALE);
        patient3.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        patient3.setHealthInsuranceProvider("AMI Insurance");
        patient3.setPatientEmail("dtrump1@mail.com");
        patient3.setPatientPhoneNumber("09-51111111");

        patient3.setPatientAddress(new Address("6/60 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient3.setPatientSmoking(SmokingAnswer.NO);
        patient3.setReferredBy(userService.getUserById(1));
        patient3.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient3.setRegisteredAt(practiceService.getPracticeById(3));
        patientService.registerOrEditPatient(patient3);
        logger.info("Saved patient with id- "+patient3.getId());

       // List<Patient> searchPatientByFirstNames(String firstNames);

        List<Patient> patientsWithFirstNameDonald=patientService.searchPatientByFirstNames("Donald");

        assert patientsWithFirstNameDonald != null;
        assert patientsWithFirstNameDonald.size() == 2;

        logger.info("Test passed... ");
    }
    @Test
    public void testSearchPatientByLastName(){
        logger.info("testSearchPatientByLastName()");
        logger.info("create patients with the same last Name and test the method");

        Patient patient1= new Patient();
        logger.info("Saving patient with");
        patient1.setPatientFirstNames("Muhammad");
        patient1.setLastName("Ali");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/12/1961"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MALI001312");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("AMI Insurance");
        patient1.setPatientEmail("mali@mail.com");
        patient1.setPatientPhoneNumber("09-5112222");

        patient1.setPatientAddress(new Address("6/61 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);
        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());


        Patient patient2= new Patient();
        logger.info("Saving patient with");
        patient2.setPatientFirstNames("Ahmed");
        patient2.setLastName("Ali");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1960"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient2.setPatientNHI("AHALI001");
        patient2.setPatientGender(Gender.MALE);
        patient2.setPatientEthnicity(Ethnicity.NIUEAN);
        patient2.setHealthInsuranceProvider("AMI Insurance");
        patient2.setPatientEmail("aali2@mail.com");
        patient2.setPatientPhoneNumber("09-51111122");

        patient2.setPatientAddress(new Address("6/60 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.NO);
        patient2.setReferredBy(userService.getUserById(2));
        patient2.setRegisteredBy(userService.getUserById(2));

        //Practice p = new Practice();
        patient2.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient2);
        logger.info("Saved patient with id- "+patient2.getId());

        Patient patient3= new Patient();
        logger.info("Saving patient with");
        patient3.setPatientFirstNames("John");
        patient3.setLastName("Ali");
        try {
            patient3.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/11/1950"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient3.setPatientNHI("DDD222");
        patient3.setPatientGender(Gender.MALE);
        patient3.setPatientEthnicity(Ethnicity.NZ_EUROPEAN);
        patient3.setHealthInsuranceProvider("AMI Insurance");
        patient3.setPatientEmail("dtrump1@mail.com");
        patient3.setPatientPhoneNumber("09-51111111");

        patient3.setPatientAddress(new Address("6/60 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient3.setPatientSmoking(SmokingAnswer.NO);
        patient3.setReferredBy(userService.getUserById(1));
        patient3.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient3.setRegisteredAt(practiceService.getPracticeById(3));
        patientService.registerOrEditPatient(patient3);
        logger.info("Saved patient with id- "+patient3.getId());

        // List<Patient> searchPatientByFirstNames(String firstNames);

        List<Patient> patientsWithLastNameAli=patientService.searchPatientByLastName("Ali");

        assert patientsWithLastNameAli != null;
        assert patientsWithLastNameAli.size() == 3;

        logger.info("Test passed... ");

    }
    @Test
    public void testSearchPatientByDOB(){
        logger.info("testSearchPatientByDOB()");

        Patient patient1= new Patient();
        logger.info("Saving patient with");
        patient1.setPatientFirstNames("Muhammad");
        patient1.setLastName("Ali");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("MALI001312");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("AMI Insurance");
        patient1.setPatientEmail("mali@mail.com");
        patient1.setPatientPhoneNumber("09-5112222");

        patient1.setPatientAddress(new Address("6/61 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);
        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        //Practice p = new Practice();
        patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient1);
        logger.info("Saved patient with id- "+patient1.getId());


        Patient patient2= new Patient();
        logger.info("Saving patient with");
        patient2.setPatientFirstNames("John");
        patient2.setLastName("Smith3");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient2.setPatientNHI("JSMITH001");
        patient2.setPatientGender(Gender.MALE);
        patient2.setPatientEthnicity(Ethnicity.NIUEAN);
        patient2.setHealthInsuranceProvider("AMI Insurance");
        patient2.setPatientEmail("smmith@mail.com");
        patient2.setPatientPhoneNumber("09-51111122");

        patient2.setPatientAddress(new Address("5/62 Grange Road", " Mount Eden",   "Balmoral","", "",           1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.NO);
        patient2.setReferredBy(userService.getUserById(2));
        patient2.setRegisteredBy(userService.getUserById(2));

        //Practice p = new Practice();
        patient2.setRegisteredAt(practiceService.getPracticeById(1));
        patientService.registerOrEditPatient(patient2);
        logger.info("Saved patient with id- "+patient2.getId());


        // List<Patient> searchPatientByFirstNames(String firstNames);

        List<Patient> searchResult= null;
        try {
            searchResult = patientService.searchPatientByDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/10/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert searchResult != null;
        assert searchResult.size() == 3;
        //assert searchResult.get(0).getPatientDOB().toString().equals("02/10/1980");

        logger.info("Test passed... ");

    }

    @Test
    public void testSearchPatientsByUserRegisteredBy() {
        // Method to test: List<Patient> searchPatientsByUserRegisteredBy(User registeredBy);//find list of patients registered by this user:
        // Steps to replicate: 1. create Patients, create User, set patients referred by User,
        logger.info("testSearchPatientsByUserRegisteredBy()...");
        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("Maitham");
        patient1.setLastName("Habib");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/02/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient1.setPatientNHI("MTH11111");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("Adam Insurance ");
        patient1.setPatientEmail("mhabib2@mail.com");
        patient1.setPatientPhoneNumber("09-5512342323");

        patient1.setPatientAddress(new Address("Botany",
                "Botany",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.YES);
        patient1.setReferredBy(userService.getUserById(1)); // User name is first Name: "James", "McK";
        patient1.setRegisteredBy(userService.getUserById(1));
        Practice practice = practiceService.getPracticeById(1);

        //patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patient1.setRegisteredAt(practice);
        patientService.registerOrEditPatient(patient1);
        //logger.info("Saved patient with id- "+patient1.getId());

        Patient patient2= new Patient();
        patient2.setPatientFirstNames("Sonni");
        patient2.setLastName("Neilson");
        try {
            patient2.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("01/02/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        patient2.setPatientNHI("SONN12323");
        patient2.setPatientGender(Gender.MALE);
        patient2.setPatientEthnicity(Ethnicity.CHINESE);
        patient2.setHealthInsuranceProvider("some Insurance ");
        patient2.setPatientEmail("sonni@mail.com");
        patient2.setPatientPhoneNumber("09-523333423");

        patient2.setPatientAddress(new Address("West Auckland",
                "West Auckland",
                "West Auckland","", "",
                2025, "Auckland", "NZ", AddressType.PHYSICAL));
        patient2.setPatientSmoking(SmokingAnswer.YES);
        patient2.setReferredBy(userService.getUserById(1)); // User name is first Name: "James", "McK";
        patient2.setRegisteredBy(userService.getUserById(1));
        Practice practice2 = practiceService.getPracticeById(1);

        //patient1.setRegisteredAt(practiceService.getPracticeById(1));
        patient2.setRegisteredAt(practice2);
        patientService.registerOrEditPatient(patient2);
        //These two patients are registered by James. So if we search the patients registered By James,
        // it should return an ArrayList of size 2
        List<Patient> patientsRegisteredByJames =
                patientService.searchPatientsByUserRegisteredBy(userService.getUserById(1));

        assert patientsRegisteredByJames != null;
        assert patientsRegisteredByJames.size() == 2;

        logger.info("Test Passed...");
    }

    //Test passed: dated: 16/4/2017. 7:35 pm
    @Test
    public void testSearchPatientsByPracticeRegisteredAt(){
        //Method under test: List<Patient> searchPatientsByPracticeRegisteredAt(Practice registeredAt); of PatientService class:
        //steps to replicate: create a patient, register it at practice p1 (add patient to practice),
        // create patient p2, register it at practice p2.

        Patient patient1= new Patient();
        logger.info("Saving patient with ");
        patient1.setPatientFirstNames("Danzil");
        patient1.setLastName("Pinto");
        try {
            patient1.setPatientDOB(new SimpleDateFormat("dd/mm/yyyy").parse("02/11/1980"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patient1.setPatientNHI("DDDPP123");
        patient1.setPatientGender(Gender.MALE);
        patient1.setPatientEthnicity(Ethnicity.INDIAN);
        patient1.setHealthInsuranceProvider("Some Insurance");
        patient1.setPatientEmail("muhammad.shaban222@mail.com");
        patient1.setPatientPhoneNumber("09-5504111");

        patient1.setPatientAddress(new Address("81 Ferndown Avenue Papatoetoe",
                " Papatoetoe",
                "Balmoral","", "",
                1024, "Auckland", "NZ", AddressType.PHYSICAL));
        patient1.setPatientSmoking(SmokingAnswer.NO);

        patient1.setReferredBy(userService.getUserById(1));
        patient1.setRegisteredBy(userService.getUserById(1));

        Practice practice= practiceService.getPracticeById(1);
        patient1.setRegisteredAt(practice);

        patientService.registerOrEditPatient(patient1); //lets save this patient:

        practice.addRegisteredPatient(patient1);

        practiceService.saveOrUpdatePractice(practice);


        List<Patient> patientRegisteredAtPracticeWithId1= patientService.
                searchPatientsByPracticeRegisteredAt(practice);

        assert patientRegisteredAtPracticeWithId1 != null;
        assert patientRegisteredAtPracticeWithId1.size() == 1;
        assert patient1.getRegisteredAt().getPracticeName().equals("Spec Savers");
        assert practice.getRegisteredPatients().contains(patient1);

        logger.info("Test Passed...");
    }
}
