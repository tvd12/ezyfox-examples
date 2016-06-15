package com.tvd12.ezyfox.demo.videopoker.matcher.impl;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;

public class StraightMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.STRAIGHT;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isStraight();
    }

}
