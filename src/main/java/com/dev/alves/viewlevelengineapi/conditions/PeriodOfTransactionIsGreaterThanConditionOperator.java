package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import com.dev.alves.viewlevelengineapi.models.Argument;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class PeriodOfTransactionIsGreaterThanConditionOperator implements ConditionOperator {

    @Override
    public ConditionOperatorEnum operation() {
        return ConditionOperatorEnum.PERIOD_OF_TRANSACTION_IS_GREATER_THAN;
    }

    @Override
    public boolean test(DecisionContext context, Argument args) {
        var object = args != null ? args.getValue() : null;
        var value = Integer.parseInt(String.valueOf(object));
        var lastTransaction = LocalDate.now().minusDays(30);
        var today = LocalDate.now();
        var differenceBetweenTodayAndLastTransaction = ChronoUnit.DAYS.between(lastTransaction, today);
        return differenceBetweenTodayAndLastTransaction >= value;
    }

    @Override
    public boolean hasArgs() {
        return true;
    }

}
