package com.dev.alves.viewlevelengineapi.services;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.dto.CustomerDTO;
import com.dev.alves.viewlevelengineapi.enums.NodeTypeEnum;
import com.dev.alves.viewlevelengineapi.enums.ViewLevelEnum;
import com.dev.alves.viewlevelengineapi.registries.ConditionOperationRegistry;
import com.dev.alves.viewlevelengineapi.requests.CreateRuleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RuleEngineService {

    @Autowired
    private ConditionOperationRegistry conditionOperationRegistry;

    public ViewLevelEnum execute(CreateRuleRequest createRuleRequest) {

        // Obter do token
        var permissions = List.of(
                "PERM_ADMIN",
                "PERM_PARTNER"
        );

        // Obter via integração
        var customer = new CustomerDTO();
        customer.setId(1L);

        var currentNodeId = createRuleRequest.getStartNode();

        var context = new DecisionContext(customer, permissions);
        var node = createRuleRequest.getNodes().get(currentNodeId);

        while(!NodeTypeEnum.ACTION.equals(node.getType())) {
            var type = node.getType();
            if (Objects.requireNonNull(type) == NodeTypeEnum.CONDITION) {
                var operation = conditionOperationRegistry.get(node.getOperation());
                var isTrue = operation.test(context, node.getArguments());
                currentNodeId = isTrue ? node.getOnTrue() : node.getOnFalse();
            }
            node = createRuleRequest.getNodes().get(currentNodeId);

            if (node == null) throw new IllegalArgumentException();
        }


        return ViewLevelEnum.valueOf(node.getSet());
    }

}
