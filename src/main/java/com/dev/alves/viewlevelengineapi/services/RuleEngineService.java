package com.dev.alves.viewlevelengineapi.services;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import com.dev.alves.viewlevelengineapi.registries.ConditionOperationRegistry;
import com.dev.alves.viewlevelengineapi.repositories.RuleRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RuleEngineService {

    private final ConditionOperationRegistry conditionOperationRegistry;
    private final RuleRepository ruleRepository;

    public RuleEngineService(ConditionOperationRegistry conditionOperationRegistry, RuleRepository ruleRepository) {
        this.conditionOperationRegistry = conditionOperationRegistry;
        this.ruleRepository = ruleRepository;
    }

    public Rule findRuleByStatus(StatusEnum status) {
        return ruleRepository.findByStatus(status);
    }

    public void save(DecisionContext decisionContext) {
        var newRule = new Rule();
        newRule.setNodes(decisionContext.getNodes().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue
                ))
        );

        newRule.setStatus(StatusEnum.PUBLISHED);
        ruleRepository.save(newRule);
    }

    public ViewLevelEnum getViewLevel(DecisionContext decisionContext) {
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

        return ViewLevelEnum.valueOf(node.getSet());
    }

}
