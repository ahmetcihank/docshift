package org.docshift.usermanagement.service.appuser;

import org.docshift.usermanagement.domain.dto.AppUserProfileCreateRequestDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;

public interface AppUserProfileService {
    public ObjectCreateResponseDto save(AppUserProfileCreateRequestDto appUserProfileCreateRequestDto);
}
