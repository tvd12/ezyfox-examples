package com.tvd12.ezyfox.demo.videopoker.matcher.impl;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;

public class FullHouseMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.FULL_HOUSE;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isFullHouse();
    }
    
    @Override
    public int[] ranks() {
        return ranks(3, 2);
    }

}
