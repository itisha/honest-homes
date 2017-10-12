package com.exadel.ethereum;

import com.exadel.ethereum.api.EthereumService;
import com.exadel.mongodb.model.Sha256Hex;
import com.exadel.mongodb.repository.EthereumEmulatorRepository;
import com.exadel.mongodb.service.api.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EthereumEmulatorServiceImpl extends AbstractService<Sha256Hex, String> implements EthereumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthereumEmulatorServiceImpl.class);

    public EthereumEmulatorServiceImpl(EthereumEmulatorRepository repository) {
        super(repository);
    }

    @Override
    public void storeFeedbackSha256Hex(String sha256Hex) {
        validate(sha256Hex);
        LOGGER.info("Storing sha256 hash: storeFeedbackSha256Hex(\"" + sha256Hex + "\")");
        repository.save(new Sha256Hex(sha256Hex));
        //todo
    }

    @Override
    public List<Sha256Hex> readFullFeedbackSha256HexList() {
        return repository.findAll();
        //todo
    }

    private void validate(String sha256Hex) {
        if (StringUtils.isEmpty(sha256Hex)) {
            throw new RuntimeException("Must be a valid sha256 hash.");
        }
        //todo
    }
}
