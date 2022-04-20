package org.youngmonkeys.example.ezyhttp.website;

import com.tvd12.ezyhttp.core.boot.EzyHttpApplicationBootstrap;
import com.tvd12.ezyhttp.server.core.asm.RequestHandlerImplementer;

public class App {
    public static void main(String[] args) throws Exception {
        RequestHandlerImplementer.setDebug(true);
        EzyHttpApplicationBootstrap.start(App.class);
    }

}
