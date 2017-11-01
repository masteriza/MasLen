package com.maslen.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.maslen.models.RegistrationUserDto;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationUserDtoDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String lastName = node.get("lastName").textValue();
        String firstName = node.get("firstName").textValue();
        String middleName = node.get("middleName").textValue();
        LocalDate birthday = LocalDate.parse(node.get("birthday").textValue());
        String email = node.get("email").textValue();
        String rawPassword = node.get("rawPassword").textValue();
        String repeatRawPassword = node.get("repeatRawPassword").textValue();
        String phone = node.get("phone").textValue();
        String gender = node.get("gender").textValue();
        boolean agree = node.get("agree").booleanValue();

        return new RegistrationUserDto(lastName, firstName, middleName, birthday, email,
                rawPassword, repeatRawPassword, phone, gender, agree);
    }
}
