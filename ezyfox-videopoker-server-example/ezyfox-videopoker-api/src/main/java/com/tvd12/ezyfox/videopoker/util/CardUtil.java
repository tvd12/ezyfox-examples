package com.tvd12.ezyfox.videopoker.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.tvd12.ezyfox.videopoker.card.Card;

public class CardUtil {

    //Prevent new instance
    private CardUtil() {}
    
    /**
     * 
     * Random number of cards
     * 
     * @param numberOfCards number of cards that you want to random
     * @return array of cards
     */
    public static Card[] random(List<Card> cards, int numberOfCards) {
        return random(cards, new Card[0], numberOfCards);
    }
    
    public static Card[] random(List<Card> cards, Card[] except, int numberOfCards) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.removeAll(Arrays.asList(except));
        
        int indexes[] = indexes(newCards);
        
        Random random = new Random();
        int remain = indexes.length, i = 0;
        
        Card[] result = new Card[numberOfCards];
        
        while((numberOfCards --) > 0) {
            int index = random.nextInt(remain);
            result[i++] = newCards.get(indexes[index]);
            swapIndexes(indexes, index, --remain);
        }
        return result;
    }
    
    /**
     * Random a card from 52 cards
     * 
     * @return a card
     */
    public static Card random(List<Card> cards) {
        Random random = new Random();
        return cards.get(random.nextInt(52));
    }
    
    /**
     * Sort array of cards
     * 
     * @param cards array of cards
     */
    public static void sort(Card[] cards) {
        Arrays.sort(cards, (Card a, Card b) -> {
                int result = a.getRank().compareTo(b.getRank());
                return (result == 0) ? a.getSuit().compareTo(b.getSuit()) : result;
        });
    }
    
    /**
     * Sort cards by their rank
     * 
     * @param cards array of cards
     */
    public static void sortByRank(Card[] cards) {
        Arrays.sort(cards, (Card a, Card b) -> {
                return a.getRank().compareTo(b.getRank());
        });
    }
    
    private static void swapIndexes(int[] indexes, int from, int to) {
        int temp = indexes[from];
        indexes[from] = indexes[to];
        indexes[to] = temp;
    }
    
    /**
     * Create temporary indexes of 52 cards
     * 
     * @return indexes
     */
    private static int[] indexes(List<Card> cards) {
        int[] indexes = new int[cards.size()];
        for(int i = 0 ; i < indexes.length ; i++) {
            indexes[i] = i;
        }
        return indexes;
    }
    
    public static Card getCard(List<Card> cards, int id) {
        for(Card card : cards) {
            if(card.getId() == id)
                return card;
        }
        throw new RuntimeException("Has no cards with id = " + id);
    }
    
    public static Card[] getCard(List<Card> cards, int ids[]) {
        Card[] result = new Card[ids.length];
        for(int i = 0 ; i < ids.length ; i++)
            result[i] = getCard(cards, ids[i]);
        return result;
    }
    
    public static int[] getCardIds(Card[] cards) {
        if(cards == null)
            return null;
        int[] carIds = new int[cards.length];
        for(int i = 0 ; i < cards.length ; i++)
            carIds[i] = cards[i].getId();
        return carIds;
    }
    
    public static String toString(List<Card> cards, int ids[]) {
        Card[] array = getCard(cards, ids);
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < ids.length ; i++)
            builder.append(array[i])
                .append(i < ids.length - 1 ? ", " : "");
        return builder.toString();
    }
    
    public static String toString(List<Card> cards) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < cards.size() ; i++)
            builder.append(cards.get(i))
                .append(i < cards.size() - 1 ? ", " : "");
        return builder.toString();
    }
    
    public static String toString(Card[] cards) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < cards.length ; i++)
            builder.append(cards[i])
                .append(i < cards.length - 1 ? ", " : "");
        return builder.toString();
    }
    
}
