package com.geovisearth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CertificationSign extends CloudApiSign {
    public CertificationSign(
            String secretId,
            String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        super(secretId, secretKey, "geovis-certification");
    }
}
