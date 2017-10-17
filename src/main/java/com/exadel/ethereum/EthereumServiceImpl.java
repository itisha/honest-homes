package com.exadel.ethereum;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.ethereum.contractwrapper.HashArray;
import com.exadel.ethereum.model.Sha256Hex;
import com.exadel.mongodb.model.ContractDetails;
import com.exadel.mongodb.repository.EthereumContractRepository;
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

import java.math.BigInteger;
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
    private EthereumContractRepository ethereumContractRepository;

    @Autowired
    public EthereumServiceImpl(Environment environment, Credentials credentials, Web3j web3j, EthereumContractRepository ethereumContractRepository) {
        this.ethereumContractRepository = ethereumContractRepository;
        initContract(environment, credentials, web3j, ethereumContractRepository);
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

    private void initContract(Environment environment, Credentials credentials, Web3j web3j, EthereumContractRepository ethereumContractRepository) {
        String contractAddress = getDeployedContractAddress(environment, ethereumContractRepository);
        if (contractAddress == null) {
            contract = deployContract(web3j, credentials);
        } else {
            contract = HashArray.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);
        }
    }

    private String getDeployedContractAddress(Environment environment, EthereumContractRepository ethereumContractRepository) {
        String contractAddress = environment.getProperty("eth.geth.contract.address");
        if (contractAddress == null) {
            List<ContractDetails> all = ethereumContractRepository.findAll();
            if (all.size() == 1) {
                contractAddress = all.get(0).getContractAddress();
            }
            if (all.size() > 1) {
                throw new RuntimeException("Expected 1 contract record, but got " + all.size() + ". The contract should only be deployed only once.");
            }
        }
        return contractAddress;
    }

    private HashArray deployContract(Web3j web3j, Credentials credentials) {
        HashArray contract;
        Future<HashArray> deploy = HashArray.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT, BigInteger.ZERO);
        try {
            contract = deploy.get();
            ethereumContractRepository.save(new ContractDetails(contract.getContractAddress()));
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Error deploying contract. " + e.getMessage());
            throw new RuntimeException("Error deploying contract.", e);
        }
        return contract;
    }
}
