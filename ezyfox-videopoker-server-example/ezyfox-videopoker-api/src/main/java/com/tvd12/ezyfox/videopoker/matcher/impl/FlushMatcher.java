package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

public class FlushMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.FLUSH;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isFlush();
    }

}
