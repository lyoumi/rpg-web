package com.web.rpg.converters;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class BaseConverter {
    byte[] serializeObjectToByteArray(Object o) {
        if (o != null) {
                return SerializationUtils.serialize((Serializable) o);
        }
        return null;
    }

    Object deserializeObjectFromByte(byte[] byteArray) {
        if (byteArray != null) {
            return SerializationUtils.deserialize(byteArray);
        }
        return null;
    }
}
