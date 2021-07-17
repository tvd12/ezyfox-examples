package org.youngmonkeys.example.ezyhttp.login.test;

import com.tvd12.ezyfox.tool.EzySQLTableCreator;
import com.tvd12.ezyfox.tool.data.EzyCaseType;
import org.youngmonkeys.example.ezyhttp.login.entity.UserInformation;

public class CreateTable {

    public static void main(String[] args) {
        EzySQLTableCreator creator = new EzySQLTableCreator(
            UserInformation.class,
            EzyCaseType.NORMAL
        );
        System.out.println(creator.createScript());
    }
}
