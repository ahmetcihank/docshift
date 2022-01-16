package org.docshift.usermanagement.service.appuser.impl;

import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.appuser.AppUserSearchService;

import java.util.List;

public record AppUserElasticService() implements AppUserSearchService {
    @Override
    public ObjectCreateResponseDto save(AppUserCreateRequestDto appUserCreateRequestDto) {
        return null;
    }

    @Override
    public List<AppUserResponseDto> getAllUsers() {
        return null;
    }

    @Override
    public ObjectCreateResponseDto deleteUserByUserName(String username) {
        return null;
    }

    @Override
    public AppUserResponseDto getAppUserBySearch(String keyword) {
        return null;
    }
}
