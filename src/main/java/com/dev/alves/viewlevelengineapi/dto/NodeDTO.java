package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.models.Node;
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

    public Node toModel() {
        var node = new Node();
        node.setType(this.type);
        node.setOperation(this.operation);
        node.setArguments(this.arguments);
        node.setOnTrue(this.onTrue);
        node.setOnFalse(this.onFalse);
        node.setSet(this.set);
        return node;
    }

}
