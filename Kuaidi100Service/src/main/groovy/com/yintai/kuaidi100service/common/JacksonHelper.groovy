package com.yintai.kuaidi100service.common
import com.fasterxml.jackson.core.JsonGenerationException
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
/**
 * Created by PangQian on 2016/8/5.
 */
public class JacksonHelper {
    private static ObjectMapper toJSONMapper = new ObjectMapper();
    private static ObjectMapper fromJSONMapper = new ObjectMapper();
    /*static {
        fromJSONMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        fromJSONMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }*/

    public static String toJSON(Object obj) {
        ObjectMapper mapper = toJSONMapper;
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, obj);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }

    public static void toJSON(Object obj, OutputStream stream, String charset) {
        ObjectMapper mapper = toJSONMapper;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(stream, charset);
            mapper.writeValue(writer, obj);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        ObjectMapper mapper = fromJSONMapper;
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJSON(InputStream json, Class<T> clazz) {
        ObjectMapper mapper = fromJSONMapper;
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> boolean toJSONList(List<T> list) {
        String jsonVal = null;
        try {
            jsonVal = toJSONMapper.writeValueAsString(list);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonVal == null ? false : true;
    }

    public static <T> List<T> fromJSONList(String jsonVal, Class<?> clazz) {

        List<T> list = null;
        try {
            list = fromJSONMapper.readValue(jsonVal, TypeFactory.collectionType(ArrayList.class, clazz));
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
