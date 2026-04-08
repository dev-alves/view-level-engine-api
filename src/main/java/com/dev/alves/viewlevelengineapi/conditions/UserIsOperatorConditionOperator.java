package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.models.Argument;
import org.springframework.stereotype.Component;

@Component
public class UserIsOperatorConditionOperator implements ConditionOperator {

    @Override
    public ConditionOperatorEnum operation() {
        return ConditionOperatorEnum.USER_IS_OPERATOR;
    }

    @Override
    public boolean test(DecisionContext context, Argument args) {
        return context.getPermissions().contains("PERM_OPERATOR");
    }

    @Override
    public boolean hasArgs() {
        return false;
    }

}
