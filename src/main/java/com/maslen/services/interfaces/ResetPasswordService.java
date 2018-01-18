package com.maslen.services.interfaces;

import com.maslen.models.ResetPasswordDto;
import org.springframework.validation.BindingResult;

public interface ResetPasswordService {
    boolean validateForm(ResetPasswordDto resetPasswordDto, BindingResult bindingResult);

}
