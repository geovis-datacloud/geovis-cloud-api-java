package com.geovisearth;

import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void TestPaymentSign() {
        try {
            PaymentSign paymentSign = new PaymentSign(
                    "secretId",
                    "oL9Ebg3ofo9pyaB42KMokWzc0SMQQeLjFkVF33U6N1M=",
                    "mchid");
            String str = paymentSign.createCloudApiHmacSign(
                    "POST",
                    "/v1/access/prepay",
                    "",
                    "{\"orderNo\":\"409327485512468136749254027\",\"productName\":\"测试验签工具\",\"total\":10000,\"payMode\":\"wxpay\",\"payChannel\":\"NATIVE\",\"callbackUrl\":\"http://www.baidu.com?ad=12\"}",
                    1698911283190l);
            System.out.println(str);
            str = paymentSign.createCloudApiHmacSign(
                    "POST",
                    "/v1/access/prepay",
                    "",
                    "{\"orderNo\":\"409327485512468136749254027\",\"productName\":\"测试验签工具\",\"total\":10000,\"payMode\":\"wxpay\",\"payChannel\":\"NATIVE\",\"callbackUrl\":\"http://www.baidu.com?ad=12\"}",
                    1698911283190l);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
