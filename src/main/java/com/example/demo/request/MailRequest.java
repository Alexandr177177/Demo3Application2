package com.example.demo.request;

import com.example.demo.model.primary.FormatFiles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailRequest {
    @NotBlank
    @Email
    String mailAddress;
    @NotBlank
    FormatFiles formatFile;

}
