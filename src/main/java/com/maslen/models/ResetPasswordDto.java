package com.maslen.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maslen.deserializers.ResetPasswordDtoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ResetPasswordDtoDeserializer.class)
public class ResetPasswordDto {

    @Size(min = 1, max = 256, message = "Something went wrong. Try again later.")
    String id;

    @Size(min = 1, max = 256, message = "Something went wrong. Try again later.")
    String uid;

    @Size(min = 1, max = 32, message = "Wrong password length")
    String rawPassword;

    @Size(min = 1, max = 32, message = "Wrong confirm password length")
    String repeatRawPassword;
}
