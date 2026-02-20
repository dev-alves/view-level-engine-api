package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.enums.StatusEnum;
import com.dev.alves.viewlevelengineapi.dto.ViewLevelDTO;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetViewLevelCustomerAction {

    private final RuleEngineService ruleEngineService;

    public GetViewLevelCustomerAction(RuleEngineService ruleEngineService) {
        this.ruleEngineService = ruleEngineService;
    }

    public ViewLevelDTO execute(Long id) {
        var response = new ViewLevelDTO();
        var rule = ruleEngineService.findRuleByStatus(StatusEnum.PUBLISHED);

        // Obter do token
        var permissions = List.of(
                "PERM_ADMIN",
                "PERM_OPERATOR"
        );

        // Obter via integração
        var customer = new CustomerDTO();
        customer.setId(id);

        response.setViewLevel(ruleEngineService.getViewLevel(DecisionContext.builder()
                .startNode(rule.getStartNode())
                .nodes(rule.getNodes())
                .customer(customer)
                .permissions(permissions)
                .build())
        );

        return response;
    }

}
