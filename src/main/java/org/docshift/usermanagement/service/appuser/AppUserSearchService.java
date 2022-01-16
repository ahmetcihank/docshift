package org.docshift.usermanagement.service.appuser;

import org.docshift.usermanagement.domain.dto.AppUserResponseDto;

public interface AppUserSearchService extends AppUserBaseService {
    public AppUserResponseDto getAppUserBySearch(String keyword);
}
