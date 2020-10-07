package com.cattrax.domain.converters;

import com.cattrax.domain.Address;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Dell User on 13/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */

public class AddressConverter implements Converter<String, Address> {
    @Override
    public Address convert(String s) {
        return null;
    }
}
