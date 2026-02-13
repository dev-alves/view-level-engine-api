package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserIsPartnerConditionOperator implements ConditionOperator {

    @Override
    public String operation() {
        return "UserIsPartnerConditionOperator";
    }

    @Override
    public boolean test(DecisionContext context, Map<String, Object> args) {
        return context.getPermissions().contains("PERM_PARTNER");
    }
}
