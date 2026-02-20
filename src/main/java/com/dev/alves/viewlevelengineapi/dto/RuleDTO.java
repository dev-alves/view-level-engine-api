package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class RuleDTO {

    private String id;
    private String startNode;
    private StatusEnum status;
    private Map<String, NodeDTO> nodes;

    public static RuleDTO toDTO(Rule model) {
        var ruleDTO = new RuleDTO();
        ruleDTO.id = model.getId();
        ruleDTO.startNode = model.getStartNode();
        ruleDTO.status = model.getStatus();
        ruleDTO.nodes = model.getNodes().entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, n -> NodeDTO.toDTO(n.getValue())
        ));
        return ruleDTO;
    }

}
