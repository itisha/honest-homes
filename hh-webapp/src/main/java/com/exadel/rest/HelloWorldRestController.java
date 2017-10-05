package com.exadel.rest;

import com.exadel.model.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tsikhan Kuprevich
 * @since 10/5/2017
 */
@RestController
public class HelloWorldRestController {

    @RequestMapping("/")
    public String welcome() {//Welcome page, non-rest
        return "Welcome to RestTemplate Example.";
    }

    @RequestMapping("/hello/{user}")
    public Message message(@PathVariable String user) {//REST Endpoint.

        Message msg = new Message(user, "Hello " + user);
        return msg;
    }
}
