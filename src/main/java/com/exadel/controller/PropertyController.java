package com.exadel.controller;

import com.exadel.mongodb.model.Property;
import com.exadel.mongodb.service.api.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/property")
public class PropertyController {


    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAll() {
        return new ResponseEntity<List<Property>>(propertyService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findById(@PathVariable("id") String id) {
        return new ResponseEntity<Property>(propertyService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/landlordId/{landlordId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findByLandlordId(@PathVariable("landlordId") String landlordId) {
        return new ResponseEntity<List<Property>>(propertyService.findByLandlordId(landlordId), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity create(@RequestBody Property property) {
        return new ResponseEntity<Property>(propertyService.save(property), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity update(@RequestBody Property property) {
        return new ResponseEntity<Property>(propertyService.save(property), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (propertyService.exists(id)) {
            propertyService.delete(id);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity(status);
    }

}
