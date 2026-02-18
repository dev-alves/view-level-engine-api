package com.dev.alves.viewlevelengineapi.actions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.requests.CreateRuleRequest;
import com.dev.alves.viewlevelengineapi.responses.ViewLevelResponse;
import com.dev.alves.viewlevelengineapi.services.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateRuleAction {

    @Autowired
    private RuleEngineService ruleEngineService;

    public ViewLevelResponse execute(CreateRuleRequest createRuleRequest) {
        var viewLevelDefault = new ViewLevelResponse();

        // Obter do token
        var permissions = List.of(
                "PERM_ADMIN",
                "PERM_PARTNER"
        );

        // Obter via integração
        var customer = new CustomerDTO();
        customer.setId(1L);

        viewLevelDefault.setViewLevel(ViewLevelEnum.COMPLETE);
        viewLevelDefault.setViewLevel(ruleEngineService.execute(DecisionContext.builder()
                .customerDTO(customer)
                .permissions(permissions)
                .version(createRuleRequest.getVersion())
                .nodes(createRuleRequest.getNodes())
                .startNode(createRuleRequest.getStartNode())
                .build())
        );

        return viewLevelDefault;
    }

}
