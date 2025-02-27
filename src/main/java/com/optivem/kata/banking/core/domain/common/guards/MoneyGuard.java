package com.optivem.kata.banking.core.domain.common.guards;

import com.optivem.kata.banking.core.domain.accounts.Money;

public class MoneyGuard extends BaseGuard<Money> {
    public MoneyGuard(Money value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(value::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(value::isNonPositive, message);
    }
}
