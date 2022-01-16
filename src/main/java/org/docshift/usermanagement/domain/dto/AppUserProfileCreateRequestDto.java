package org.docshift.usermanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.docshift.usermanagement.domain.Gender;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserProfileCreateRequestDto {
    @NotBlank(message = "name cannot be empty")
    private String username;
    //@NotBlank(message = "lastname cannot be empty")
    private Gender gender;
    @NotBlank(message = "email cannot be empty")
    private String phoneNumber;
    @NotBlank(message = "email cannot be empty")
    private String address;
}
