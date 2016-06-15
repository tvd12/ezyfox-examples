/**
 * 
 */
package com.puppet.videopoker.client;

/**
 * @author tavandung12
 *
 */
public class ClassC {
    
    public static void getValue() {
        ClassB.getValue();
    }

    public static void main(String[] args) {
        try {
            getValue();
        } catch(Exception e) {
//            e.printStackTrace();
        }
    }
    
}
