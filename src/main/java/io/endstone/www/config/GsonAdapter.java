package io.endstone.www.config;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.endstone.www.data.ESDataArr;
import io.endstone.www.data.ESDataBol;
import io.endstone.www.data.ESDataFlt;
import io.endstone.www.data.ESDataInt;
import io.endstone.www.data.ESDataObj;
import io.endstone.www.data.ESDataStr;
import io.endstone.www.data.IESData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GsonAdapter extends TypeAdapter<IESData> {

    @Override
    public IESData read(JsonReader reader) throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(reader);
        reader.close();
        return read(json);
    }

    private IESData read(JsonElement json) {
        if (json.isJsonPrimitive()) {
            JsonPrimitive jsonp = json.getAsJsonPrimitive();
            if (jsonp.isNumber()) {
                Number num = jsonp.getAsNumber();
                if (num instanceof Integer) {
                    return new ESDataInt(jsonp.getAsInt());
                } else {
                    return new ESDataFlt(jsonp.getAsFloat());
                }
            } else if (jsonp.isBoolean()) {
                return new ESDataBol(jsonp.getAsBoolean());
            } else {
                return new ESDataStr(jsonp.getAsString());
            }
        } else if (json.isJsonObject()) {
            JsonObject jsono = json.getAsJsonObject();
            ESDataObj obj = new ESDataObj();
            for (Map.Entry entry : jsono.entrySet()) {
                obj.put(entry.getKey().toString(), read((JsonElement)entry.getValue()));
            }
            return obj;
        } else if (json.isJsonArray()) {
            JsonArray jsona = json.getAsJsonArray();
            ESDataArr arr = new ESDataArr();
            for (JsonElement elem : jsona) {
                arr.push(read(elem));
            }
            return arr;
        } else {
            return new ESDataObj();
        }
    }

    @Override
    public void write(JsonWriter writer, IESData config) throws IOException {
        writer.setIndent("  ");
        writeConfig(writer, config);
        writer.close();
    }

    private void writeConfig(JsonWriter writer, IESData config) throws IOException{
        if (config instanceof ESDataStr) {
            writer.value((String)config.val());
        } else if (config instanceof ESDataInt) {
            writer.value((Integer)config.val());
        } else if (config instanceof ESDataFlt) {
            writer.value((Float)config.val());
        } else if (config instanceof ESDataBol) {
            writer.value((Boolean)config.val());
        } else if (config instanceof ESDataObj) {
            ESDataObj cobj = (ESDataObj) config;
            writer.beginObject();
            for (Map.Entry entry : ((Map<String, IESData>)cobj.val()).entrySet()) {
                writer.name(entry.getKey().toString());
                writeConfig(writer, (IESData) entry.getValue());
            }
            writer.endObject();
        } else if (config instanceof ESDataArr) {
            ESDataArr carr = (ESDataArr) config;
            writer.beginArray();
            for (IESData elem : (List<IESData>)carr.val()) {
                writeConfig(writer, elem);
            }
            writer.endArray();
        } else {
            writer.nullValue();
        }
    }
}