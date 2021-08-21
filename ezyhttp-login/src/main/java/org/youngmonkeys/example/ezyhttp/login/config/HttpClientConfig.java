package org.youngmonkeys.example.ezyhttp.login.config;

import com.tvd12.ezyfox.bean.annotation.EzyConfigurationBefore;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.client.HttpClientProxy;

@EzyConfigurationBefore
public class HttpClientConfig {

    @EzySingleton
    public HttpClientProxy httpClientProxy() throws Exception {
        HttpClientProxy httpClientProxy = HttpClientProxy.builder()
            .build();
        httpClientProxy.start();
        return httpClientProxy;
    }
}
