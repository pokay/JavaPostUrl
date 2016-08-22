package com.yintai.kuaidi100service.common

import com.thoughtworks.xstream.converters.MarshallingContext
import com.thoughtworks.xstream.converters.UnmarshallingContext
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper
import com.thoughtworks.xstream.io.HierarchicalStreamReader
import com.thoughtworks.xstream.io.HierarchicalStreamWriter
import sun.font.LayoutPathImpl

import java.util.Map.Entry

/**
 * Created by PangQian on 2016/8/8.
 */
public class MapCustomConverter extends AbstractCollectionConverter {

    public MapCustomConverter(LayoutPathImpl.SegmentPath.Mapper mapper) {
        super(mapper);
    }

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
        // 这里只列了HashMap一种情况
        return type.equals(HashMap.class);
    }

    @SuppressWarnings("rawtypes")
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map map = (Map) source;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Entry entry = (Entry) iterator.next();
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, entry.getKey().toString(), Entry.class);

            writer.setValue(entry.getValue().toString());
            writer.endNode();
        }
    }

    @SuppressWarnings("rawtypes")
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map map = (Map) createCollection(context.getRequiredType());
        populateMap(reader, context, map);
        return map;

    }

    @SuppressWarnings("rawtypes")
    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            Object key = reader.getNodeName();
            Object value = reader.getValue();
            map.put(key, value);
            reader.moveUp();
        }
    }

}