package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;

import java.util.Map;

public interface ConditionOperator {

    String operation();
    boolean test(DecisionContext context, Map<String, Object> args);

}
