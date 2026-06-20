package com.gavin.rinnepay.common.entities;
import com.gavin.rinnepay.common.enums.CurrencyType;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
    private Integer amountUnits;
    private String currency;

    private Money(Integer amountUnits, String currency) {
        this.amountUnits = amountUnits;
        this.currency = currency;
    }

    public static Money of(Integer amountUnits, String currency) {
        return new Money(amountUnits, currency);
    }

    public static Money inr (int amountUnits) {
        return new Money(amountUnits, CurrencyType.INR.name());
    }

    public Money add(Money otherMoney) {
        if (!this.currency.equals(otherMoney.currency)) {
            throw new IllegalArgumentException("Cannot add money of different currencies");
        }
        return  new Money(this.amountUnits + otherMoney.amountUnits, this.currency);
    }

    public Money subtract(Money otherMoney) {
        if (!this.currency.equals(otherMoney.currency)) {
            throw new IllegalArgumentException("Cannot subtract money of different currencies");
        }
        return  new Money(this.amountUnits - otherMoney.amountUnits, this.currency);
    }
}
