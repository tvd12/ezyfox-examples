package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

public class JacksOrBetterMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.JACK_OR_BETTER;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isJacksOrBetter();
    }
    
    @Override
    public int indexes() {
        return indexes(2);
    }
    
    @Override
    public int[] ranks() {
        return ranks(2);
    }

}
