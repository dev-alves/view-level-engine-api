package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.dto.UpdateRuleDTO;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import com.dev.alves.viewlevelengineapi.validators.TreeValidator;
import org.springframework.stereotype.Component;

@Component
public class UpdateRuleAction {

    private final TreeValidator treeValidator;
    private final RuleEngineService ruleEngineService;

    public UpdateRuleAction(TreeValidator treeValidator, RuleEngineService ruleEngineService) {
        this.treeValidator = treeValidator;
        this.ruleEngineService = ruleEngineService;
    }

    public void execute(String id, UpdateRuleDTO updateRuleDTO) {
        treeValidator.validate(updateRuleDTO);
        ruleEngineService.updateRules(id, updateRuleDTO);
    }

}
