package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.CreateRuleAction;
import com.dev.alves.viewlevelengineapi.dto.CreateRuleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine/rule")
public class EngineRuleCreateController {

    private final CreateRuleAction action;

    public EngineRuleCreateController(CreateRuleAction action) {
        this.action = action;
    }

    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody CreateRuleDTO createRuleDTO) {
        action.execute(createRuleDTO);
        return ResponseEntity.ok().build();
    }
}
