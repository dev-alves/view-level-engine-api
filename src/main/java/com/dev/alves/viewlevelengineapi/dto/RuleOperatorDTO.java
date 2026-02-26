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

    private String id;
    private String name;
    private NodeTypeEnum type;
    private boolean hasArgs;

    public static RuleOperatorDTO toDTO(Operation operation) {
        var dto = new RuleOperatorDTO();
        dto.id = operation.getId();
        dto.name = operation.getName();
        dto.type = operation.getNodeType();
        dto.hasArgs = operation.isArgs();
        return dto;
    }

    public static List<RuleOperatorDTO> toDTOS(List<Operation> entities) {
        return entities.stream()
                .map(RuleOperatorDTO::toDTO)
                .toList();
    }
}
