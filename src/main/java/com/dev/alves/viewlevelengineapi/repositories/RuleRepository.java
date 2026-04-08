package com.dev.alves.viewlevelengineapi.repositories;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RuleRepository extends MongoRepository<Rule, Long> {

    Rule findByStatus(StatusEnum statusEnum);
    List<Rule> findAllByStatus(StatusEnum statusEnum);

}
