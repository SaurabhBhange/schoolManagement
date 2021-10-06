package com.school.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class InternCategorySerlizer  extends JsonSerializer<InternshipCategory> {
@Override
    public void serialize(InternshipCategory cat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("category", cat.getCategory());
        jsonGenerator.writeNumberField("cat_id",cat.getCat_id());
        jsonGenerator.writeEndObject();
 }

}
