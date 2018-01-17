package com.maslen.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.maslen.models.ResetPasswordDto;

import java.io.IOException;

public class ResetPasswordDtoDeserializer extends JsonDeserializer<ResetPasswordDto> {
    @Override
    public ResetPasswordDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String id = node.get("id").textValue();
        String uid = node.get("uid").textValue();
        String rawPassword = node.get("rawPassword").textValue();
        String repeatRawPassword = node.get("repeatRawPassword").textValue();

        return new ResetPasswordDto(id, uid, rawPassword, repeatRawPassword);
    }
}
