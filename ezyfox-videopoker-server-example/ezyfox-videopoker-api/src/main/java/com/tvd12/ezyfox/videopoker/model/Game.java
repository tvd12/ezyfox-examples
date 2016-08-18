/**
 * 
 */
package com.tvd12.ezyfox.videopoker.model;

import java.util.ArrayList;
import java.util.List;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.videopoker.builder.PackMatcherChain;
import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.util.CardUtil;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ResponseParams
public class Game {

    private Card[] cards;
    private Card[] drawCards;
    private Card[] dealCards;
    private GameState state;
    private Result result;
    private List<DoubleTurn> doubleTurns
            = new ArrayList<>();
    
    public void draw(List<Card> cardPack) {
        drawCards = CardUtil.random(cardPack, 5);
        cards = drawCards;
    }
    
    public void deal(List<Card> cardPack, 
            int selectedIndexes, 
            PackMatcherChain chain) {
        dealCards = CardUtil.random(cardPack, drawCards, 5);
        for(int i = 0 ; i < dealCards.length ; i++) {
            if(((1 << i) & selectedIndexes) != 0) {
                dealCards[i] = drawCards[i];
            }
        }
        cards = dealCards;
        result = chain.cards(dealCards).check();
    }
    
    public DoubleTurn startDoubleTurn() {
        DoubleTurn turn = new DoubleTurn();
        doubleTurns.add(turn);
        turn.start();
        return turn;
    }
    
    public DoubleTurn finishDoubleTurn(int selectedIndex) {
        DoubleTurn turn = doubleTurns.get(doubleTurns.size() - 1);
        turn.finish(selectedIndex);
        return turn;
    }
    
    @ResponseParam("1")
    public int[] cardIds() {
        return CardUtil.getCardIds(cards);
    }
    
    @ResponseParam("2")
    public Result result() {
        return result;
    }
}
