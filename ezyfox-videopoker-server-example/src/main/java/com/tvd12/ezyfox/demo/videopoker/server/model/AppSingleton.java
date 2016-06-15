/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.demo.videopoker.builder.PackMatcherChain;
import com.tvd12.ezyfox.demo.videopoker.card.Card;
import com.tvd12.ezyfox.demo.videopoker.matcher.PackChecker;
import com.tvd12.ezyfox.demo.videopoker.matcher.PackMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FiveOfAKindMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FourOfAKindMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.FullHouseMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.JacksOrBetterMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.KingsOrBetterMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.RoyalFlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.StraightMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.ThreeOfAKindMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.TwoPairMatcher;
import com.tvd12.ezyfox.demo.videopoker.matcher.impl.WildRoyalFlushMatcher;
import com.tvd12.ezyfox.demo.videopoker.model.Hand;
import com.tvd12.ezyfox.demo.videopoker.model.Pack;
import com.tvd12.ezyfox.demo.videopoker.model.PayTable;

/**
 * @author tavandung12
 *
 */
public final class AppSingleton {
    
    private static final Map<Pack, Class<?>> PACK_MATCHERS 
            = packMatchers();
    private static final Map<Integer, List<Card>> CARD_PACKS
            = cardPacks();

    private AppSingleton() {}
    
    public static PackMatcherChain chain(PayTable table) {
        List<Class<?>> classes = new ArrayList<>();
        for(Hand hand : table.getHands()) {
            Class<?> clazz = PACK_MATCHERS.get(hand.getPack());
            if(clazz != null) classes.add(clazz);
        }
        PackMatcherChain chain = new PackMatcherChain()
                .checker(new PackChecker());
        try {
            for(Class<?> clazz : classes) {
                chain.matcher((PackMatcher) 
                        ReflectClassUtil.newInstance(clazz));
            }
        }
        catch(ExtensionException e) {
        }
        return chain;
    }
    
    public static List<Card> cardPack(int typeId) {
        return CARD_PACKS.get(typeId);
    }
    
    private static Map<Pack, Class<?>> packMatchers() {
        Map<Pack, Class<?>> answer = new HashMap<>();
        answer.put(Pack.ROYAL_FLUSH, RoyalFlushMatcher.class);
        answer.put(Pack.STRAIGHT_FLUSH, StraightMatcher.class);
        answer.put(Pack.FOUR_OF_A_KIND, FourOfAKindMatcher.class);
        answer.put(Pack.FULL_HOUSE, FullHouseMatcher.class);
        answer.put(Pack.FLUSH, FlushMatcher.class);
        answer.put(Pack.STRAIGHT, StraightMatcher.class);
        answer.put(Pack.THREE_OF_A_KIND, ThreeOfAKindMatcher.class);
        answer.put(Pack.TWO_PAIRS, TwoPairMatcher.class);
        answer.put(Pack.JACK_OR_BETTER, JacksOrBetterMatcher.class);
        answer.put(Pack.FIVE_OF_A_KIND, FiveOfAKindMatcher.class);
        answer.put(Pack.WILD_ROYAL_FLUSH, WildRoyalFlushMatcher.class);
        answer.put(Pack.KING_OR_BETTER, KingsOrBetterMatcher.class);
        return answer;
    }
    
    private static Map<Integer, List<Card>> cardPacks() {
        Map<Integer, List<Card>> answer = new HashMap<>();
        answer.put(0, Card.NORMAL_CARDS);
        answer.put(1, Card.NORMAL_JOKER_CARDS);
        answer.put(2, Card.NORMAL_WILDS_CARDS);
        answer.put(3, Card.NORMAL_WILDS_JOKER_CARDS);
        return answer;
    }
}
