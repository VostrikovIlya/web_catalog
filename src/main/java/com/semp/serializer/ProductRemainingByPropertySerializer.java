package com.semp.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.semp.entity.PhotoProduct;
import com.semp.entity.ProductRemainingByProperty;

import java.io.IOException;

public class ProductRemainingByPropertySerializer extends StdSerializer<ProductRemainingByProperty> {

    public ProductRemainingByPropertySerializer() {
        this(null);
    }

    protected ProductRemainingByPropertySerializer(Class<ProductRemainingByProperty> t) {
        super(t);
    }

    @Override
    public void serialize(ProductRemainingByProperty remaining, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", remaining.getProduct().getName());
        jsonGenerator.writeStringField("category", remaining.getProduct().getCategory().getName());
        jsonGenerator.writeNumberField("remaining", remaining.getRemaining());
        jsonGenerator.writeNumberField("price", remaining.getPrice());
        String mainPhoto = null;
        if(!remaining.getPhoto().isEmpty()) {
            jsonGenerator.writeFieldName("photo");
            jsonGenerator.writeStartArray();
            for (PhotoProduct photo : remaining.getPhoto()) {
                if (photo.isMainPhoto()) {
                     mainPhoto = photo.getPath();
                } else {
                    jsonGenerator.writeString(photo.getPath());
                }
            }
            jsonGenerator.writeEndArray();
        }
        if(mainPhoto != null) {
            jsonGenerator.writeStringField("main-photo", mainPhoto);
        }
        jsonGenerator.writeEndObject();
    }
}