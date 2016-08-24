/**
 * 
 */
package com.tvd12.ezyfox.videopoker.client.handler;

import java.util.Collection;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.tvd12.ezyfox.videopoker.card.Card;
import com.tvd12.ezyfox.videopoker.client.AppInput;
import com.tvd12.ezyfox.videopoker.model.Pack;
import com.tvd12.ezyfox.videopoker.model.TurnResult;
import com.tvd12.ezyfox.videopoker.util.CardUtil;

/**
 * @author tavandung12
 *
 */
public class ExtensionResponseListener extends BaseEventAdapter {

    @Override
    public void dispatch(String command, ISFSObject params) {
        System.out.println("extension response command = " + command + ", params = \n" + params.toJson());
        if(command.equals("___err___")) {
            System.err.println("error :[" + params.getInt("code") + "], " + params.getUtfString("msg"));
            System.out.print("bet with id: "); 
            int id = AppInput.nextInt();
            RequestUtil.bet(id);
        }
        else if(command.equals("1")) {
            logGameInformation(params);
            System.out.print("bet with id: "); 
            int id = AppInput.nextInt();
            RequestUtil.bet(id);
        }
        else if(command.equals("2")) {
            System.out.print("bet or draw? (b/d): ");
            char ch = AppInput.nextChar();
            if(ch == 'b') {
                System.out.print("bet with id: "); 
                int id = AppInput.nextInt();
                RequestUtil.bet(id);
            }
            else {
                RequestUtil.draw();
            }
//            SmartFoxConfig.getInstance().getSmartFox().killConnection();
                
        }
        else if(command.equals("3")) {
            Collection<Integer> cardIds = params.getIntArray("1");
            System.out.println(CardUtil.toString(Card.NORMAL_CARDS, convert(cardIds)));
            System.out.print("hold or continues? (h/c): ");
            char ch = AppInput.nextChar();
            if(ch == 'h') {
                System.out.print("hold: ");
                String str = AppInput.nextString();
                int indexes = parseIndexes(str);
                RequestUtil.deal(indexes);
            }
            else {
                RequestUtil.deal(0);
            }
            
        }
        else if(command.equals("4")) {
            Collection<Integer> cardIds = params.getIntArray("1");
            System.out.println(CardUtil.toString(Card.NORMAL_CARDS, convert(cardIds)));
            int resultPackId = params.getSFSObject("2").getInt("3");
            Pack pack = Pack.valueOf(resultPackId);
            if(pack == Pack.NONE) {
                System.out.print("bet with id: "); 
                new Thread() {
                    public void run() {
                        int id = AppInput.nextInt();
                        RequestUtil.bet(id);
                    }
                }.start();;
                return;
            }
            System.out.println("Double or Nothing? (d/n)");
            char ch = AppInput.nextChar();
            if(ch == 'd') {
                RequestUtil.selectDouble();
            }
            else {
                RequestUtil.nothing();
            }
            
        }
        else if(command.equals("5")) {
            int firstCardId = params.getInt("1");
            System.out.println("first card is: " + CardUtil.toString(Card.NORMAL_CARDS, new int[] {firstCardId}));
            System.out.println("Select a index: ");
            int index = AppInput.nextInt();
            RequestUtil.dealInDouble(index);
        }
        else if(command.equals("6")) {
            System.out.print("bet with id: "); 
            int id = AppInput.nextInt();
            RequestUtil.bet(id);
        }
        else if(command.equals("7")) {
            int firstCardId = params.getInt("1");
            int[] nextCardIds = convert(params.getIntArray("2"));
            int selectedCard = nextCardIds[params.getInt("3") - 1];
            TurnResult result = TurnResult.valueOf(params.getInt("4"));
            System.out.println("first card is: " + CardUtil.toString(Card.NORMAL_CARDS, new int[] {firstCardId}));
            System.out.println("nextd cards is: " + CardUtil.toString(Card.NORMAL_CARDS, nextCardIds));
            System.out.println("selected card is: " + CardUtil.toString(Card.NORMAL_CARDS, new int[] {selectedCard}));
            System.out.println("result is: " + result);
            if(result == TurnResult.LOSE) {
                System.out.print("bet with id: "); 
                int id = AppInput.nextInt();
                RequestUtil.bet(id);
            }
            else {
                System.out.println("Double or Nothing? (d/n)");
                char ch = AppInput.nextChar();
                if(ch == 'd') {
                    RequestUtil.selectDouble();
                }
                else {
                    RequestUtil.nothing();
                }
            }
        }
    }
    
    public void logGameInformation(ISFSObject params) {
        ISFSObject payTable = params.getSFSObject("1");
        ISFSArray bettingTypeIds = params.getSFSArray("2");
        System.out.println(payTable);
        System.out.println(bettingTypeIds);
    }
    
    private int[] convert(Collection<Integer> ids) {
        int i = 0;
        int[] answer = new int[ids.size()];
        for(Integer id : ids) {
            answer[i ++] = id;
        }
        return answer;
    }
    
    public int parseIndexes(String str) {
        String[] strs = str.split(",");
        int indexes = 0;
        for(int i = 0 ; i < strs.length ; i++) {
            int index = Integer.valueOf(strs[i]);
            indexes |= 1 << index;
        }
        return indexes;
    }
}
