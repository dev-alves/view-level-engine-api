package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.models.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RuleOperatorDTO {

    private String name;
    private NodeTypeEnum type;

    public static RuleOperatorDTO toDTO(Operation operation) {
        var dto = new RuleOperatorDTO();
        dto.name = operation.getName();
        dto.type = operation.getNodeType();
        return dto;
    }

    public static List<RuleOperatorDTO> toDTOS(List<Operation> entities) {
        return entities.stream()
                .map(RuleOperatorDTO::toDTO)
                .toList();
    }
}
