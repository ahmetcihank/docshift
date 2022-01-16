package org.docshift.usermanagement.service.appuser;

import org.docshift.usermanagement.domain.AppUser;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;

import java.util.List;

public interface AppUserService extends AppUserBaseService{
    public AppUser getUserByUserName(String email);
    public AppUserResponseDto getAppUserByUsername(String username);
    public List<AppUserWithProfileDto> getAllUsersWithProfiles();
    public void updateAppUser(AppUserWithProfileDto appUserWithProfileDto);
}
