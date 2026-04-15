package com.dev.alves.viewlevelengineapi.repositories;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends MongoRepository<Rule, String> {

    Optional<Rule> findByStatus(StatusEnum statusEnum);
    List<Rule> findAllByStatus(StatusEnum statusEnum);

}
