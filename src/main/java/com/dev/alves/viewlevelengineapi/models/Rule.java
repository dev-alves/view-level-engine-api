package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Document
public class Rule {

    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private StatusEnum status;
    private Map<String, Node> nodes;

}
