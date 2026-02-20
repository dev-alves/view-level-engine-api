package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import com.dev.alves.viewlevelengineapi.enums.ConditionOperatorEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Component
public class PeriodOfTransactionIsLessThanConditionOperator implements ConditionOperator {

    @Override
    public ConditionOperatorEnum operation() {
        return ConditionOperatorEnum.PERIOD_OF_TRANSACTION_IS_LESS_THAN;
    }

    @Override
    public boolean test(DecisionContext context, Map<String, Object> args) {
        var object = args.get("paramDaysTransaction");
        var value = (int) object;
        var lastTransaction = LocalDate.now().minusDays(10);
        var today = LocalDate.now();
        var differenceBetweenTodayAndLastTransaction = ChronoUnit.DAYS.between(lastTransaction, today);
        return differenceBetweenTodayAndLastTransaction <= value;
    }

}
