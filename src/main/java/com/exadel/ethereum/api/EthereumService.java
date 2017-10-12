package com.exadel.ethereum.api;

import com.exadel.mongodb.model.Sha256Hex;

import java.util.List;

public interface EthereumService {
    void storeFeedbackSha256Hex(String sha256Hex);

    List<Sha256Hex> readFullFeedbackSha256HexList();
}
