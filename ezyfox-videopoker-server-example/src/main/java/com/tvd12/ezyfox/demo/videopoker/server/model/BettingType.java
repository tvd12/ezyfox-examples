/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tavandung12
 *
 */
@Data
@EqualsAndHashCode(of = "id")
public class BettingType {

    private int id;
    private long money;
    
    public BettingType() {
        this(0, 10000);
    }
    
    public BettingType(int id) {
        this(id, 10000);
    }
    
    public BettingType(int id, long money) {
        this.id = id;
        this.money = money;
    }
    
}
