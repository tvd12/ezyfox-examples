/**
 * 
 */
package com.tvd12.ezyfox.demo.videopoker.client;

import java.util.Scanner;

/**
 * @author tavandung12
 *
 */
public final class AppInput {

    private static final Scanner SCANNER = new Scanner(System.in);
    
    private AppInput() {}
    
    public static int nextInt() {
        return SCANNER.nextInt();
    }
    
    public static long nextLong() {
        return SCANNER.nextLong();
    }
    
    public static char nextChar() {
        return (char)SCANNER.next().charAt(0);
    }
    
    public static String nextString() {
        return SCANNER.next();
    }
}
