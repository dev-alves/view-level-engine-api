package com.dev.alves.viewlevelengineapi.validators;

import com.dev.alves.viewlevelengineapi.dto.NodeDTO;
import com.dev.alves.viewlevelengineapi.dto.RuleEdgeDTO;
import com.dev.alves.viewlevelengineapi.dto.TreeDTO;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum.ACTION;

@Component
public class TreeValidator {

    public void validate(TreeDTO treeDTO) {
        validateIfSourceIsEqualTarget(treeDTO.getEdges());
        validateIfOnlyConditionCanHaveExists(treeDTO.getEdges(), treeDTO.getNodes());
        validateSingleEdgePerSourceHandle(treeDTO.getEdges(), treeDTO.getNodes());
        validateOperationRequiredForConditionNodes(treeDTO.getNodes());
    }

    private void validateSingleEdgePerSourceHandle(List<RuleEdgeDTO> edges, Map<String, NodeDTO> nodes) {
        for (String source : nodes.keySet()) {
            var node = nodes.get(source);

            long trueSourceHandleCount = edges.stream()
                    .filter(edge -> source.equals(edge.getSource()))
                    .filter(RuleEdgeDTO::getSourceHandle)
                    .count();

            long falseSourceHandleCount = edges.stream()
                    .filter(edge -> source.equals(edge.getSource()))
                    .filter(edge -> !edge.getSourceHandle())
                    .count();

            boolean hasSingleTrueSourceHandle = trueSourceHandleCount == 1;
            boolean hasSingleFalseSourceHandle = falseSourceHandleCount == 1;

            if (ACTION.equals(node.getType()) && (trueSourceHandleCount > 0 || falseSourceHandleCount > 0)) {
                throw new ValidationException("Node action cannot have sourceHandles");
            }

            if (!ACTION.equals(node.getType()) && (!hasSingleTrueSourceHandle || !hasSingleFalseSourceHandle)) {
                throw new ValidationException("Single edge per source handle already exists");
            }
        }
    }

    private void validateIfOnlyConditionCanHaveExists(List<RuleEdgeDTO> edges, Map<String, NodeDTO> nodes) {
        for (RuleEdgeDTO edge : edges) {
            var node = nodes.get(edge.getSource());
            if (ACTION.equals(node.getType())) {
                throw new ValidationException("Source must not be action");
            }
        }
    }

    private void validateIfSourceIsEqualTarget(List<RuleEdgeDTO> edges) {
        for (RuleEdgeDTO edge : edges) {
            if (edge.getSource().equals(edge.getTarget())) {
                throw new ValidationException("Source must not equal target");
            }
        }
    }

    private void validateOperationRequiredForConditionNodes(Map<String, NodeDTO> nodes) {
        for (Map.Entry<String, NodeDTO> entry : nodes.entrySet()) {
            var node = entry.getValue();
            if (!ACTION.equals(node.getType()) && node.getOperation() == null) {
                throw new ValidationException("Condition node '" + entry.getKey() + "' must have an operation");
            }
        }
    }
}
