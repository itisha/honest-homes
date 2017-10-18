package com.exadel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Configuration
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
    private final Environment environment;

    @Autowired
    public ApplicationConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public HttpService httpService() {
        return new HttpService(environment.getProperty("eth.host.url"));
    }

    @Bean
    public Credentials credentials() {
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(
                    environment.getProperty("eth.geth.account.password"),
                    environment.getProperty("eth.geth.account.file"));
        } catch (IOException | CipherException e) {
            LOGGER.error("Unable to load Ethereum credentials." + e.getMessage());
            throw new RuntimeException(e);
        }
        return credentials;
    }

    @Bean
    public Web3j web3j() {
        return Web3j.build(httpService());
    }
}
