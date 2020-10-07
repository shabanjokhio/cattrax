package com.cattrax.services;

import javax.ws.rs.core.Response;
/**
 * Created by shabanm on 5/04/2017.
 */

public interface FaxService {

    boolean sendFax(String username, String key);
    Response getFaxDeliveryReceipt (String username, String key, String messageId);

}
