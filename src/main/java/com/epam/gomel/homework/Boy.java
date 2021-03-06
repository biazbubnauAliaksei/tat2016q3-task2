package com.epam.gomel.homework;

import lombok.Getter;
import lombok.Setter;

public class Boy extends Human {

    @Getter
    private Month birthdayMonth;
    @Getter
    @Setter
    private Girl girlFriend;
    @Getter
    private double wealth;

    public Boy(Month birthdayMonth, double wealth, Girl girlFriend) {
        this.birthdayMonth = birthdayMonth;
        this.wealth = wealth;
        if (girlFriend == null) {
            return;
        }
        this.girlFriend = girlFriend;
        this.girlFriend.setBoyFriend(this);
    }

    public Boy(Month birthdayMonth, double wealth) {
        this(birthdayMonth, wealth, null);
    }

    public Boy(Month birthdayMonth) {
        this(birthdayMonth, 0, null);
    }

    @Override
    public Mood getMood() {
        if (isRich() && isPrettyGirlFriend() && isSummerMonth()) {
            return Mood.EXCELLENT;
        } else if (isRich() && isPrettyGirlFriend()) {
            return Mood.GOOD;
        } else if (isRich() && isSummerMonth()) {
            return Mood.NEUTRAL;
        } else if (isRich() || isPrettyGirlFriend() || isSummerMonth()) {
            return Mood.BAD;
        }
        return Mood.HORRIBLE;
    }

    public void spendSomeMoney(double amountForSpending) {
        if (amountForSpending <= getWealth()) {
            wealth -= amountForSpending;
        } else {
            throw new RuntimeException(String.format("Not enough money! Requested amount is %s$ but you can't spend more then %s$", amountForSpending, getWealth()));
        }
    }

    public boolean isSummerMonth() {
        return Month.JUNE.equals(getBirthdayMonth()) || Month.JULY.equals(getBirthdayMonth()) || Month.AUGUST.equals(getBirthdayMonth());
    }

    public boolean isRich() {
        return getWealth() >= 1000000d;
    }

    public boolean isPrettyGirlFriend() {
        return getGirlFriend() != null && getGirlFriend().isPretty();
    }
}
