/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.client.handler;

import com.tvd12.ezyfox.demo.videopoker.client.AppInput;

/**
 * @author tavandung12
 *
 */
public class AppTest {

    private AppTest() {}
    
    public static void test() {
        new Thread() {
            public void run() {
                while(true) {
                    System.out.println("\nfunctions:\n" + 
                            "1. send private message\n" + 
                            "2. send public message\n" + 
                            "3. send buddy message\n" +
                            "0. exit");
                    System.out.println("\n");
                    System.out.print("you select: ");
                    int value = AppInput.nextInt();
                    switch (value) {
                    case 1:
                        RequestUtil.sendPrivateMessage("dungtv14");
                        break;
                    case 2:
                        RequestUtil.sendPublicMessage();
                        break;
                    case 3:
                        RequestUtil.sendBuddyMessage("dungtv14");
                        break;
                    case 0:
                        RequestUtil.joinGameRoom("vjob");
                        break;
                    default:
                        break;
                    }
                }
            }
        }.start();
        
    }
    
}
