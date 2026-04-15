package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class TreeDTO {

    @NotBlank
    private String startNode;

    @NotNull
    private StatusEnum statusEnum;

    @Valid
    private Map<String, NodeDTO> nodes;

    @Valid
    private Map<String, NodePositionDTO> positions;

    @Valid
    private List<RuleEdgeDTO> edges;

    public Rule toModel() {
        var rule = new Rule();
        bindToModel(rule);
        return rule;
    }

    public void bindToModel(Rule rule) {
        rule.setStartNode(this.startNode);
        rule.setStatus(this.statusEnum);
        rule.setNodes(this.nodes.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, node -> node.getValue().toModel()))
        );
        rule.setPositions(this.positions == null ? null : this.positions.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, position -> position.getValue().toModel()))
        );
        rule.setEdges(this.edges == null ? null : this.edges.stream()
                .map(RuleEdgeDTO::toModel)
                .toList());
    }

}
