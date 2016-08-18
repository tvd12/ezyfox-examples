package com.tvd12.ezyfox.videopoker.matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.card.Rank;

import lombok.Getter;

public class PackChecker {
    
    protected Card[] cards;
    
    @Getter
    protected boolean straight;
    @Getter
    protected boolean flush;
    @Getter
    protected boolean fourCards;
    @Getter
    protected boolean fullHouse;
    @Getter
    protected boolean threeCards;
    @Getter
    protected boolean twoPair;
    @Getter
    protected boolean jacksOrBetter;
    @Getter
    protected int specialCardCount;
    
    public void check(Card[] cards) {
        this.cards = cards;
        this.init();
        this.straight = straight();
        this.flush = flush();
        this.fourCards = fourCards();
        this.fullHouse = fullhouse();
        this.threeCards = threeCards();
        this.twoPair = twoPair();
        this.jacksOrBetter = better(Rank.JACK);
    }
    
    protected void init() {}
    
    public boolean isNature() {
        return getSpecialCardCount() == 0;
    }
    
    public boolean isNatureStraight() {
        int length = cards.length;
        if(cards[0].getRank() == Rank.TWO
                && cards[cards.length - 1].getRank() == Rank.ACE)
            length = cards.length - 1;
        Card card = cards[0];
        for(int i = 1 ; i < length ; i++) { 
            if(!cards[i].getRank().isNext(card.getRank())) 
                return false;
            card = cards[i];
        }
        return true;
    }
    
    public boolean isNatureFlush() {
        Card card = cards[0];
        for(int i = 1 ; i < cards.length ; i++) { 
            if(cards[i].getSuit() !=  card.getSuit()) 
                return false;
            card = cards[i];
        }
        return true;
    }
    
    public boolean isNatureFourCards() {
        return checkRanks(4);
    }
    
    public boolean isNatureThreeCards() {
        return checkRanks(3);
    }

    protected boolean straight() {
        return isNatureStraight();
    }
    
    protected boolean flush() {
        return isNatureFlush();
    }
    
    protected boolean fourCards() {
        return isNatureFourCards();
    }
    
    protected boolean fullhouse() {
        return rankSet().size() == 2;
    }
    
    protected boolean threeCards() {
        return isNatureThreeCards();
    }
    
    protected boolean twoPair() {
        return rankSet().size() == 3;
    }
    
    public boolean better(Rank rank) {
        return findBetterRank(rank) != null;
            
    }
    
    protected Rank findBetterRank(Rank rank) {
        List<Rank> rankSet = new ArrayList<>(rankSet());
        if(rankSet.size() != 4)
            return null;
        List<Rank> rankList = rankList();
        Collections.sort(rankList);
        for(int i = 0 ; i < rankList.size() && specialCardCount == 1 ; i++) {
            if(rankList.get(i).compare(rank) >= 0)
                return rankList.get(i);
        }
        for(int i = 0 ; i < rankList.size() - 1 ; i++) {
            if((rankList.get(i) == rankList.get(i + 1))
                    && (rankList.get(i).compare(rank) >= 0))
                return rankList.get(i);
        }
        return null;
    }
    
    protected boolean checkRanks(int expectedCount) {
        return findRank(expectedCount) != null;
    }
    
    public Rank findRank(int expectedCount) {
        return findRank(expectedCount, null);
    }
    
    public Rank findRank(int expectedCount, Rank except) {
        if(expectedCount == 1) return maxRank(except);
        int i = 0;  Card card = cards[0];
        for(; i < cards.length ; i++) {
            if(cards[i].getRank() == except)    
                continue;
            card = cards[i];    break;
        }
        int count = 1;
        for(++i; i < cards.length ; i++) {
            if(cards[i].getRank() == except)    
                continue;
            if(card.getRank() == cards[i].getRank()) {
                count ++;
            } else {
                card = cards[i];    count = 1;
            }
            if(count == expectedCount)
                return card.getRank();
        }
        return null;
    }
    
    protected Set<Rank> rankSet() {
        Set<Rank> ranks = new HashSet<>();
        for(Card card : cards)
            ranks.add(card.getRank());
        return ranks;
    }
    
    protected List<Rank> rankList() {
        List<Rank> ranks = new ArrayList<>();
        for(Card card : cards)
            ranks.add(card.getRank());
        return ranks;
    }
    
    protected boolean contains(Rank rank) {
        for(Card card : cards)
            if(card.getRank() == rank)
                return true;
        return false;
    }
    
    protected boolean isSpecialCard(Rank rank) {
        return rank == Rank.JOKER
                || rank == Rank.WILD;
    }
    
    protected boolean isSpecialCard(Card card) {
        return isSpecialCard(card.getRank());
    }
    
    public Rank maxRank() {
        return maxRank(null);
    }
    
    public Rank maxRank(Rank except) {
        Card maxCard = cards[0];
        for(int i = 1 ; i < cards.length ; i++) {
            if(cards[i].getRank() == except)
                continue;
            if(maxCard.getRank() == except ||
                    cards[i].getRank().compare(maxCard.getRank()) > 0)
                maxCard = cards[i];
        }
        return maxCard.getRank();
    }
}
