package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.models.NodePosition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodePositionDTO {

    private Integer x;
    private Integer y;

    public NodePosition toModel() {
        var nodePosition = new NodePosition();
        nodePosition.setX(this.x);
        nodePosition.setY(this.y);
        return nodePosition;
    }

    public static NodePositionDTO toDTO(NodePosition model) {
        var nodePositionDTO = new NodePositionDTO();
        nodePositionDTO.x = model.getX();
        nodePositionDTO.y = model.getY();
        return nodePositionDTO;
    }

}
