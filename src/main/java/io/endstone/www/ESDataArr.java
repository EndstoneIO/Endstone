package io.endstone.www.data;

import io.endstone.www.IESData;

import java.util.ArrayList;
import java.util.List;

public class ESDataArr implements IESData {

    private List<IESData> value;

    public ESDataArr() {
        this.value = new ArrayList<IESData>();
    }

    public ESDataArr(List value) {
        this.value = value;
    }

    @Override
    public dataType type() {
        return dataType.ARR;
    }

    @Override
    public Object val() {
        return this.value;
    }

    @Override
    public void val(Object obj) {
        if (obj instanceof List) {
            this.value = (List)obj;
        } else {
            throw new IllegalArgumentException("Attempt to set array config attribute with invalid value");
        }
    }

    public int size() {
        return this.value.size();
    }

    public IESData get(int i) {
        return this.value.get(i);
    }

    public void push(IESData elem) {
        this.value.add(elem);
    }

    public IESData pull() {
        return this.value.remove(size() -1);
    }
}