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
public class CreateRuleDTO {

    private int version;
    private String startNode;
    private StatusEnum statusEnum;
    private Map<String, NodeDTO> nodes;
    private Map<String, NodePositionDTO> positions;
    private List<RuleEdgeDTO> edges;

    public Rule toModel() {
        var rule = new Rule();
        rule.setStatus(this.statusEnum);
        rule.setNodes(this.nodes.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, nodes -> nodes.getValue().toModel()))
        );
        if (this.positions != null) {
            rule.setPositions(this.positions.entrySet().stream().collect(Collectors.toMap(
                    Map.Entry::getKey, position -> position.getValue().toModel()))
            );
        }
        if (this.edges != null) {
            rule.setEdges(this.edges.stream()
                    .map(RuleEdgeDTO::toModel)
                    .toList());
        }
        return rule;
    }

}
