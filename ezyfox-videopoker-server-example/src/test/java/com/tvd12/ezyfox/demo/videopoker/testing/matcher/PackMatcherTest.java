package com.tvd12.ezyfox.demo.videopoker.testing.matcher;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tvd12.ezyfox.demo.videopoker.builder.PackMatcherChain;
import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.matcher.PackChecker;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FourOfAKindMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FullHouseMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.JacksOrBetterMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.RoyalFlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.StraightFlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.StraightMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.ThreeOfAKindMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.TwoPairMatcher;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;
import com.tvd12.ezyfox.demo.videopoker.model.Result;
import com.tvd12.ezyfox.demo.videopoker.util.CardUtil;

public class PackMatcherTest {
    
    private PackMatcherChain builer;
    
    public PackMatcherTest() {
        builer = new PackMatcherChain()
            .matcher(new RoyalFlushMatcher())
            .matcher(new StraightFlushMatcher())
            .matcher(new FourOfAKindMatcher())
            .matcher(new FullHouseMatcher())
            .matcher(new FlushMatcher())
            .matcher(new StraightMatcher())
            .matcher(new ThreeOfAKindMatcher())
            .matcher(new TwoPairMatcher())
            .matcher(new JacksOrBetterMatcher())
            .checker(new PackChecker());
    }
    
    private Card[] normal(int... ids) {
        return CardUtil.getCard(Card.NORMAL_CARDS, ids);
    }
    
    public void normalAssertEquals(Pack pack, int indexes, int[] ranks, int... ids) {
        System.out.println(CardUtil.toString(CardUtil.getCard(Card.NORMAL_CARDS, ids)));
        Result result = builer.cards(normal(ids)).check();
        assertEquals(result.getPack(), pack);
        assertEquals(result.getIndexes(), indexes);
        assertArrayEquals(result.getRankIds(), ranks);
    }

    @Test
    public void test() {
        //royal flush
        normalAssertEquals(Pack.ROYAL_FLUSH,
                31,
                new int[] {8, 9, 10, 11, 12},
                8, 9, 10, 11, 12);
        normalAssertEquals(Pack.STRAIGHT_FLUSH,
                31,
                new int[] {0, 1, 2, 3, 12},
                0, 1, 2, 3, 12);
        normalAssertEquals(Pack.STRAIGHT_FLUSH,
                31,
                new int[] {8, 9, 10, 11, 12},
                21, 22, 23, 24, 25);
        normalAssertEquals(Pack.FOUR_OF_A_KIND,
                15, 
                new int[] {0},
                0, 13, 26, 39, 1);
        normalAssertEquals(Pack.FULL_HOUSE,
                31,
                new int[] {0, 1},
                0, 13, 26, 1, 14);
        normalAssertEquals(Pack.FLUSH,
                31,
                new int[] {0, 1, 5, 6, 7},
                0, 1, 5, 6, 7);
        normalAssertEquals(Pack.STRAIGHT,
                31,
                new int[] {0, 1, 2, 3, 4},
                0, 14, 2, 3, 4);
        normalAssertEquals(Pack.THREE_OF_A_KIND,
                7,
                new int[] {0},
                0, 13, 26, 1, 2);
        normalAssertEquals(Pack.TWO_PAIRS,
                15,
                new int[] {0, 1},
                0, 13, 1, 14, 3);
        normalAssertEquals(Pack.JACK_OR_BETTER,
                24,
                new int[] {10},
                0, 2, 3, 10, 23);
        
        normalAssertEquals(Pack.NONE,
                0,
                new int[0],
                0, 2, 3, 11, 23);
        
        normalAssertEquals(Pack.NONE,
                0,
                new int[0],
                0, 2, 3, 16, 23);
        
        normalAssertEquals(Pack.NONE,
                0,
                new int[0],
                14, 1, 25, 8, 24);
        
        normalAssertEquals(Pack.NONE,
                0,
                new int[0],
                25, 8, 24, 14, 1);
        
    }
    
}
