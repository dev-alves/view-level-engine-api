package com.dev.alves.viewlevelengineapi.repositories;

import com.dev.alves.viewlevelengineapi.models.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleRepository extends MongoRepository<Rule, Long> {

}
