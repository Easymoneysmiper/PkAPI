package com.itpk.pkapiclientsdk.Utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.Arrays;

public  class signUtil {
    public static String genSign(String body, String secretKey) {
        Digester md5=new Digester(DigestAlgorithm.SHA256);
        String content = body+"."+secretKey;
        return Arrays.toString(md5.digest(content));
    }
}
