package com.maslen.models;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class RegistrationUserDto {
    @Size(min = 1, max = 256, message = "Wrong filename")
    private String firstName;

    @Size(min = 1, max = 256, message = "Wrong last name")
    private String lastName;

    @Size(min = 1, max = 256, message = "Wrong middle name")
    private String middleName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthday;

    @Email(message = "Wrong email")
    private String email;

    private String rawPassword;
    private String repeatRawPassword;

    private long phone;

    private char gender;

    private boolean agree;
}
