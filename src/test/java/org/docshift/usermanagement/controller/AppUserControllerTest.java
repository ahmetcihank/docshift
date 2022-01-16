package org.docshift.usermanagement.controller;

import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.appuser.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserControllerTest {

    @InjectMocks
    private AppUserController appUserController;

    @Mock
    private AppUserService appUserService;

    @Test
    void shouldCreateUser() {
        AppUserCreateRequestDto appUserCreateRequestDto = AppUserCreateRequestDto
                                                                        .builder()
                                                                        .username("cihan")
                                                                        .lastName("kara")
                                                                        .name("cihan")
                                                                        .build();

        String messageStr = String.format("user: %s has been created", appUserCreateRequestDto.getName());

        ObjectCreateResponseDto objectCreateResponseDto = ObjectCreateResponseDto
                                                                            .builder()
                                                                            .timestamp(new Date())
                                                                            .message(messageStr)
                                                                            .build();

         when(appUserService.save(appUserCreateRequestDto)).thenReturn(objectCreateResponseDto);

         ResponseEntity<ObjectCreateResponseDto> responseEntity = appUserController.createUser(appUserCreateRequestDto);
         assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
         assertEquals("user: cihan has been created", responseEntity.getBody().getMessage());
    }

    @Test
    void shouldGetUserByUsername() {
        String reqUserName = "jack";

        AppUserResponseDto appUserResponseDto = AppUserResponseDto
                                                                .builder()
                                                                .username(reqUserName)
                                                                .createdDate(new Date())
                                                                .name("jack")
                                                                .build();

        when(appUserService.getAppUserByUsername(reqUserName)).thenReturn(appUserResponseDto);

        ResponseEntity<AppUserResponseDto> responseEntity = appUserController.getUserNameByUsername(reqUserName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appUserResponseDto, responseEntity.getBody());
    }

    @Test
    void getAllUsers() {
        List<AppUserResponseDto> appUserResponseDtos = new LinkedList<>();

        AppUserResponseDto appUserResponseDto = AppUserResponseDto
                .builder()
                .username("jhon")
                .createdDate(new Date())
                .name("jack")
                .build();

        appUserResponseDtos.add(appUserResponseDto);

        when(appUserService.getAllUsers()).thenReturn(appUserResponseDtos);

        ResponseEntity<List<AppUserResponseDto>> responseEntity = appUserController.getAllUser();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appUserResponseDtos, responseEntity.getBody());
    }

    @Test
    void getAllUsersWithProfiles() {
        List<AppUserWithProfileDto> appUserWithProfileDtos = new LinkedList<>();

        AppUserWithProfileDto appUserWithProfileDto = AppUserWithProfileDto
                                                                    .builder()
                                                                    .id(2l)
                                                                    .username("jack")
                                                                    .lastname("jhon")
                                                                    .build();

        appUserWithProfileDtos.add(appUserWithProfileDto);

        when(appUserService.getAllUsersWithProfiles()).thenReturn(appUserWithProfileDtos);

        ResponseEntity<List<AppUserWithProfileDto>> responseEntity = appUserController.getAllUsersWithProfiles();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(appUserWithProfileDtos, responseEntity.getBody());
    }

    @Test
    void deleteUserByUserName() {

        String messageStr = String.format("user: %s has been deleted", "jack");

        ObjectCreateResponseDto objectCreateResponseDto = ObjectCreateResponseDto
                .builder()
                .timestamp(new Date())
                .message(messageStr)
                .build();

        when(appUserService.deleteUserByUserName("jack")).thenReturn(objectCreateResponseDto);

        ResponseEntity<ObjectCreateResponseDto> responseEntity = appUserController.deleteUserByUserName("jack");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(objectCreateResponseDto, responseEntity.getBody());
    }
}