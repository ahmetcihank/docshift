package org.docshift.usermanagement.service.appuser.impl;

import lombok.extern.slf4j.Slf4j;
import org.docshift.usermanagement.dao.AppUserProfileRepository;
import org.docshift.usermanagement.dao.AppUserRepository;
import org.docshift.usermanagement.domain.AppUser;
import org.docshift.usermanagement.domain.AppUserProfile;
import org.docshift.usermanagement.domain.dto.AppUserProfileCreateRequestDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.appuser.AppUserProfileService;
import org.docshift.usermanagement.service.appuser.AppUserService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public record AppUserProfilePostgreService(
        AppUserService appUserService,
        AppUserProfileRepository appUserProfileRepository,
        AppUserRepository appUserRepository
) implements AppUserProfileService {

    @Override
    public ObjectCreateResponseDto save(AppUserProfileCreateRequestDto appUserProfileCreateRequestDto) {
        //TODO Null exception
        AppUser appUser = appUserService.getUserByUserName(appUserProfileCreateRequestDto.getUsername());

        AppUserProfile appUserProfile = AppUserProfile
                .builder()
                .appUser(appUser)
                .address(appUserProfileCreateRequestDto.getAddress())
                .phoneNumber(appUserProfileCreateRequestDto.getPhoneNumber())
                .gender(appUserProfileCreateRequestDto.getGender())
                .build();

       AppUserProfile appUserProfiledb =  appUserProfileRepository.save(appUserProfile);
       appUser.setUserProfile(appUserProfile);
       appUserRepository.save(appUser);

       if(appUserProfiledb == null) {
           return null;
       }

        log.info("user Profile has been created:{}", appUserProfileCreateRequestDto.getUsername());
        String messageStr = String.format("userPP: %s has been created", appUserProfileCreateRequestDto.getUsername());

        return ObjectCreateResponseDto
                .builder()
                .message(messageStr)
                .timestamp(new Date())
                .build();
    }
}
