package com.maslen.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maslen.deserializers.RegistrationUserDtoDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@JsonDeserialize(using = RegistrationUserDtoDeserializer.class)
public class RegistrationUserDto {
    @Size(min = 1, max = 256, message = "Wrong first name")
    private String firstName;

    @Size(min = 1, max = 256, message = "Wrong last name")
    private String lastName;

    @Size(min = 1, max = 256, message = "Wrong middle name")
    private String middleName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "The field must be filled")
    // TODO: 27.08.2017 доделать паст
    //@Past(message = "The specified date can not be more than the current date")
    private LocalDate birthday;

    @Size(min = 5, max = 256, message = "E-mail must be longer than 5 and less than 256 characters")
    @Email(message = "Wrong email")
    private String email;

    @Size(min = 1, max = 32, message = "Wrong password length")
    private String rawPassword;

    @Size(min = 1, max = 32, message = "Wrong confirm password length")
    private String repeatRawPassword;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must be longer than 10 and less than 15 numbers")
    private String phone;

    @Pattern(regexp = "[MWX]", message = "Wrong gender")
    private String gender;

    @AssertTrue(message = "Confirm the agreement with the conditions")
    private boolean agree;

}
