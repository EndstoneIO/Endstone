import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Test {
    public static void main(String[] args) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(System.out));
        writer.setIndent("  ");
        writer.beginObject();
        writer.name("hello");
        writer.value("gson");
        writer.name("foo");
        writer.beginArray();
        writer.value("bar");
        writer.value(42);
        writer.endArray();
        writer.endObject();
        writer.close();
    }
}