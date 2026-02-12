package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import org.jeasy.rules.api.Facts;

import java.util.Map;

public interface ConditionOperator {

    String operation();
    boolean test(DecisionContext context, Facts facts, Map<String, Object> args);

}
