package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.CreateRuleDTO;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.stereotype.Component;

@Component
public class CreateRuleAction {

    private final RuleEngineService ruleEngineService;

    public CreateRuleAction(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    public void execute(CreateRuleDTO createRuleDTO) {
        ruleEngineService.save(DecisionContext.builder()
                .startNode(createRuleDTO.getStartNode())
                .version(createRuleDTO.getVersion())
                .nodes(createRuleDTO.toModel().getNodes())
                .startNode(createRuleDTO.getStartNode())
                .build());
    }

}
