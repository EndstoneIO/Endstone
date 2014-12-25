package io.endstone.www.data;

import io.endstone.www.IESData;

public class ESDataFlt implements IESData {

    private double value;

    public ESDataFlt() {
        this.value = 0.0d;
    }

    public ESDataFlt(double value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.FLT;
    }

    @Override
    public Object val() {
        return this.value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof Float || obj instanceof Double) {
            this.value = (Double)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set float config attribute with invalid value");
        }
    }
}
