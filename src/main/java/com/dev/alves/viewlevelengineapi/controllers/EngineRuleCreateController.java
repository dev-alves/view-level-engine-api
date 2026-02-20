package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.CreateRuleAction;
import com.dev.alves.viewlevelengineapi.dto.CreateRuleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine")
public class EngineRuleCreateController {

    @Autowired
    private CreateRuleAction action;

    @PostMapping("rule")
    public ResponseEntity<?> createRule(@RequestBody CreateRuleDTO createRuleDTO) {
        action.execute(createRuleDTO);
        return ResponseEntity.ok().build();
    }
}
