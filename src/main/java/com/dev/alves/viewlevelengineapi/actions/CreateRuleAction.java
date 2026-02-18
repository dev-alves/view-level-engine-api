package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.requests.CreateRuleRequest;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateRuleAction {

    @Autowired
    private RuleEngineService ruleEngineService;

    public void execute(CreateRuleRequest createRuleRequest) {
        ruleEngineService.save(DecisionContext.builder()
                .version(createRuleRequest.getVersion())
                .nodes(createRuleRequest.getNodes())
                .startNode(createRuleRequest.getStartNode())
                .build());
    }

}
