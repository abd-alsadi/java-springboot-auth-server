package com.core.authserver.helpers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class SerializableObjectConverterHelper {
    public static String serialize(Object object) {
        try {
            byte[] bytes = SerializationUtils.serialize(object);
            return Base64.encodeBase64String(bytes);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Object deserialize(String encodedObject) {
        try {
            byte[] bytes = Base64.decodeBase64(encodedObject);
            return  SerializationUtils.deserialize(bytes);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
