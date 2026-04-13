package com.dev.alves.viewlevelengineapi.dto;

import com.dev.alves.viewlevelengineapi.models.RuleEdge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleEdgeDTO {

    private String id;
    private String source;
    private String target;
    private String sourceHandle;

    public RuleEdge toModel() {
        var ruleEdge = new RuleEdge();
        ruleEdge.setId(this.id);
        ruleEdge.setSource(this.source);
        ruleEdge.setTarget(this.target);
        ruleEdge.setSourceHandle(this.sourceHandle);
        return ruleEdge;
    }

    public static RuleEdgeDTO toDTO(RuleEdge model) {
        var ruleEdgeDTO = new RuleEdgeDTO();
        ruleEdgeDTO.id = model.getId();
        ruleEdgeDTO.source = model.getSource();
        ruleEdgeDTO.target = model.getTarget();
        ruleEdgeDTO.sourceHandle = model.getSourceHandle();
        return ruleEdgeDTO;
    }

}
