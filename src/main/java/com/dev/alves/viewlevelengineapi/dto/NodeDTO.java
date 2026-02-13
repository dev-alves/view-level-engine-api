package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NodeDTO {

    private NodeTypeEnum type;
    private String operation;
    private Map<String, Object> arguments;
    private String onTrue;
    private String onFalse;
    private String set;
    private String next;

}
