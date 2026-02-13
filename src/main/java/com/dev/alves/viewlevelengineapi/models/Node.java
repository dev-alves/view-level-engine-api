package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import jakarta.persistence.Embeddable;

import java.util.Map;

@Embeddable
public class Node {

    private NodeTypeEnum type;
    private String operation;
    private Map<String, Object> arguments;
    private String onTrue;
    private String onFalse;
    private String set;
    private String next;

}
