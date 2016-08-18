package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

public class ThreeOfAKindMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.THREE_OF_A_KIND;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isThreeCards();
    }

    @Override
    public int indexes() {
        return indexes(3);
    }
    
    @Override
    public int[] ranks() {
        return ranks(3);
    }
    
}
