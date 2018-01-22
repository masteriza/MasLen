package com.maslen.services.interfaces;

import com.maslen.models.ResetPasswordDto;
import org.springframework.validation.BindingResult;

public interface ResetPasswordService {
    BindingResult validateForm(ResetPasswordDto resetPasswordDto, BindingResult bindingResult);

}
