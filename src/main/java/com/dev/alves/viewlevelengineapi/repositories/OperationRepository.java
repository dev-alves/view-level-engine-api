package com.dev.alves.viewlevelengineapi.repositories;

import com.dev.alves.viewlevelengineapi.models.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {
}
