package com.exadel;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.ethereum.model.Sha256Hex;
import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.model.User;
import com.exadel.mongodb.service.api.FeedbackService;
import com.exadel.mongodb.service.api.PropertyService;
import com.exadel.mongodb.service.api.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//The test will fail unless local Geth node is configured correctly
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicFlowTest {

    @Autowired
    UserService userService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    EthereumService ethereumService;

    @Test
    public void basicIntegrationTest() throws Exception {
        /*
        1. Create Landlord.
        2. Create Property.
        => Make sure that property id is added to landlord.
        3. Create Tenant.
        4. Add visited property to tenant.
        5. Tenant adding Feedback on visited Property.
        6. Verify hash from ethereum blockchain.
         */

        //create landlord:
        User landLord = new User();
        landLord.setFirstName("Land_" + Instant.now());
        landLord.setLastName("Lord");
        landLord = userService.save(landLord);
        assertNotNull(landLord.getId());

        //create property:
        Property property = new Property();
        property.setLandlordId(landLord.getId());
        property.setDescription("The House That Jack Built.");
        property.addImageUrl("http://img.hosting.com/images/property1.jpg");
        property.addImageUrl("http://img.hosting.com/images/property2.jpg");
        property = propertyService.save(property);
        assertNotNull(property.getId());
        //make sure property id added to landlord:
        landLord = userService.findOne(landLord.getId());
        assertEquals(landLord.getOwnedPropertyIds().get(0), property.getId());
        assertEquals(landLord.getVisitedPropertyIds().size(), 0);

        //create tenant:
        User tenant = new User();
        tenant.setFirstName("Tenant" + Instant.now());
        tenant.setLastName("Renting");
        tenant = userService.save(tenant);

        //add visited property to tenant
        tenant.addVisitedPropertyId(property.getId());
        tenant = userService.save(tenant);
        assertEquals(tenant.getVisitedPropertyIds().size(), 1);
        assertEquals(tenant.getOwnedPropertyIds().size(), 0);

        //add feedback to the visited property:
        Feedback feedback = new Feedback();
        feedback.setAuthorId(tenant.getId());
        feedback.setEntityId(property.getId());
        feedback.setDescription("Nice cozy place!");
        feedback.setScore(5);
        feedback = feedbackService.save(feedback);

        Property foundProperty = propertyService.findAll().get(0);
        List<Feedback> feedbackList = feedbackService.findByEntityId(foundProperty.getId());
        assertEquals(feedbackList.size(), 1);
        assertEquals(feedback.getEntityId(), property.getId());
        assertEquals(feedback.getAuthorId(), tenant.getId());

        //hash verification
        List<Sha256Hex> sha256List = ethereumService.readFullFeedbackSha256HexList();
        Gson gson = new GsonBuilder().create();
        sha256List.forEach(sha -> {
            Feedback fb = feedbackService.findOne(sha.getFeedbackId());
            assertNotNull(fb);
            String json = gson.toJson(fb);
            assertEquals(sha.getSha256Hex(), DigestUtils.sha256Hex(json));
        });
    }
}
