package org.docshift.usermanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserCreateRequestDto {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "lastname cannot be empty")
    private String lastName;
    @NotBlank(message = "email cannot be empty")
    private String username;
}
