package com.school.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GraphicCategorySerilizer extends JsonSerializer<GraphicCategory> {
@Override
    public void serialize(GraphicCategory category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("category", category.getCategory());
        jsonGenerator.writeNumberField("cat_id",category.getCat_id());
        jsonGenerator.writeEndObject();
 }
}