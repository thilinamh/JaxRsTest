package com.epic.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.enterprise.context.ApplicationScoped;
import java.security.Key;
import java.security.NoSuchAlgorithmException;


/**
 * Created by tm on 7/14/17.
 */

@ApplicationScoped
public class KeyHandler {


    Key HMAC_SHA512_KEY;


    Logger debugLogger = LoggerFactory.getLogger(KeyHandler.class);

//    public void onStart(@Observes(notifyObserver=ALWAYS) @Bootstrap.OnStart  Bootstrap.LifecycleEvent evnt) {
//        System.out.println("boom");
//    }

    @PostConstruct
    public void init() {
        HMAC_SHA512_KEY=generateKey();
    }

    public SecretKey getAES_SecretKey() {
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // for example
            SecretKey secretKey = keyGen.generateKey();
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            debugLogger.error("Could not generate key", e);
            return null;
        }

    }

    private Key generateKey() {
        return MacProvider.generateKey(SignatureAlgorithm.HS512);
    }

    private Key getHMAC_SHA512_KEY() {
        return HMAC_SHA512_KEY;
    }

    public Key getJWTSigningKey() {
        return getHMAC_SHA512_KEY();
    }

    private void setHMAC_SHA512_KEY(Key HMAC_SHA512_KEY) {
        this.HMAC_SHA512_KEY = HMAC_SHA512_KEY;
    }

}
