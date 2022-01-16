package org.docshift.usermanagement.service.appuser.impl;

import lombok.extern.slf4j.Slf4j;
import org.docshift.usermanagement.dao.AppUserRepository;
import org.docshift.usermanagement.domain.AppUser;
import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.exception.AlreadyRegisteredException;
import org.docshift.usermanagement.exception.UserNotFoundException;
import org.docshift.usermanagement.service.UtilService;
import org.docshift.usermanagement.service.appuser.AppUserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public record AppUserPostgreService(AppUserRepository appUserRepository,
                                    UtilService utilService) implements AppUserService {

    @Override
    public ObjectCreateResponseDto save(AppUserCreateRequestDto appUserCreateRequestDto) {
        if(appUserRepository.existsByUsername(appUserCreateRequestDto.getUsername())) {
            throw new AlreadyRegisteredException("User has already registered");
        }

        AppUser appUser = AppUser
                                .builder()
                                .username(appUserCreateRequestDto.getUsername())
                                .name(appUserCreateRequestDto.getName())
                                .lastname(appUserCreateRequestDto.getLastName())
                                .build();

        AppUser appUserdb = appUserRepository.save(appUser);

        if(appUserdb==null) {
            //TODO exception & exception handling
            return null;
        }

        log.info("user has been created:{}", appUserCreateRequestDto.getName());
        String messageStr = String.format("user: %s has been created", appUserCreateRequestDto.getName());

        return ObjectCreateResponseDto
                .builder()
                .message(messageStr)
                .timestamp(new Date())
                .build();
    }

    @Override
    public AppUser getUserByUserName(String username) {
       return appUserRepository.getUserByUserName(username);
    }

    @Override
    public AppUserResponseDto getAppUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);

        if (appUser == null) {
          throw new UserNotFoundException("user is not exist in db");
        }

        return AppUserResponseDto
                            .builder()
                            .id(appUser.getId())
                            .username(appUser.getUsername())
                            .lastname(appUser.getLastname())
                            .name(appUser.getName())
                            .build();
    }

    @Override
    public ObjectCreateResponseDto deleteUserByUserName(String username) {
        appUserRepository.deleteUserByUserName(username);

        log.info("user has been deleted:{}", username);
        String messageStr = String.format("user: %s has been deleted", username);

        return ObjectCreateResponseDto
                .builder()
                .message(messageStr)
                .timestamp(new Date())
                .build();
    }

    @Override
    public List<AppUserResponseDto> getAllUsers() {
      return utilService.convertUserEntityToDto(appUserRepository.findAll());
    }

    public List<AppUserWithProfileDto> getAllUsersWithProfiles() {
        return utilService.convertUserEntityToDetailedDto(appUserRepository.findAll());
    }

    @Override
    public void updateAppUser(AppUserWithProfileDto appUserWithProfileDto) {
         AppUser appUser = appUserRepository.findByUsername(appUserWithProfileDto.getUsername());

         appUser.setLastname(appUserWithProfileDto.getLastname());
         appUser.setName(appUserWithProfileDto.getName());
         appUser.getUserProfile().setPhoneNumber(appUserWithProfileDto.getPhoneNumber());
         appUser.getUserProfile().setAddress(appUserWithProfileDto.getAddress());

         appUserRepository.save(appUser);
    }
}
