package com.dev.alves.viewlevelengineapi.services;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.NodeDTO;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.models.Node;
import com.dev.alves.viewlevelengineapi.models.Rule;
import com.dev.alves.viewlevelengineapi.registries.ConditionOperationRegistry;
import com.dev.alves.viewlevelengineapi.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RuleEngineService {

    @Autowired
    private ConditionOperationRegistry conditionOperationRegistry;

    @Autowired
    private RuleRepository ruleRepository;

    public ViewLevelEnum execute(DecisionContext decisionContext) {
        var newRule = new Rule();
        newRule.setNodes(decisionContext.getNodes().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, e -> toEntityNode(e.getValue())
                )));

        newRule.setStatus(StatusEnum.PUBLISHED);

        var currentNodeId = decisionContext.getStartNode();
        var node = decisionContext.getNodes().get(currentNodeId);

        while(!NodeTypeEnum.ACTION.equals(node.getType())) {
            var type = node.getType();
            if (Objects.requireNonNull(type) == NodeTypeEnum.CONDITION) {
                var operation = conditionOperationRegistry.get(node.getOperation());
                var isTrue = operation.test(decisionContext, node.getArguments());
                currentNodeId = isTrue ? node.getOnTrue() : node.getOnFalse();
            }

            node = decisionContext.getNodes().get(currentNodeId);
            if (node == null) throw new IllegalArgumentException();
        }

        ruleRepository.save(newRule);
        return ViewLevelEnum.valueOf(node.getSet());
    }

    private Node toEntityNode(NodeDTO value) {
        var node = new Node();
        node.setSet(value.getSet());
        node.setOnTrue(value.getOnTrue());
        node.setOnFalse(value.getOnFalse());
        node.setOperation(value.getOperation());
        node.setNext(value.getNext());
        node.setType(value.getType());
        node.setArguments(value.getArguments());
        return node;
    }

}
