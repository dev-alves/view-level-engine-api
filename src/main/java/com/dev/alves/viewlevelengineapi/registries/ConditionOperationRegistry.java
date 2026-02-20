package com.dev.alves.viewlevelengineapi.registries;

import com.dev.alves.viewlevelengineapi.conditions.ConditionOperator;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConditionOperationRegistry {

    private final Map<ConditionOperatorEnum, ConditionOperator> operators;

    public ConditionOperationRegistry(final List<ConditionOperator> operators) {
        this.operators = operators.stream().collect(Collectors.toMap(ConditionOperator::operation, o -> o));
    }

    public ConditionOperator get(ConditionOperatorEnum operation) {
        var operator = operators.get(operation);
        if (operator == null) throw new IllegalArgumentException();
        return operator;
    }
}
