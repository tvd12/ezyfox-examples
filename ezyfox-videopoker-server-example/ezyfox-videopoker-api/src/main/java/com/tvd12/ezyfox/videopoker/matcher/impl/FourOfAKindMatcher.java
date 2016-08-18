package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

public class FourOfAKindMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.FOUR_OF_A_KIND;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isFourCards();
    }
    
    @Override
    public int indexes() {
        return indexes(4);
    }
    
    @Override
    public int[] ranks() {
        return ranks(4);
    }

}
