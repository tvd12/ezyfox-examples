/**
 * 
 */
package com.tvd12.ezyfox.videopoker.config;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.annotation.RoomPackages;

/**
 * @author tavandung12
 *
 */
@PackagesScan(packages = {
        "com.tvd12.ezyfox.videopoker.entities", 
        "com.tvd12.ezyfox.videopoker.event",
        "com.tvd12.ezyfox.videopoker.model"
        })
@RoomPackages(packages = {
        "com.tvd12.ezyfox.videopoker"
})
@AppContextConfiguration
public class AppConfig {

}
