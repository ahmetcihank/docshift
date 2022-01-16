package org.docshift.usermanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.docshift.usermanagement.domain.Gender;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserWithProfileDto implements Comparable<AppUserWithProfileDto>{
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private Date createdDate;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private LocalDate dateOfBirth;

    @Override
    public int compareTo(AppUserWithProfileDto appUserWithProfileDto) {
        return getCreatedDate().compareTo(appUserWithProfileDto.getCreatedDate());
    }
}
