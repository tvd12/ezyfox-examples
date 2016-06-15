package com.tvd12.ezyfox.demo.videopoker.card;

import lombok.Getter;

/**
 * 
 * Suit of card
 * 
 * @author tavandung12
 *
 */
public enum Suit {
    
    DIAMOND(0, "diamond", "♦"),
    HEART(1, "heart", "♥"),
    SPADE(2, "spade", "♠"),
    CLUB(3, "club", "♣");
    
    private Suit(int value, String name, String display) {
        this.value = value;
        this.name = name;
        this.display = display;
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
    
    public static void main(String[] args) {
        System.out.println(DIAMOND.compareTo(HEART));
    }
}
