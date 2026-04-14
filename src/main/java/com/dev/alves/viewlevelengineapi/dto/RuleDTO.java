package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class RuleDTO {

    private String id;
    private String startNode;
    private StatusEnum status;
    private Map<String, NodeDTO> nodes;
    private Map<String, NodePositionDTO> positions;
    private List<RuleEdgeDTO> edges;

    public static RuleDTO toDTO(Rule model) {
        var ruleDTO = new RuleDTO();
        ruleDTO.setId(model.getId());
        ruleDTO.startNode = model.getStartNode();
        ruleDTO.status = model.getStatus();
        ruleDTO.nodes = model.getNodes().entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, n -> NodeDTO.toDTO(n.getValue())
        ));
        if (model.getPositions() != null) {
            ruleDTO.positions = model.getPositions().entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getKey, position -> NodePositionDTO.toDTO(position.getValue())
            ));
        }
        if (model.getEdges() != null) {
            ruleDTO.edges = model.getEdges().stream()
                    .map(RuleEdgeDTO::toDTO)
                    .toList();
        }
        return ruleDTO;
    }

}
