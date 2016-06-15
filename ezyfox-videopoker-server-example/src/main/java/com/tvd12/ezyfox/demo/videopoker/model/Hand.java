/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
@Data
@ResponseParams
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hand {
    
    @ResponseParam("1")
    private int id;
    
    @ResponseParam("2")
    private double payout;
    
    @ResponseParam("3")
    private int[] cardIds;
    
    @ResponseParam("4")
    private String name;
    
    private Pack pack;
    
    public void setId(int id) {
        this.id = id;
        this.pack = Pack.valueOf(id);
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
