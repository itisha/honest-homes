package com.exadel;

import com.exadel.mongodb.model.Feedback;
import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.model.User;
import com.exadel.mongodb.service.api.FeedbackService;
import com.exadel.mongodb.service.api.PropertyService;
import com.exadel.mongodb.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicFlowTest {

    @Autowired
    UserService userService;

    @Autowired
    PropertyService propertyService;

    @Autowired
    FeedbackService feedbackService;

    @Test
    public void basicIntegrationTest() throws Exception {
        /*
        1. Create Landlord.
        2. Create Property.
        => Make sure that property id is added to landlord.
        3. Create Tenant.
        4. Add visited property to tenant.
        5. Tenant adding Feedback on visited Property.
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

        //create tenant:
        User tenant = new User();
        tenant.setFirstName("Tenant" + Instant.now());
        tenant.setLastName("Renting");
        tenant = userService.save(tenant);

        //add visited property to tenant
        tenant.addOwnedPropertyId(property.getId());
        tenant.addVisitedPropertyId(property.getId());
        tenant = userService.save(tenant);

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
        assertEquals(feedback.getAuthorId(), tenant.getId());

        //todo update for methods to search by hash
    }
}
