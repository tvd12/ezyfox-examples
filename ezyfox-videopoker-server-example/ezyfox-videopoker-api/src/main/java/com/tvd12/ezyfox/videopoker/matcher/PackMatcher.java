package com.tvd12.ezyfox.videopoker.matcher;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.model.Pack;

/**
 * A class suport to check matching of array of cards with
 * winning pack
 * 
 * @author tavandung12
 *
 */
public interface PackMatcher extends IndexMatcher, RankMatcher {
    
    /**
     * 
     * @return pack result
     */
    Pack pack();
    
    /**
     * 
     * @param checker
     */
    void checker(PackChecker checker);
    
    /**
     * 
     * @return
     */
    boolean match(Card[] sortedCards);
    
    /**
     * 
     * @param cards
     */
    void cards(Card[] cards);
}
