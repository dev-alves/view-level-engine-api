package com.dev.alves.viewlevelengineapi.registries;

import com.dev.alves.viewlevelengineapi.conditions.ConditionOperator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConditionOperationRegistry {

    private final Map<String, ConditionOperator> operators;

    public ConditionOperationRegistry(final Map<String, ConditionOperator> operators) {
        this.operators = operators;
    }

    public ConditionOperator get(String operation) {
        var operator = operators.get(operation);
        if (operator == null) throw new IllegalArgumentException();
        return operator;
    }
}
