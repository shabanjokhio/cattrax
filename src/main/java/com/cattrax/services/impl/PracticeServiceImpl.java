package com.cattrax.services.impl;

import com.cattrax.dao.impl.PracticeDaoServiceImpl;
import com.cattrax.domain.Patient;
import com.cattrax.domain.Practice;
import com.cattrax.services.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class PracticeServiceImpl implements PracticeService{

    private PracticeDaoServiceImpl practiceDaoService;

    @Autowired
    public void setPracticeDaoService(PracticeDaoServiceImpl practiceDaoService) {
        this.practiceDaoService = practiceDaoService;
    }

    @Override
    public List<Practice> getAllPractices() {
        return practiceDaoService.listAll();
    }

    @Override
    public Practice getPracticeById(Integer practiceId) {
        return practiceDaoService.getById(practiceId);
    }

    @Override
    public Practice saveOrUpdatePractice(Practice practice) {
        return (Practice) practiceDaoService.saveOrUpdate(practice);
    }

    @Override
    public void deletePracticce(Integer practiceId) {
        practiceDaoService.delete(practiceId);
    }

    @Override
    public List<Patient> getRegisteredPatients(Practice practice) {
        return null;
    }


}
