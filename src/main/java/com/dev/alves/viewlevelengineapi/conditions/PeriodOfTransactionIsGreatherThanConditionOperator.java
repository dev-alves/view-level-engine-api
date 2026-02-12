package com.dev.alves.viewlevelengineapi.conditions;

import com.dev.alves.viewlevelengineapi.context.DecisionContext;
import org.jeasy.rules.api.Facts;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Component
public class PeriodOfTransactionIsGreatherThanConditionOperator implements ConditionOperator {

    @Override
    public String operation() {
        return "PeriodOfTransactionIsGreatherThanConditionOperator";
    }

    @Override
    public boolean test(DecisionContext context, Facts facts, Map<String, Object> args) {
        var object = args.get("txDaysTransaction");
        var value = (int) object;
        var lastTransaction = LocalDate.now().minusDays(10);
        var today = LocalDate.now();
        var differenceBetweenTodayAndLastTransaction = ChronoUnit.DAYS.between(lastTransaction, today);
        return differenceBetweenTodayAndLastTransaction > value;
    }

}
