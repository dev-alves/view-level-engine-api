package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.GetPublishedRulesAction;
import com.dev.alves.viewlevelengineapi.dto.RuleDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private GetPublishedRulesAction getPublishedRulesAction;

    public RuleController(GetPublishedRulesAction getPublishedRulesAction) {
        this.getPublishedRulesAction = getPublishedRulesAction;
    }

    @GetMapping
    public RuleDTO getPublishedRules() {
        return getPublishedRulesAction.execute();
    }

}
