package com.itpk.pkapi_interface;

import com.itpk.pkapiclientsdk.config.PkAPIClient;
import com.itpk.pkapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.annotation.Resources;

@SpringBootTest
class PkApiInterfaceApplicationTests {

    @Resource
    private PkAPIClient pkAPIClient;
    @Test
    void testPkApiInterface() {
        String GetName=pkAPIClient.NameByGet("GETName");
        User user=new User();
        user.setName("liyu");
        String JsonName=pkAPIClient.NameByPostJson(user);
        System.out.println(JsonName);
        System.out.println(GetName);
    }

}
