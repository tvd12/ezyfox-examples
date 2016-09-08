/**
 * 
 */
package com.tvd12.ezyfox.videopoker;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.PackagesScan;

/**
 * @author tavandung12
 *
 */
@PackagesScan(packages = {
        "com.tvd12.ezyfox.videopoker.request"})
@AdditionalClientRequestListeners({
        "com.tvd12.ezyfox.videopoker.interceptor.BetRequestInterceptor"})
public class RoomConfig {

}
