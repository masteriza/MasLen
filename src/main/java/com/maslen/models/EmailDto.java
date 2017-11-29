package com.maslen.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maslen.deserializers.EmailDtoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@JsonDeserialize(using = EmailDtoDeserializer.class)
public class EmailDto {
    @Size(min = 5, max = 256, message = "E-mail must be longer than 5 and less than 256 characters")
    @Email(message = "Wrong email")
    private String email;
}
