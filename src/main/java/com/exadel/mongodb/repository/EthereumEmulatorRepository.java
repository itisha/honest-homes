package com.exadel.mongodb.repository;

import com.exadel.mongodb.model.Sha256Hex;
import com.exadel.mongodb.repository.generic.Repository;

public interface EthereumEmulatorRepository extends Repository<Sha256Hex, String> {
}
