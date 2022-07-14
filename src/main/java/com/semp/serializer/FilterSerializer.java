package com.semp.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.semp.entity.Property;
import com.semp.entity.PropertyValue;
import com.semp.pojo.Filter;

import java.io.IOException;

public class FilterSerializer extends StdSerializer<Filter> {
    public FilterSerializer() {
        this(null);
    }

    public FilterSerializer(Class<Filter> t) {
        super(t);
    }

    @Override
    public void serialize(Filter filter, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        if(!filter.getListProperty().isEmpty()) {
            for(Property property: filter.getListProperty()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("title", property.getName());
                jsonGenerator.writeStringField("slug", property.getSlug());
                if(!property.getPropertyValueSet().isEmpty()) {
                    jsonGenerator.writeFieldName("values");
                    jsonGenerator.writeStartArray();
                    for(PropertyValue propertyValue: property.getPropertyValueSet()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("title", propertyValue.getValue());
                        jsonGenerator.writeStringField("slug", propertyValue.getSlug());
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                }
                jsonGenerator.writeEndObject();
            }
        }
        jsonGenerator.writeEndArray();
    }
}
