package io.endstone.www.data;

import io.endstone.www.IESData;

import java.util.HashMap;
import java.util.Map;

public class ESDataObj implements IESData {

    private Map<String, IESData> value;

    public ESDataObj() {
        this.value = new HashMap<String, IESData>();
    }

    public ESDataObj(Map value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.OBJ;
    }

    @Override
    public Object val() {
        return this.value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof Map) {
            this.value = (Map)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set object config attribute with invalid value");
        }
    }

    public IESData get(String key) {
        return this.value.get(key);
    }

    public void put(String key, IESData value) {
        this.value.put(key, value);
    }

    public void remove(String key) {
        this.value.remove(key);
    }
}
