/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tvd12.ezyfox.core.annotation.ResponseParams;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ResponseParams
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayTable {

    private Hand[] hands;
    
    public Hand getHandByPack(Pack pack) {
        for(Hand hand : hands) {
            if(hand.getPack() == pack)
                return hand;
        }
        throw new IllegalStateException("No hand with pack " + pack);
    }
    
}
