package org.docshift.usermanagement.service.appuser;

import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;

import java.util.List;

public interface AppUserBaseService {
    public ObjectCreateResponseDto save(AppUserCreateRequestDto appUserCreateRequestDto);
    public List<AppUserResponseDto> getAllUsers();
    public ObjectCreateResponseDto deleteUserByUserName(String username);
}
