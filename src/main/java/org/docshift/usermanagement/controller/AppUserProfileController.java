package org.docshift.usermanagement.controller;

import org.docshift.usermanagement.domain.dto.AppUserProfileCreateRequestDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.appuser.AppUserProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("profile")
@Validated
public class AppUserProfileController {

    private final AppUserProfileService appUserProfileService;

    public AppUserProfileController(@Qualifier("appUserProfilePostgreService")
                                            AppUserProfileService appUserProfileService) {
        this.appUserProfileService = appUserProfileService;
    }

    @PostMapping("/create")
    public ResponseEntity<ObjectCreateResponseDto> createProfile(@RequestBody @Valid
                                                                          AppUserProfileCreateRequestDto appUserProfileCreateRequestDto) {
        ObjectCreateResponseDto objectCreateResponseDto = appUserProfileService.save(appUserProfileCreateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(objectCreateResponseDto);
    }
}
