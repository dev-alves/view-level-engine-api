package com.dev.alves.viewlevelengineapi.models;

import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

    private NodeTypeEnum type;
    private ConditionOperatorEnum operation;
    private Argument arguments;
    private String onTrue;
    private String onFalse;
    private String set;
    private String next;

}
