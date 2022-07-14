package com.semp.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.semp.entity.PhotoProduct;
import com.semp.entity.ProductPropertyForRemaining;
import com.semp.pojo.Remaining;

import java.io.IOException;

public class RemainingSerializer extends StdSerializer<Remaining> {
    public RemainingSerializer() {
        this(null);
    }

    protected RemainingSerializer(Class<Remaining> t) {
        super(t);
    }

    @Override
    public void serialize(Remaining remaining, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", remaining.getRemaining().getProduct().getName());
        jsonGenerator.writeStringField("category", remaining.getRemaining().getProduct().getCategory().getName());
        jsonGenerator.writeNumberField("remaining", remaining.getRemaining().getRemaining());
        jsonGenerator.writeNumberField("price", remaining.getRemaining().getPrice());
        String mainPhoto = null;
        if(!remaining.getRemaining().getPhoto().isEmpty()) {
            jsonGenerator.writeFieldName("photo");
            jsonGenerator.writeStartArray();
            for (PhotoProduct photo : remaining.getRemaining().getPhoto()) {
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
        if(!remaining.getProperty().isEmpty()) {
            jsonGenerator.writeFieldName("property");
            jsonGenerator.writeStartArray();
            for(ProductPropertyForRemaining property : remaining.getProperty()) {
                jsonGenerator.writeString(property.getPropertyValue().getValue());
            }
            jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}
