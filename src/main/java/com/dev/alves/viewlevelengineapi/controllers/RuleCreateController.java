package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.request.CreateRuleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleCreateController {

    @PostMapping
    public ResponseEntity createRule(@RequestBody CreateRuleRequest createRuleRequest) {
        return ResponseEntity.ok().build();
    }
}
