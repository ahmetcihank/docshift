package org.docshift.usermanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponseDto implements Comparable<AppUserResponseDto>{
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private Date createdDate;

    @Override
    public int compareTo(AppUserResponseDto appUserResponseDto) {
        return getCreatedDate().compareTo(appUserResponseDto.getCreatedDate());
    }
}
