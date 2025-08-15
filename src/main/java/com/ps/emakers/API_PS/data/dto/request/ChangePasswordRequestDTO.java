package com.ps.emakers.API_PS.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(
        @NotBlank
        String currentPassword,

        @NotBlank
        String newPassword
) {
}