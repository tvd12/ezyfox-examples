/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.demo.videopoker.builder.PackMatcherChain;
import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.model.Hand;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;
import com.tvd12.ezyfox.demo.videopoker.model.PayTable;
import com.tvd12.ezyfox.demo.videopoker.server.model.AppSingleton;
import com.tvd12.ezyfox.demo.videopoker.server.model.BettingType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tavandung12
 *
 */
@RoomAgent
@ResponseParams
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoPokerRoom extends ApiRoom {

    @Setter @Getter
    public int typeId = 0;
    
    @Variable(name = "1", visible = true)
    @Setter @Getter
    private long jackpotMoney = 100000;

    @ResponseParam("1")
    @Setter @Getter
    private PayTable payTable;
    
    @Getter
    @JsonIgnore
    private Map<Integer, BettingType> bettingTypes;
    
    public VideoPokerRoom() {
        setGame(true);
    }
    
    @ResponseParam("2")
    public Collection<BettingType> bettingTypes() {
        return bettingTypes.values();
    }
    
    @JsonProperty("bettingTypes")
    public void setBettingTypes(BettingType[] types) {
        bettingTypes = new HashMap<>();
        for(BettingType type : types)
            bettingTypes.put(type.getId(), type);
    }
    
    public BettingType getBettingType(int id) {
        return bettingTypes.get(id);
    }
    
    public PackMatcherChain matcherChain() {
        return AppSingleton.chain(payTable);
    }
    
    public List<Card> cardPack() {
        return AppSingleton.cardPack(typeId);
    }
    
    public void increaseJackpotMoney(long offset) {
        setJackpotMoney(getJackpotMoney() + offset);
    }
    
    public void resetJackpotMoney() {
        setJackpotMoney(0);
    }
    
    public Hand getHandByPack(Pack pack) {
        return payTable.getHandByPack(pack);
    }
}
