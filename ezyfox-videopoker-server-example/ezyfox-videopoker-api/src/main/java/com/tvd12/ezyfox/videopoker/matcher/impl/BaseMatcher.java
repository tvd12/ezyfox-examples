package com.tvd12.ezyfox.videopoker.matcher.impl;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.card.Rank;
import com.tvd12.ezyfox.videopoker.matcher.PackChecker;
import com.tvd12.ezyfox.videopoker.matcher.PackMatcher;

public abstract class BaseMatcher implements PackMatcher {
    protected PackChecker checker;
    protected Card[] cards;
    protected Card[] sortedCards;
    
    @Override
    public void cards(Card[] cards) {
        this.cards = cards;
    }
    
    @Override
    public void checker(PackChecker checker) {
        this.checker = checker;
    }
    
    @Override
    public int indexes() {
        return 1 << 4 | 1 << 3 | 1 << 2 | 1 << 1 | 1 << 0;
    }
    
    @Override
    public int[] ranks() {
        int i = 0;
        int[] rankIds = new int[cards.length];
        for(Card card : cards) {
            rankIds[i++] = card.getRank().getValue();
        }
        return rankIds;
    }    

    protected int indexes(int... expectedRankCounts) {
        int indexs = 0;
        Rank before = null;
        for(int count : expectedRankCounts) {
            int realCount = count - checker.getSpecialCardCount();
            Rank rank =  checker.findRank(realCount, before);
            for(int i = 0 ; i < cards.length ; i++) {
                Rank cardRank = cards[i].getRank();
                if(cardRank == before)
                    continue;
                if(cardRank == rank
                        || cardRank == Rank.JOKER
                        || cardRank == Rank.WILD)
                    indexs |= 1 << i;
            }
            before = rank;
        }
        return indexs;
    }
    
    public int[] ranks(int... expectedRankCounts) {
        int[] rankIds = new int[expectedRankCounts.length];
        Rank before = null;
        for(int i = 0 ; i < rankIds.length ; i++) {
            Rank rank = checker.findRank(expectedRankCounts[i] 
                    - checker.getSpecialCardCount(), before);
            rankIds[i] = rank.getValue();
            before = rank;
        }
        return rankIds;
    }
}
