package com.tvd12.ezyfox.demo.videopoker.matcher.impl;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.card.Rank;
import com.tvd12.ezyfox.demo.videopoker.card.Suit;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;

public class RoyalFlushMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.ROYAL_FLUSH;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.getSpecialCardCount() == 0
                && checker.isStraight() 
                && checker.isFlush() 
                && cards[0].getSuit() == Suit.DIAMOND
                && cards[0].getRank() == Rank.TEN;
    }

}
