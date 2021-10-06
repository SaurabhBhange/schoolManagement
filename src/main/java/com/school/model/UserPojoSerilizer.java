package com.school.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserPojoSerilizer extends JsonSerializer<UserPojo> {
@Override
    public void serialize(UserPojo user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("username",user.getUsername());
        jsonGenerator.writeNumberField("userId",user.getUser_id());
        jsonGenerator.writeEndObject();
    }
}
