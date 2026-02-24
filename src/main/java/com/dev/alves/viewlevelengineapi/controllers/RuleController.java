package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.GetPublishedRulesAction;
import com.dev.alves.viewlevelengineapi.actions.GetRuleOperatorAction;
import com.dev.alves.viewlevelengineapi.dto.RuleDTO;
import com.dev.alves.viewlevelengineapi.dto.RuleOperatorDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rules")
@AllArgsConstructor
public class RuleController {

    private final GetPublishedRulesAction getPublishedRulesAction;
    private final GetRuleOperatorAction getRuleOperatorAction;

    @GetMapping
    public RuleDTO getPublishedRules() {
        return getPublishedRulesAction.execute();
    }

    @GetMapping("/operators")
    public List<RuleOperatorDTO> getRulesOperators() {
        return getRuleOperatorAction.execute();
    }

}
