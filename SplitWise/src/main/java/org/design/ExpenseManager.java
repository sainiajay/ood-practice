package org.design;

import java.util.Objects;

public class ExpenseManager {
    HouseHold houseHold;

    public ExpenseManager(HouseHold houseHold) {
        Objects.requireNonNull(houseHold, "Household cannot be null.");
        this.houseHold = houseHold;
    }

}
