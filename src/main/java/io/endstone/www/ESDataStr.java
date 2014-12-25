package io.endstone.www.data;

import io.endstone.www.IESData;

public class ESDataStr implements IESData {

    private String value;

    public ESDataStr() {
        this.value = "";
    }

    public ESDataStr(String value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.STR;
    }

    @Override
    public Object val() {
        return value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof String) {
            value = (String)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set string config attribute with invalid value");
        }
    }
}