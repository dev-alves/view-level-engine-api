package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Node {

    private NodeTypeEnum type;
    private ConditionOperatorEnum operation;
    private Map<String, Object> arguments;
    private String onTrue;
    private String onFalse;
    private String set;
    private String next;

}
