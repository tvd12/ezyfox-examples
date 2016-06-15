package com.tvd12.ezyfox.demo.videopoker.model;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;

import lombok.Getter;
import lombok.Setter;

@ResponseParams
public class Result {

    @Getter
    private Pack pack;
    
    @ResponseParam("1")
    @Getter @Setter
    private int indexes;
    
    @ResponseParam("2")
    @Getter @Setter
    private int[] rankIds;
    
    public Result(Pack pack, int indexes, int[] rankIds) {
        this.pack = pack;
        this.indexes = indexes;
        this.rankIds = rankIds;
    }
    
    @ResponseParam("3")
    public int getPackId() {
        return pack.getValue();
    }
}
