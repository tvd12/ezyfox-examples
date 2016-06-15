package com.tvd12.ezyfox.demo.videopoker.builder;

import java.util.ArrayList;
import java.util.List;

import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.matcher.PackChecker;
import com.tvd12.ezyfox.demo.videopoker.matcher.PackMatcher;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;
import com.tvd12.ezyfox.demo.videopoker.model.Result;
import com.tvd12.ezyfox.demo.videopoker.util.CardUtil;

/**
 * 
 * A builder support to check matching array of cards with
 * list of winning pack 
 * 
 * @author tavandung12
 *
 */
public class PackMatcherChain {

    private Card[] cards;
    
    //array of cards
    private Card[] sortedCards;
    
    private PackChecker checker;
    
  //which matchers used to check matching
    private List<PackMatcher> matchers
            = new ArrayList<>();
    
    //factory function
    public static PackMatcherChain create() {
        return new PackMatcherChain();
    }
    
    //set a matcher object
    public PackMatcherChain matcher(PackMatcher matcher) {
        matchers.add(matcher);
        return this;
    }
    
    public PackMatcherChain checker(PackChecker checker) {
        this.checker = checker;
        return this;
    }
    
    //set array of cards
    public PackMatcherChain cards(Card[] cards) {
        this.cards = cards;
        this.sortedCards = new Card[cards.length];
        for(int i = 0 ; i < cards.length ; i++) {
            this.sortedCards[i] = cards[i];
        }
        return this;
    }
    
    //start check
    public Result check() {
        sortCards();
        return invokeMatcher();
    }
    
    //sort array of cards
    private void sortCards() {
        CardUtil.sort(sortedCards);
    }

    /**
     * 
     * Count number of duplicate rank in array of cards
     * 
     * @param sortedCards array of cards
     * @return fit with condition or not
     */
    private Result invokeMatcher() {
        checker.check(sortedCards);
        for(PackMatcher matcher : matchers) {
            matcher.cards(cards);
            matcher.checker(checker);
            if(matcher.match(sortedCards))
                return new Result(matcher.pack(), 
                        matcher.indexes(), matcher.ranks());
        }
        return new Result(Pack.NONE, 0, new int[0]);
    }
    
}
