/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.model;

import lombok.Getter;

/**
 * @author tavandung12
 *
 */
public enum TurnResult {

    WIN(0), SAME(1), LOSE(2);
    
    @Getter
    private int value;
    
    private TurnResult(int value) {
        this.value = value;
    }
    
    public static TurnResult valueOf(int value) {
        for(TurnResult r : values()) {
            if(r.getValue() == value)
                return r;
        }
        throw new IllegalStateException("Has no turn result with id = " + value);
    }
}
