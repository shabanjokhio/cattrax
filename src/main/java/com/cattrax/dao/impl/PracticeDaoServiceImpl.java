package com.cattrax.dao.impl;

import com.cattrax.dao.PracticeDaoService;
import com.cattrax.domain.Practice;
import com.cattrax.repositories.PracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PracticeDaoServiceImpl implements PracticeDaoService{
    PracticeRepository practiceRepository;
    @Autowired
    public void setPracticeRepository(PracticeRepository practiceRepository) {
        this.practiceRepository = practiceRepository;
    }

    @Override
    public List<Practice> listAll() {
        List<Practice> practices = new ArrayList<>();
        practiceRepository.findAll().forEach(practices::add);
        return practices;
    }

    @Override
    public Practice getById(Integer id) {
        return practiceRepository.findOne(id);
    }

    @Override
    public Practice saveOrUpdate(Practice practice) {
        return practiceRepository.save(practice);
    }

    @Override
    public void delete(Integer id) {
        practiceRepository.delete(id);
    }
}
