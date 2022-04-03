package org.design;

import java.util.*;

public class HouseHold {
    private static final int MEMBERS_LIMIT = 3;
    TreeSet<String> membersList = new TreeSet<>();
    Map<String, Double> balances = new HashMap<>();

    public void moveIn(String memberName) {
        System.out.println(String.format("MOVE_IN: %s", memberName));
        if(membersList.size() == MEMBERS_LIMIT) {
            throw new RuntimeException(AppExceptions.HOUSEFUL.toString());
        }
        membersList.add(memberName);
    }

    public void moveOut(String memberName) {
        System.out.println(String.format("MOVE_OUT: %s", memberName));
        if(!balances.containsKey(memberName)) {
            throw new RuntimeException(AppExceptions.MEMBER_NOT_FOUND.toString());
        }
        balances.remove(memberName);
    }

    public void addExpense(Double amount, String payer, List<String> borrowers) {
        System.out.println(String.format("SPEND: %.2f %s", amount, payer));
        for(String borrower: borrowers) {
            if(borrower == null) {
                throw new IllegalArgumentException("Can't be null");
            }
            if(borrower.isEmpty()) {
                throw new IllegalArgumentException("Can't be empty");
            }
        }
        double parts = amount / (borrowers.size() + 1);
        double currentBalance = balances.getOrDefault(payer, 0.0);
        balances.put(payer, currentBalance + amount - parts);
        for(String borrower: borrowers) {
            currentBalance = balances.getOrDefault(borrower, 0.0);
            balances.put(borrower, currentBalance - parts);
        }
    }

    public void clearDues(Double amount, String paidBy, String paidTo) {
        System.out.println(String.format("CLEAR: %.2f %s %s", amount, paidBy, paidTo));
        double currentBalance = balances.getOrDefault(paidBy, 0.0);
        balances.put(paidBy, currentBalance + amount);

        currentBalance = balances.getOrDefault(paidTo, 0.0);
        balances.put(paidTo, currentBalance - amount);
    }

    public void showDues(String memberName) {
        System.out.println(String.format("SHOW: %s", memberName));
        Map<String, Map<String, Double>> paymentGraph = computePaymentGraph();
        ArrayList<Map.Entry<String, Double>> duesList  = new ArrayList<>(paymentGraph.get(memberName).entrySet());
        Collections.sort(duesList, Comparator.comparingDouble(entry -> -entry.getValue()));
        for(Map.Entry<String, Double> dues: duesList) {
            System.out.println(String.format("==> %s %.2f", dues.getKey(), dues.getValue()));
        }
    }

    public Map<String, Map<String, Double>> computePaymentGraph() {
        //receivers
        PriorityQueue<Map.Entry<String, Double>> positiveBalances =
                new PriorityQueue<>(Comparator.comparingDouble(entry -> entry.getValue()));
        //givers
        PriorityQueue<Map.Entry<String, Double>> negativeBalances =
                new PriorityQueue<>(Comparator.comparingDouble(entry -> -entry.getValue()));
        for(Map.Entry<String, Double> entry: balances.entrySet()) {
            System.out.println(entry);
            if(entry.getValue() >= 0.0) {
                positiveBalances.offer(entry);
            }
            else {
                negativeBalances.offer(entry);
            }
        }
        Map<String, Map<String, Double>> graph = new HashMap<>();
        while(!positiveBalances.isEmpty() && !negativeBalances.isEmpty()) {
            Map.Entry<String, Double> receiver = positiveBalances.poll();
            Map.Entry<String, Double> giver = negativeBalances.poll();
            double amount = Math.min(receiver.getValue(), -giver.getValue());
            graph.computeIfAbsent(giver.getKey(), k -> new HashMap<>())
                    .put(receiver.getKey(), amount);
            if (amount < receiver.getValue()) {
                positiveBalances.add(Map.entry(receiver.getKey(), receiver.getValue() - amount));
            } else if (amount < -giver.getValue()) {
                negativeBalances.add(Map.entry(giver.getKey(), giver.getValue() + amount));
            }
        }

        for(String member: membersList) {
            graph.putIfAbsent(member, new HashMap<>());
        }
        for(String key: graph.keySet()) {
            for(String member: membersList) {
                if(!key.equals(member)) {
                    graph.get(key).putIfAbsent(member, 0.0);
                }
            }
        }
        return graph;
    }
}
