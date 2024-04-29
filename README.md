### 使用方法

1. DataCloudSign使用
```java
import com.geovisearth;

try {
    DataCloudSign geoSign = new DataCloudSign(
            "secretId",
            "oL9Ebg3ofo9pyaB42KMokWzc0SMQQeLjFkVF33U6N1M=");
    String str = geoSign.createCloudApiHmacSign(
            "GET",
            "/v1/cloudapi/developer/devDataPackUsage",
            "channel=channelId&appId=appId",
            "",
            1698911283190l);
} catch (Exception e) {
   
}
// 签名返回内容
//  "secretId=secretId,nonceStr=K-zgG5ZW7UvfmwpgN8LO4,service=geovis-data-cloud,timestamp=1698828171453,signature=13f01b038e401bf7d8d78e1a530d14cdfa07964daf11b4b0a2c850c4c8e1b36d"


```

2. CertificationSign使用
```java
import com.geovisearth;

try {
    CertificationSign geoSign = new CertificationSign(
            "secretId",
            "oL9Ebg3ofo9pyaB42KMokWzc0SMQQeLjFkVF33U6N1M=");
    String str = geoSign.createCloudApiHmacSign(
            "GET",
            "/v1/cloudapi/developer/devDataPackUsage",
            "channel=channelId&appId=appId",
            "",
            1698911283190l);
} catch (Exception e) {
   
}
// 签名返回内容
//  "secretId=secretId,nonceStr=K-zgG5ZW7UvfmwpgN8LO4,service=geovis-data-cloud,timestamp=1698828171453,signature=13f01b038e401bf7d8d78e1a530d14cdfa07964daf11b4b0a2c850c4c8e1b36d"


```

3. PaymentSign使用
```java
import com.geovisearth;

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
} catch (Exception e) {
   
}
// 签名返回内容
//  "secretId=secretId,nonceStr=K-zgG5ZW7UvfmwpgN8LO4,service=geovis-payment-center,timestamp=1698828171453,signature=13f01b038e401bf7d8d78e1a530d14cdfa07964daf11b4b0a2c850c4c8e1b36d,mchid=mchid"


// 验证签名
try {
    String jsonData = paymentSign.checkCallback(
            "jsonData",
            "notifyTime",
            "sign",
            "status");
    CallBackData = parseJson(jsonData);
} catch (Exception e) {
    // 签名不正确
    return null
}


```
