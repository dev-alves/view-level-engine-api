package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;

import java.util.Map;

public interface ConditionOperator {

    ConditionOperatorEnum operation();
    boolean test(DecisionContext context, Map<String, Object> args);
    boolean hasArgs();

}
