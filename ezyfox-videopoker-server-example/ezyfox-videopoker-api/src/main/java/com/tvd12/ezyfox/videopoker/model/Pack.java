package com.tvd12.ezyfox.videopoker.model;

import lombok.Getter;

public enum Pack {

    ROYAL_FLUSH(    0,     "royal flush",      "royal flush"),
    STRAIGHT_FLUSH( 1,      "straight flush",   "thung pha sanh"),
    FOUR_OF_A_KIND( 2,      "four of a kind",   "tu qui"),
    FULL_HOUSE(     3,       "full house",       "cu lu"),
    FLUSH(          4,       "flush",            "thung"),
    STRAIGHT(       5,       "straight",         "sanh"),
    THREE_OF_A_KIND(6,       "three of a kind",  "sam"),
    TWO_PAIRS(      7,       "two pairs",        "hai doi"),
    JACK_OR_BETTER( 8,       "jack or better",   "doi J tro len"),
    NONE(           9,       "none",             "khong an"),
    FIVE_OF_A_KIND( 10,      "five of a kind",   "tu qui va joker"),
    WILD_ROYAL_FLUSH(11,     "wil royal flush", "thung pha sanh va joker"),
    KING_OR_BETTER( 12,      "king or better",   "doi K tro len");
    
    
    
    @Getter private int value;
    @Getter private String name;
    @Getter private String display;
    
    private Pack(int value, String name, String display) {
        this.value = value;
        this.name = name;
        this.display = display;
    }
    
    public static Pack valueOf(int value) {
        for(Pack pack : values()) {
            if(pack.getValue() == value)
                return pack;
        }
        throw new IllegalStateException("Has no pack with id = " + value);
    }
    
    @Override
    public String toString() {
        return display;
    }
}
