package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.CreateRuleAction;
import com.dev.alves.viewlevelengineapi.actions.UpdateRuleAction;
import com.dev.alves.viewlevelengineapi.dto.CreateRuleDTO;
import com.dev.alves.viewlevelengineapi.dto.UpdateRuleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/engine/rule")
public class EngineRuleCreateController {

    private final CreateRuleAction action;
    private final UpdateRuleAction updateRuleAction;

    public EngineRuleCreateController(CreateRuleAction action, UpdateRuleAction updateRuleAction) {
        this.action = action;
        this.updateRuleAction = updateRuleAction;
    }

    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody CreateRuleDTO createRuleDTO) {
        action.execute(createRuleDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRule(@PathVariable String id, @RequestBody UpdateRuleDTO updateRuleDTO) {
        updateRuleAction.execute(id, updateRuleDTO);
        return ResponseEntity.ok().build();
    }
}
