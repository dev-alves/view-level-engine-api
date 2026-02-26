package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Operation {

    @UUID
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private NodeTypeEnum nodeType;
    private boolean args;

    public Operation(NodeTypeEnum nodeType, String name,  boolean hasArgs) {
        this.nodeType = nodeType;
        this.name = name;
        this.args = hasArgs;
    }
}
