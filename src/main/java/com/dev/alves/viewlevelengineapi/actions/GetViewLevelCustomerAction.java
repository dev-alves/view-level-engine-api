package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.responses.ViewLevelResponse;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetViewLevelCustomerAction {

    private final RuleEngineService ruleEngineService;

    public GetViewLevelCustomerAction(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    public ViewLevelResponse execute(Long id) {
        var response = new ViewLevelResponse();
        var rule = ruleEngineService.findRuleByStatus(StatusEnum.PUBLISHED);

        // Obter do token
        var permissions = List.of(
                "PERM_ADMIN",
                "PERM_PARTNER"
        );

        // Obter via integração
        var customer = new CustomerDTO();
        customer.setId(id);

        response.setViewLevel(ruleEngineService.getViewLevel(DecisionContext.builder()
                .startNode(rule.getStartNode())
                .nodes(rule.getNodes())
                .customerDTO(customer)
                .permissions(permissions)
                .build())
        );

        return response;
    }

}
