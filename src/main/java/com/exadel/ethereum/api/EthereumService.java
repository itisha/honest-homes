package com.exadel.ethereum.api;

import com.exadel.ethereum.model.Sha256Hex;
import com.exadel.mongodb.model.Feedback;

import java.util.List;

public interface EthereumService {
    void storeFeedbackSha256Hex(Feedback feedback);

    List<Sha256Hex> readFullFeedbackSha256HexList();
}
