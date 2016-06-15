package com.tvd12.ezyfox.demo.videopoker.card;

import lombok.Getter;

/**
 * 
 * Rank of card
 * 
 * @author tavandung12
 *
 */
public enum Rank {
    TWO(0, "two", "2"),
    THREE(1, "three", "3"),
    FOUR(2, "four", "4"),
    FIVE(3, "five", "5"),
    SIX(4, "six", "6"),
    SEVEN(5, "seven", "7"),
    EIGHT(6, "eight", "8"),
    NINE(7, "nine", "9"),
    TEN(8, "ten", "10"),
    JACK(9, "jack", "J"),
    QUEEN(10, "queen", "Q"),
    KING(11, "king", "K"),
    ACE(12, "ace", "A"),
    JOKER(13, "joker", "Joker"),
    WILD(14, "joker's wild", "Wild");
    
    private Rank(int value, String name, String display) {
        this.value = value;
        this.name = name;
        this.display = display;
    }
    
    public static Rank[] normal() {
        return new Rank[] {
                TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
                JACK, QUEEN, KING, ACE
        };
    }
    
    public boolean isNext(Rank rank) {
        return getValue() == rank.getValue() + 1;
    }
    
    public int compare(Rank rank) {
        return getValue() - rank.getValue();
    }
    
    @Getter
    private int value;
    
    @Getter
    private String name;
    
    @Getter
    private String display;
    
    @Override
    public String toString() {
        return display;
    }
}
