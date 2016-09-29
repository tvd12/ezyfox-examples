/**
 * 
 */
package com.tvd12.ezyfox.videopoker.test;

import java.util.List;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.reflect.ReflectPackageUtil;

/**
 * @author tavandung12
 *
 */
public class ReflectionTest {
    
    public static void main(String[] args) {
        List<Class<?>> classes = ReflectPackageUtil.findClasses(new String[] {"com.tvd12.ezyfox.videopoker"}, RoomContextConfiguration.class);
        System.out.println(classes);
    }
    

}
