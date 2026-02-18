package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.requests.CreateRuleRequest;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.stereotype.Component;

@Component
public class CreateRuleAction {

    private final RuleEngineService ruleEngineService;

    public CreateRuleAction(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    public void execute(CreateRuleRequest createRuleRequest) {
        ruleEngineService.save(DecisionContext.builder()
                .version(createRuleRequest.getVersion())
                .nodes(createRuleRequest.toModel().getNodes())
                .startNode(createRuleRequest.getStartNode())
                .build());
    }

}
