/**
 * 
 */
package com.tvd12.ezyfox.videopoker.config;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;

/**
 * @author tavandung12
 *
 */
@PackagesScan(packages = {
        "com.tvd12.ezyfox.videopoker.request"
})
@AdditionalClientRequestListeners({
        "com.tvd12.ezyfox.videopoker.interceptor.BetRequestInterceptor"})
@RoomContextConfiguration
public class RoomConfig {

}
