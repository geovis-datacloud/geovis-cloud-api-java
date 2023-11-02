package com.geovisearth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CloudApiSign {
    protected String serviceName;
    protected String secretId;
    protected String secretKey;
    protected String[][] extendKeys = new String[0][0];

    protected void setExtendKeys(String[][] extendKeys) {
        this.extendKeys = extendKeys;
    }

    protected Mac macInstance;
    final char[] hexArray = "0123456789abcdef".toCharArray();

    public CloudApiSign(
            String secretId,
            String secretKey,
            String serviceName) throws NoSuchAlgorithmException, InvalidKeyException {
        this.serviceName = serviceName;
        this.secretId = secretId;
        this.secretKey = secretKey;
        // this.extendKeys = extendKeys;
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] keyBytes = decoder.decode(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        this.macInstance = mac;
    }

    public String createSign(String toSign) {
        byte[] bytes = macInstance.doFinal(toSign.getBytes());
        /************ 转换为十六进制字符串 ************/
        return bytesToHex(bytes);
    }

    public String createCloudApiHmacSign(
            String method,
            String path,
            String queryString,
            String body,
            long timestamp) throws Exception {
        String nonceStr = "IwvFjfGE86baEC5tg9k9I";
        // UUID.randomUUID().toString();
        /************ 拼接待签字符串 ************/
        StringBuilder sb = new StringBuilder();
        sb.append(serviceName);
        sb.append("\n");
        sb.append(method);
        sb.append("\n");
        sb.append(path);
        sb.append("\n");
        sb.append(queryString);
        sb.append("\n");
        sb.append(body);
        sb.append("\n");
        sb.append(nonceStr);
        sb.append("\n");
        sb.append(Long.toString(timestamp));
        /************ 转换为十六进制字符串 ************/
        String signature = createSign(sb.toString());
        /************ 拼接authorization ************/
        sb = new StringBuilder();
        sb.append("secretId=");
        sb.append(secretId);
        sb.append(",");
        sb.append("nonceStr=");
        sb.append(nonceStr);
        sb.append(",");
        sb.append("service=");
        sb.append(serviceName);
        sb.append(",");
        sb.append("timestamp=");
        sb.append(timestamp);
        sb.append(",");
        sb.append("signature=");
        sb.append(signature);
        if (extendKeys.length > 0) {
            for (String[] keys : extendKeys) {
                sb.append(",");
                sb.append(keys[0]);
                sb.append("=");
                sb.append(keys[1]);
            }
        }
        return sb.toString();
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0, v; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}