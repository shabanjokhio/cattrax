package com.cattrax.services.impl;

import com.cattrax.dao.impl.UserDaoServiceImpl;
import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;
import com.cattrax.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl implements UserService{

    private UserDaoServiceImpl userDaoService;
    @Autowired
    public void setUserDaoService(UserDaoServiceImpl userDaoService) {
        this.userDaoService = userDaoService;
    }


    @Override
    public List<User> getAllUsers() {
        return userDaoService.listAll();
    }

    @Override
    public User getUserById(Integer userId) {
        return userDaoService.getById(userId);
    }

    @Override
    public User saveOrUpdateUser(User user) {
        return userDaoService.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDaoService.delete(userId);
    }

    @Override
    public List<Patient> getListOfPatientsReferred(User user) {
        return userDaoService.getById(user.getId()).getPatientsReferred();
    }
    @Override
    public List<Practice> getListOfPracticesWorkIn(User user) {
        return userDaoService.getById(user.getId()).getWorksIn();
    }

    /**
     *
     * @param patient
     * @param toPractice
     * @return
     */
    @Override
    public boolean transferPatient(Patient patient, Practice toPractice) {
        /**
         * Steps:
         * 1. Get the current practice this patient is registerdAt
         * 2. Remove this patient from the current practice
         * 3. Add this patient to the current practice.
          */
        Practice currentPractice = patient.getRegisteredAt();
        currentPractice.removeRegisteredPatient(patient);
        toPractice.addRegisteredPatient(patient);
        return true;
    }
}
