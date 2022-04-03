package org.design;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main driver class
 */
public class App {
    public static void main(String[] args) {

    }

    public static void run(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "Input stream cannot be null");
        Scanner inputScanner = new Scanner(inputStream);
        HouseHold houseHold = new HouseHold();
        while(inputScanner.hasNext()) {
            String command = inputScanner.next();
            switch (command) {
                case "MOVE_IN":
                    houseHold.moveIn(inputScanner.next());
                    break;
                case "MOVE_OUT":
                    houseHold.moveOut(inputScanner.next());
                    break;
                case "SPEND":
                    String x = inputScanner.next();
                    double amount = Double.parseDouble(x);
                    String spentBy = inputScanner.next();
                    String[] participant = inputScanner.nextLine().strip().split(" ");
                    houseHold.addExpense(amount, spentBy, List.of(participant));
                    break;
                case "CLEAR_DUE":
                    String paidBy = inputScanner.next();
                    String paidTo = inputScanner.next();
                    houseHold.clearDues(Double.parseDouble(inputScanner.next()), paidBy, paidTo);
                    break;
                case "DUES":
                    String memberName = inputScanner.next();
                    houseHold.showDues(memberName);
                    break;
            }

        }
    }
}
