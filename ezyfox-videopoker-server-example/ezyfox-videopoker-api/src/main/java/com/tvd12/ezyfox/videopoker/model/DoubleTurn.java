/**
 * 
 */
package com.tvd12.ezyfox.videopoker.model;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.util.CardUtil;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ResponseParams
public class DoubleTurn {

    private Card firstCard;
    private Card[] nextCards;
    private Card selectedCard;
    private TurnResult result;
    private int selectedIndex;
    private long money;
    
    public void start() {
        setFirstCard(CardUtil.random(Card.NORMAL_CARDS));
    }
    
    public void finish(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        setNextCards(CardUtil.random(
                Card.NORMAL_CARDS, 
                new Card[] {firstCard}, 
                4));
        check();
        calculateMoney();
    }
    
    public void check() {
        selectedCard = nextCards[selectedIndex - 1];
        int compareResult = firstCard.getRank().compare(selectedCard.getRank());
        if(compareResult == 0)
            result = TurnResult.SAME;
        else if(compareResult > 0)
            result = TurnResult.LOSE;
        else
            result = TurnResult.WIN;
    }
    
    public void calculateMoney() {
        switch (result) {
        case WIN:
            money += money;
            break;
        case LOSE:
            money = 0;
            break;
        default:
            break;
        }
    }
    
    @ResponseParam("0")
    public long money() {
        return money;
    }
    
    @ResponseParam("1")
    public int firstCardId() {
        return firstCard.getId();
    }
    
    @ResponseParam("2")
    public int[] nextCardIds() {
        return CardUtil.getCardIds(nextCards);
    }
    
    @ResponseParam("3")
    public Integer selectedIndex() {
        if(selectedCard == null)
            return null;
        return selectedIndex;
    }
    
    @ResponseParam("4")
    public Integer result() {
        if(result == null)
            return null;
        return result.getValue();
    }
    
}
