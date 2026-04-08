package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.models.Argument;

public interface ConditionOperator {

    ConditionOperatorEnum operation();
    boolean test(DecisionContext context, Argument args);
    boolean hasArgs();

}
