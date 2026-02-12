package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserIsPartnerConditionOperator implements ConditionOperator {

    @Override
    public String operation() {
        return "UserIsPartnerConditionOperator";
    }

    @Override
    public boolean test(DecisionContext context, Facts facts, Map<String, Object> args) {
        return context.getCustomerDTO().isPartner();
    }
}
