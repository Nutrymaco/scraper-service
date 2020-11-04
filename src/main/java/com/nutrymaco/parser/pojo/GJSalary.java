package com.nutrymaco.parser.pojo;


public class GJSalary {

    private final int from;

    private final int to;

    private final Currency currency;

    private GJSalary(int from, int to, Currency currency) {
        this.from = from;
        this.to = to;
        this.currency = currency;
    }

    private GJSalary(int to, Currency currency) {
        this.currency = currency;
        this.from = 0;
        this.to = to;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public String currency() {
        return currency.toString();
    }

    public static GJSalary parse(String salary) {
        salary = salary.replaceAll(" ", "");
        if (salary.equals("")) {
            return new GJSalary(0, 0, Currency.RUB);
        }
        Currency currency = parseCurrency(salary);
        salary = salary.substring(0, salary.length() - 1);
        if (salary.startsWith("от")) {
            if (salary.contains("до")) {
                String[] fromTo = salary.substring(2).split("до");
                int from = Integer.parseInt(fromTo[0]);
                int to = Integer.parseInt(fromTo[1]);
                return new GJSalary(from, to, currency);
            } else {
                int from = Integer.parseInt(salary.substring(2));
                return new GJSalary(from, Integer.MAX_VALUE, currency);
            }
        } else if (salary.startsWith("до")) {
            int to = Integer.parseInt(salary.substring(2));
            return new GJSalary(to, currency);
        } else {
            int to = parseNumber(salary);
            return new GJSalary(to, currency);
        }
    }

    private static Currency parseCurrency(String salary) {
        char currency = salary.charAt(salary.length() - 1);
        switch (currency) {
            case '₽': return Currency.RUB;
            case '$': return Currency.USD;
            default: return Currency.RUB;
        }
    }

    private static int parseNumber(String salary) {
        salary = salary.replace(" ", "");
        if (salary.contains("K")) {
            return Integer.parseInt(salary.substring(0, salary.length() - 1)) * 1000;
        } else {
            return Integer.parseInt(salary);
        }
    }

    @Override
    public String toString() {
        return "GJSalary{" +
                "from=" + from +
                ", to=" + to +
                ", currency=" + currency +
                '}';
    }
}

enum Currency {
    RUB,
    USD
}