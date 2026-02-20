package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserIsOperatorConditionOperator implements ConditionOperator {

    @Override
    public ConditionOperatorEnum operation() {
        return ConditionOperatorEnum.USER_IS_OPERATOR;
    }

    @Override
    public boolean test(DecisionContext context, Map<String, Object> args) {
        return context.getPermissions().contains("PERM_OPERATOR");
    }
}
