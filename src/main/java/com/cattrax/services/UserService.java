package com.cattrax.services;

import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.domain.User;

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
public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer userId);
    User saveOrUpdateUser(User user);
    void deleteUser(Integer userId);

    List<Patient> getListOfPatientsReferred(User user);
    List<Practice> getListOfPracticesWorkIn(User user);

    /**
     *
     * @param patient
     * @param toPractice
     * @return
     */
    boolean transferPatient(Patient patient,  Practice toPractice);

}
