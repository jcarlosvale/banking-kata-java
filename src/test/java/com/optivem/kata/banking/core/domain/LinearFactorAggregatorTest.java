package com.optivem.kata.banking.core.domain;

import com.optivem.kata.banking.core.domain.accounts.scoring.FactorAggregator;
import com.optivem.kata.banking.core.domain.accounts.scoring.FactorCalculator;
import com.optivem.kata.banking.core.domain.accounts.scoring.LinearFactorAggregator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.optivem.kata.banking.core.common.builders.entities.BankAccountBuilder.aBankAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class LinearFactorAggregatorTest {
    private FactorCalculator nameFactorCalculator;
    private FactorCalculator balanceFactorCalculator;
    private FactorCalculator timeFactorCalculator;
    private FactorAggregator factorAggregator;

    @BeforeEach
    void init() {
        nameFactorCalculator = mock(FactorCalculator.class);
        balanceFactorCalculator = mock(FactorCalculator.class);
        timeFactorCalculator = mock(FactorCalculator.class);
        factorAggregator = new LinearFactorAggregator(nameFactorCalculator, balanceFactorCalculator, timeFactorCalculator);
    }

    @Test
    void should_return_name_factor_plus_balance_factor_minus_time_factor() {
        var bankAccount = aBankAccount().build();

        var nameFactor = 10;
        var balanceFactor = 40;
        var timeFactor = 15;

        var expectedAggregationResult = 35;

        given(nameFactorCalculator.calculate(bankAccount)).willReturn(nameFactor);
        given(balanceFactorCalculator.calculate(bankAccount)).willReturn(balanceFactor);
        given(timeFactorCalculator.calculate(bankAccount)).willReturn(timeFactor);

        var aggregationResult = factorAggregator.aggregate(bankAccount);

        assertThat(aggregationResult).isEqualTo(expectedAggregationResult);
    }
}
