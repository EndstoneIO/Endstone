package io.endstone.www.config;

import io.endstone.www.data.IESData;

import java.util.List;

public interface ESConfig {

    String getAbsoluteName();
    void store();
    String getString(String key, String defaultValue);
    int getInt(String key, int defaultValue);
    double getFloat(String key, double defaultValue);
    boolean getBoolean(String key, boolean defaultValue);
    List<IESData> getList(String key, List<IESData> defaultValue);
    EndstoneConfig getChild(String key);
}
