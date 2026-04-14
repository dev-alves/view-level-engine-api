package com.dev.alves.viewlevelengineapi.services;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.UpdateRuleDTO;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.models.Rule;
import com.dev.alves.viewlevelengineapi.registries.ConditionOperationRegistry;
import com.dev.alves.viewlevelengineapi.repositories.RuleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

    public void save(Rule newRule) {
        var newStatus = newRule.getStatus() != null ? newRule.getStatus() : StatusEnum.PUBLISHED;
        newRule.setStatus(newStatus);

        if (StatusEnum.PUBLISHED.equals(newStatus)) {
            deactivatePublishedRules();
        }

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

    public void updateRules(String id, UpdateRuleDTO updateRuleDTO) {
        var rule = ruleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void deactivatePublishedRules() {
        var publishedRules = ruleRepository.findAllByStatus(StatusEnum.PUBLISHED);

        for (var publishedRule : publishedRules) {
            publishedRule.setStatus(StatusEnum.DRAFT);
        }

        if (!publishedRules.isEmpty()) {
            ruleRepository.saveAll(publishedRules);
        }
    }

}
