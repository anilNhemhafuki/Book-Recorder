package com.example.bookrecorder;

public class Parties {
    String name;
    String debit;
    String credit;
    String balance;

    public Parties(String name, String debit, String credit, String balance) {
        this.name = name;
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDebit() {
        return debit;
    }
    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
}
