package com.semp.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.semp.entity.ProductPropertyForRemaining;


import java.io.IOException;

public class ProductPropertyForRemainingSerializer extends StdSerializer<ProductPropertyForRemaining> {
    public ProductPropertyForRemainingSerializer() {
        this(null);
    }

    protected ProductPropertyForRemainingSerializer(Class<ProductPropertyForRemaining> t) {
        super(t);
    }

    @Override
    public void serialize(ProductPropertyForRemaining productPropertyForRemaining, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("property", productPropertyForRemaining.getPropertyValue().getProperty().getName());
        jsonGenerator.writeStringField("value", productPropertyForRemaining.getPropertyValue().getValue());
        jsonGenerator.writeStringField("isFilter", productPropertyForRemaining.getPropertyValue().getProperty().getIsFilter().toString());
        jsonGenerator.writeEndObject();
    }
}
