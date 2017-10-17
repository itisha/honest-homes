package com.exadel.ethereum;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.ethereum.contractwrapper.HashArray;
import com.exadel.mongodb.model.Sha256Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

@Service
public class EthereumServiceImpl implements EthereumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthereumServiceImpl.class);

    private HashArray contract;

    @Autowired
    public EthereumServiceImpl(Credentials credentials, Web3j web3j, Environment environment) {
        contract = HashArray.load(environment.getProperty("eth.geth.contract.address"), web3j, credentials, GAS_PRICE, GAS_LIMIT);
        try {
            if (!contract.isValid()) {
                LOGGER.error("For some reason the contract is not valid, but works anyway.");
            }
        } catch (IOException e) {
            LOGGER.error("Unable to connect to web3 node." + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void storeFeedbackSha256Hex(String sha256Hex) {
        validate(sha256Hex);
        LOGGER.info("Storing sha256 hash: storeFeedbackSha256Hex(\"" + sha256Hex + "\")");
        contract.addHash(new Utf8String(sha256Hex));
    }

    @Override
    public List<Sha256Hex> readFullFeedbackSha256HexList() {
        List<Sha256Hex> hexList = new LinkedList<>();

        try {
            Long hashesCount = contract.getHashesCount().get().getValue().longValue();
            if (hashesCount > 0) {
                hexList = new ArrayList<>(hashesCount.intValue());
                for (long i = 0; i < hashesCount; i++) {
                    Future<Utf8String> hash = contract.getHash(new Uint256(i));
                    hexList.add(new Sha256Hex(i, hash.get().getValue()));
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Unable to read from smart contract.");
            throw new RuntimeException("Unable to read from smart contract.", e);
        }

        return hexList;
    }

    private void validate(String sha256Hex) {
        if (StringUtils.isEmpty(sha256Hex)) {
            throw new RuntimeException("Must be a valid sha256 hash.");
        }
    }
}
