package com.tvd12.ezyfox.demo.videopoker.matcher.impl;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.card.Rank;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;

public class KingsOrBetterMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.KING_OR_BETTER;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.better(Rank.KING);
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
