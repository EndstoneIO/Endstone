package io.endstone.www.data;

import io.endstone.www.IESData;

public class ESDataBol implements IESData {

    private boolean value;

    public ESDataBol() {
        this.value = true;
    }

    public ESDataBol(boolean value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.BOL;
    }

    @Override
    public Object val() {
        return this.value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof Boolean) {
            this.value = (Boolean)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set boolean config attribute with invalid value");
        }
    }
}
