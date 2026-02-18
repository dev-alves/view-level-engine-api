package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class IsPartnerConditionOperator implements ConditionOperator {

    @Override
    public String operation() {
        return "IsPartnerConditionOperator";
    }

    @Override
    public boolean test(DecisionContext context, Map<String, Object> args) {
        return context.getPermissions().contains("PERM_PARTNER");
    }

}
