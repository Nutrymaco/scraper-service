package com.nutrymaco.parser.pojo;

public class GJExperience {

    private final int from;

    private final int to;

    private GJExperience(int from, int to) {
        this.from = from;
        this.to = to;
    }

    private GJExperience(int from) {
        this.from = from;
        this.to = Integer.MAX_VALUE;
    }

    private GJExperience() {
        this.from = 0;
        this.to = Integer.MAX_VALUE;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public static GJExperience parse(String experience) {
        switch (experience) {
            case "Опыт работы менее года": return new GJExperience(0, 1);
            case "Опыт работы от 1 года до 3х лет": return new GJExperience(1, 3);
            case "Опыт работы от 3х до 5 лет": return new GJExperience(3, 5);
            case "Опыт работы более 5 лет": return new GJExperience(5);
            default: return new GJExperience();
        }
    }

}
