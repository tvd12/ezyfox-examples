/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.matcher.impl;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;

/**
 * @author tavandung12
 *
 */
public class WildRoyalFlushMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.WILD_ROYAL_FLUSH;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isNatureStraight()
                && checker.isNatureFlush()
                && checker.getSpecialCardCount() == 1;
    }
    
}
