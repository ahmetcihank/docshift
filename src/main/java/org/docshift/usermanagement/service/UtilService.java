package org.docshift.usermanagement.service;

import org.docshift.usermanagement.domain.AppUser;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class UtilService {
    public List<AppUserResponseDto> convertUserEntityToDto(List<AppUser> appUsers) {
        LinkedList<AppUserResponseDto> appUserResponseDtos = new LinkedList<>();

        for (AppUser appUser: appUsers) {
            AppUserResponseDto appUserResponseDto = AppUserResponseDto
                    .builder()
                    .id(appUser.getId())
                    .username(appUser.getUsername())
                    .lastname(appUser.getLastname())
                    .name(appUser.getName())
                    .createdDate(appUser.getCreated())
                    .build();
            appUserResponseDtos.add(appUserResponseDto);
        }

        Collections.sort(appUserResponseDtos);
        return appUserResponseDtos;
    }

    public List<AppUserWithProfileDto> convertUserEntityToDetailedDto(List<AppUser> appUsers) {
        LinkedList<AppUserWithProfileDto> appUserResponseDtos = new LinkedList<>();

        for (AppUser appUser: appUsers) {
            AppUserWithProfileDto appUserResponseWithProfileDto = AppUserWithProfileDto
                    .builder()
                    .id(appUser.getId())
                    .username(appUser.getUsername())
                    .lastname(appUser.getLastname())
                    .name(appUser.getName())
                    .phoneNumber(appUser.getUserProfile().getPhoneNumber())
                    .gender(appUser.getUserProfile().getGender())
                    .address(appUser.getUserProfile().getAddress())
                    .createdDate(appUser.getCreated())
                    .build();
            appUserResponseDtos.add(appUserResponseWithProfileDto);
        }

        Collections.sort(appUserResponseDtos);
        return appUserResponseDtos;
    }
}
