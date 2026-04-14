package com.dev.alves.viewlevelengineapi.validators;

import com.dev.alves.viewlevelengineapi.dto.NodeDTO;
import com.dev.alves.viewlevelengineapi.dto.RuleEdgeDTO;
import com.dev.alves.viewlevelengineapi.dto.TreeDTO;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TreeValidator {

    public void validate(TreeDTO treeDTO) {
        validateIfSourceIsEqualTarget(treeDTO.getEdges());
        validateIfOnlyConditionCanHaveExists(treeDTO.getEdges(), treeDTO.getNodes());
        validateSingleEdgePerSourceHandle(treeDTO.getEdges());
    }

    private void validateSingleEdgePerSourceHandle(List<RuleEdgeDTO> edges) {
        for (RuleEdgeDTO edge : edges) {

        }
    }

    private void validateIfOnlyConditionCanHaveExists(List<RuleEdgeDTO> edges, Map<String, NodeDTO> nodes) {
        for (RuleEdgeDTO edge : edges) {
            var node = nodes.get(edge.getSource());
            if (NodeTypeEnum.ACTION.equals(node.getType())) {
                throw new ValidationException("Source must not be action");
            }
        }
    }

    private void validateIfSourceIsEqualTarget(@Valid List<RuleEdgeDTO> edges) {
        for (RuleEdgeDTO edge : edges) {
            if (edge.getSource().equals(edge.getTarget())) {
                throw new ValidationException("Source must not equal target");
            }
        }
    }
}
