package com.geovisearth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class PaymentSign extends CloudApiSign {
    public PaymentSign(
            String secretId,
            String secretKey,
            String mchid) throws InvalidKeyException, NoSuchAlgorithmException {
        super(secretId, secretKey, "geovis-payment-center");
        String[][] ext = { { "mchid", mchid } };
        this.setExtendKeys(ext);
    }

    public String checkCallback(
            String jsonData,
            String notifyTime,
            String sign,
            String status) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(jsonData);
        sb.append("\n");
        sb.append(notifyTime);
        String checkStr = createSign(sb.toString());
        if (sign != checkStr) {
            throw new Exception("invalid signature");
        }
        return jsonData;
    }
}