package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.models.Argument;
import com.dev.alves.viewlevelengineapi.models.Node;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDTO {

    @NotNull
    private NodeTypeEnum type;

    @NotNull
    private ConditionOperatorEnum operation;

    private Argument arguments;
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

    public static NodeDTO toDTO(Node model) {
        var nodeDTO = new NodeDTO();
        nodeDTO.type = model.getType();
        nodeDTO.operation = model.getOperation();
        nodeDTO.arguments = model.getArguments();
        nodeDTO.onTrue = model.getOnTrue();
        nodeDTO.onFalse = model.getOnFalse();
        nodeDTO.set = model.getSet();
        return nodeDTO;
    }

}
