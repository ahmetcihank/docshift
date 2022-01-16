package org.docshift.usermanagement.service.appuser.impl;

import org.docshift.usermanagement.dao.AppUserRepository;
import org.docshift.usermanagement.domain.AppUser;
import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.UtilService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserPostgreServiceTest {

    @InjectMocks
    private AppUserPostgreService appUserPostgreService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private UtilService utilService;

    @Test
    void shouldSaveAppuser() {
        AppUserCreateRequestDto appUserCreateRequestDto = AppUserCreateRequestDto
                                                                            .builder()
                                                                            .username("cihan")
                                                                            .lastName("kara")
                                                                            .name("cihan")
                                                                            .build();

        AppUser appUserdb = AppUser
                                .builder()
                                .username("cihan")
                                .lastname("kara")
                                .name("cihan")
                                .build();

        when(appUserRepository.save(appUserdb)).thenReturn(appUserdb);

        ObjectCreateResponseDto objectCreateResponseDto = appUserPostgreService.save(appUserCreateRequestDto);
        assertEquals("user: cihan has been created", objectCreateResponseDto.getMessage());
    }

    @Test
    void shouldGetUserByUserName() {
        AppUser appUserdb = AppUser
                                .builder()
                                .username("cihan")
                                .lastname("kara")
                                .name("cihan")
                                .build();

        when(appUserRepository.getUserByUserName("cihan")).thenReturn(appUserdb);
        AppUser appUser = appUserPostgreService.getUserByUserName("cihan");

        assertEquals(appUser.getUsername(), appUserdb.getUsername());
        assertEquals(appUser.getName(), appUserdb.getName());
    }

    @Test
    void deleteUserByUserName() {
      ObjectCreateResponseDto objectCreateResponseDto = appUserPostgreService.deleteUserByUserName("jack");
      assertEquals("user: jack has been deleted", objectCreateResponseDto.getMessage());
    }

    @Test
    void getAllUsers() {
        List<AppUser> appUsers = new ArrayList<>();
        List<AppUserWithProfileDto> appUserWithProfileDtos = new ArrayList<>();

        AppUserWithProfileDto appUserWithProfileDto = AppUserWithProfileDto
                .builder()
                .username("cihan")
                .lastname("kara")
                .name("cihan")
                .build();

        AppUser appUser = AppUser
                .builder()
                .username("cihan")
                .lastname("kara")
                .name("cihan")
                .build();

        appUsers.add(appUser);
        appUserWithProfileDtos.add(appUserWithProfileDto);

        when(appUserRepository.findAll()).thenReturn(appUsers);
        when(utilService.convertUserEntityToDetailedDto(appUsers)).thenReturn(appUserWithProfileDtos);

        List<AppUserWithProfileDto> appUserWithProfileDtosActual = appUserPostgreService.getAllUsersWithProfiles();

        assertEquals(appUserWithProfileDtosActual.get(0).getUsername(), appUserWithProfileDtos.get(0).getUsername());
        assertEquals(appUserWithProfileDtosActual.get(0).getName(), appUserWithProfileDtos.get(0).getName());
    }

    @Test
    void appUserRepository() {
    }
}