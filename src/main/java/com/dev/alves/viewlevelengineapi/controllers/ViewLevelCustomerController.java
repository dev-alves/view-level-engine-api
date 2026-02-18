package com.dev.alves.viewlevelengineapi.controllers;

import com.dev.alves.viewlevelengineapi.actions.GetViewLevelCustomerAction;
import com.dev.alves.viewlevelengineapi.responses.ViewLevelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view-level-customer/{id}")
public class ViewLevelCustomerController {

    private final GetViewLevelCustomerAction getViewLevelCustomerAction;

    public ViewLevelCustomerController(GetViewLevelCustomerAction getViewLevelCustomerAction) {
        this.getViewLevelCustomerAction = getViewLevelCustomerAction;
    }

    @GetMapping
    public ResponseEntity<ViewLevelResponse> getViewLevel(@PathVariable Long id) {
        return ResponseEntity.ok(getViewLevelCustomerAction.execute(id));
    }
}
