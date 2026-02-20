package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.dto.RuleDTO;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetPublishedRulesAction {

    private RuleEngineService ruleEngineService;

    public RuleDTO execute() {
        return RuleDTO.toDTO(ruleEngineService.findRuleByStatus(StatusEnum.PUBLISHED));
    }

}
