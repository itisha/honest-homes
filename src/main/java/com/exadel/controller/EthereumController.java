package com.exadel.controller;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.ethereum.model.Sha256Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/sha256")
public class EthereumController {

    private final EthereumService ethereumService;

    @Autowired
    public EthereumController(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Sha256Hex>> getAll() {
        return new ResponseEntity<>(ethereumService.readFullFeedbackSha256HexList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity create(@RequestBody String Sha256Hex) {
        ethereumService.storeFeedbackSha256Hex(Sha256Hex);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
