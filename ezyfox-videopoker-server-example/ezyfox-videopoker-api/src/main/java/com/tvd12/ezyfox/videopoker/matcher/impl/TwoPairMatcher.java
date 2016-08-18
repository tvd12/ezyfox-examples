package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

public class TwoPairMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.TWO_PAIRS;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isTwoPair();
    }
    
    @Override
    public int indexes() {
        return indexes(2, 2);
    }
    
    @Override
    public int[] ranks() {
        return ranks(2, 2);
    }

}
