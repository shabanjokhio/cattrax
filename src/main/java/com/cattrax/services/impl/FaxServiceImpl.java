package com.cattrax.services.impl;

import com.cattrax.services.FaxService;
import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by shabanm on 5/04/2017.
 */

@Service
public class FaxServiceImpl implements FaxService{
    private  org.apache.log4j.Logger logger= Logger.getLogger(FaxServiceImpl.class);

    private String username;
    private String key;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean sendFax(String username, String key) {
        // Fax sending using clickSend API
        Client client = ClientBuilder.newClient();
        Entity payload = Entity.json(
                "{'file_url': " +
                        "'https://s3-ap-southeast-2.amazonaws.com/clicksend-api-downloads/_public/_examples/document.pdf', "
                        +" 'messages': [" +
                        " { " +
                        " 'source': 'cattrax', " +
                        "'to': '+61261111111',      " +
                        "'schedule': '1436874701',      " +
                        "'custom_string': 'custom_string',      " +
                        "'country': 'AU',      " +
                        "'from_email': 'will@smith.com'  " +
                        "  }," +
                        " {    " +
                        "  'source': 'Java',    " +
                        "  'to': '+61261111122',    " +
                        "  'schedule': '1436874701',     " +
                        " 'custom_string': 'custom_string',     " +
                        " 'country': 'AU',     " +
                        " 'from_email': 'john@doe.com'    " +
                        "} " +
                        "]" +
                        "}"
        );
        //username=mshaban, key= 4BB5C66A-5BDD-8D92-7C68-D8CA6F29F4F9
        //TODO: Following is the MockServer URL.
        // TODO: Replace this with Clisend PROD server for the production
        Response response = client.target("https://private-anon-4bda0cf0ae-clicksend.apiary-mock.com/" +
                "v3/fax/send?"+"username="+username+"&key="+key)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(payload);



        logger.info("status: " + response.getStatus());
        logger.info("headers: " + response.getHeaders());
        logger.info("body:" + response.readEntity(String.class));
        return true;
    }

    //TODO: unit testing.
    public Response getFaxDeliveryReceipt (String username, String key, String message_id){

    Client client = ClientBuilder.newClient();
    //TODO: replace ClickSend MOCKServer with PROD Server for CatTrax Production:
    Response response = client.target("https://private-anon-d4651bc00d-clicksend.apiary-mock.com/" +
            "v3/fax/receipts/"+message_id)
            .request(MediaType.TEXT_PLAIN_TYPE)
            .get();
        logger.info("status: " + response.getStatus());
        logger.info("headers: " + response.getHeaders());
        logger.info("body:" + response.readEntity(String.class));

        return response;
    }

}
