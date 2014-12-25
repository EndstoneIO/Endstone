package io.endstone.www.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.endstone.www.Endstone;
import io.endstone.www.data.ESDataArr;
import io.endstone.www.data.ESDataBol;
import io.endstone.www.data.ESDataFlt;
import io.endstone.www.data.ESDataInt;
import io.endstone.www.data.ESDataObj;
import io.endstone.www.data.ESDataStr;
import io.endstone.www.data.IESData;

import java.io.*;
import java.util.List;

public class EndstoneConfig implements ESConfig {

    private static Gson builder = new GsonBuilder()
            .registerTypeAdapter(IESData.class, new GsonAdapter())
            .create();

    public static IESData load(String name) {
        IESData result = null;
        try {
            File cfgFile = new File(Endstone.configDir, name + ".json");
            if (!cfgFile.exists()) {
                cfgFile.createNewFile();
            }
            result = builder.fromJson(new BufferedReader(new FileReader(cfgFile)), IESData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(IESData config, String name) {
        File cfgFile = new File(Endstone.configDir, name + ".json");
        try {
            builder.toJson(config, new BufferedWriter(new FileWriter(cfgFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String name;
    public final ESDataObj data;
    public final EndstoneConfig parent;

    private boolean isModified = false;

    public EndstoneConfig(String name) {
        this.name = name;
        this.data = (ESDataObj)load(name);
        this.parent = null;
    }

    public EndstoneConfig(EndstoneConfig parent, String name) {
        this.name = name;
        this.data = (ESDataObj)parent.data.get(name);
        this.parent = parent;
    }

    public String getAbsoluteName() {
        if (parent == null) {
            return name;
        } else {
            return parent.getAbsoluteName() + "." + name;
        }
    }

    private void set(String key, IESData value) {
        this.data.put(key, value);
        this.isModified = true;
        if (this.parent != null) {
            this.parent.data.put(this.name, this.data);
            this.parent.isModified = true;
        }
    }

    public void store() {
        if (this.parent != null) {
            this.parent.store();
        } else if (this.isModified) {
            save(this.data, this.name);
        }
    }

    public String getString(String key, String defaultValue) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataStr) {
            return (String)data.val();
        } else {
            this.set(key, new ESDataStr(defaultValue));
            return defaultValue;
        }
    }

    public int getInt(String key, int defaultValue) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataInt) {
            return (Integer)data.val();
        } else {
            this.set(key, new ESDataInt(defaultValue));
            return defaultValue;
        }
    }

    public double getFloat(String key, double defaultValue) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataFlt) {
            return (Double)data.val();
        } else {
            this.set(key, new ESDataFlt(defaultValue));
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataBol) {
            return (Boolean)data.val();
        } else {
            this.set(key, new ESDataBol(defaultValue));
            return defaultValue;
        }
    }

    public List<IESData> getList(String key, List<IESData> defaultValue) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataArr) {
            return (List<IESData>)data.val();
        } else {
            this.set(key, new ESDataArr(defaultValue));
            return defaultValue;
        }
    }

    public EndstoneConfig getChild(String key) {
        IESData data = this.data.get(key);
        if (data != null && data instanceof ESDataObj) {
            return new EndstoneConfig(this, key);
        } else {
            this.set(key, new ESDataObj());
            return new EndstoneConfig(this, key);
        }
    }
}