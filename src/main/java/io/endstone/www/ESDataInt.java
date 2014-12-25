package io.endstone.www.data;

import io.endstone.www.IESData;

public class ESDataInt implements IESData {

    private int value;

    public ESDataInt() {
        this.value = 0;
    }

    public ESDataInt(int value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.INT;
    }

    @Override
    public Object val() {
        return this.value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof Integer) {
            this.value = (Integer)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set integer config attribute with invalid value");
        }
    }
}
