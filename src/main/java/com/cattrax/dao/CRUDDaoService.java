package com.cattrax.dao;

import java.util.List;

/**
 * Created by Dell User on 21/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
public interface CRUDDaoService <T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate (T domainObject);

    void delete(Integer id);
}
