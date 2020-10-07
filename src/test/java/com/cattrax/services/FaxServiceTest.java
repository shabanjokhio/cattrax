package com.cattrax.services;

import com.cattrax.services.impl.FaxServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shabanm on 5/04/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@ComponentScan(basePackages = {"com.cattrax"})
public class FaxServiceTest {

    private  org.apache.log4j.Logger logger= Logger.getLogger(FaxServiceTest.class);

    //@Autowired
    private FaxServiceImpl faxService = new FaxServiceImpl();

    public FaxService getFaxService() {
        return faxService;
    }

    public void setFaxService(FaxServiceImpl faxService) {
        this.faxService = faxService;
    }

    @Test
    public void testSendFax(){
        String username="mshaban" ;
        String key= "4BB5C66A-5BDD-8D92-7C68-D8CA6F29F4F9";

        assert faxService.sendFax(username,key) == true;

        //                "message_id":"5106363A-AF38-4FB9-AF0E-B5FD38F62BDC",

    }

    @Test
    public void testGetFaxDeliveryReceipt(){
        String username="mshaban" ;
        String key= "4BB5C66A-5BDD-8D92-7C68-D8CA6F29F4F9";
        String message_id="5106363A-AF38-4FB9-AF0E-B5FD38F62BDC";

        assert faxService.getFaxDeliveryReceipt(username,key,message_id) != null;

        assert true;
    }
}
