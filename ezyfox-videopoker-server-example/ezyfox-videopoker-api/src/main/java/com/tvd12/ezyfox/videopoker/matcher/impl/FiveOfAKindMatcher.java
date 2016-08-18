/**
 * 
 */
package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

/**
 * @author tavandung12
 *
 */
public class FiveOfAKindMatcher extends BaseMatcher {

    @Override
    public Pack pack() {
        return Pack.FIVE_OF_A_KIND;
    }

    @Override
    public boolean match(Card[] cards) {
        return checker.isNatureFourCards()
                && checker.getSpecialCardCount() == 1;
    }
    
}
