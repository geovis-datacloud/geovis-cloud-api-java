package com.geovisearth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DataCloudSign extends CloudApiSign {
    public DataCloudSign(
            String secretId,
            String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        super(secretId, secretKey, "geovis-data-cloud");
    }
}