package com.tvd12.ezyfox.demo.videopoker.card;

import static com.tvd12.ezyfox.demo.videopoker.card.Rank.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * Represent to a card
 * 
 * @author tavandung12
 *
 */
@EqualsAndHashCode(of = {"id"})
public class Card {

    public static final List<Card> NORMAL_CARDS 
        = createNormalCards();
    public static final List<Card> NORMAL_JOKER_CARDS 
        = createNormalAndJokerCards();
    public static final List<Card> NORMAL_WILDS_CARDS 
        = createNormalAndWildsCards();
    public static final List<Card> NORMAL_WILDS_JOKER_CARDS
        = createNormalWildsAndJokerCards();
    
    @Getter
    private int id;

    /**
     * Rank of card (2 to ace)
     */
    @Getter
    private Rank rank;
    
    /**
     * Suit of card (heart, club, diamond, spade)
     */
    @Getter
    private Suit suit;

    private Card(int id, Rank rank, Suit suit) {
        this.id = id;
        this.rank = rank;
        this.suit = suit;
    }
    
    private static List<Card> createCards(Rank[] ranks) {
        int i = 0;
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : ranks) {
                cards.add(new Card(i++, rank, suit));
            }
        }
        return Collections.unmodifiableList(cards);
    }

    /**
     * Create 52 default cards, this list of card if read only
     * Anyone can't change or sort it
     * 
     * @return 52 cards
     */
    private static List<Card> createNormalCards() {
        return createCards(Rank.normal());
    }
    
    private static List<Card> createNormalAndJokerCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(createNormalCards());
        cards.add(new Card(cards.size(), Rank.JOKER, Suit.DIAMOND));
        return Collections.unmodifiableList(cards);
    }
    
    private static List<Card> createNormalAndWildsCards() {
        Rank[] ranks = new Rank[] {
                THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
                JACK, QUEEN, KING, ACE, WILD
        };
        return createCards(ranks);
    }
    
    private static List<Card> createNormalWildsAndJokerCards() {
        List<Card> cards = new ArrayList<>();
        cards.addAll(createNormalAndWildsCards());
        cards.add(new Card(cards.size(), Rank.JOKER, Suit.DIAMOND));
        return Collections.unmodifiableList(cards);
    }
    
    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
