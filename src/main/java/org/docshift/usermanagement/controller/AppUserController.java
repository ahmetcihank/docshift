package org.docshift.usermanagement.controller;

import org.docshift.usermanagement.domain.dto.AppUserCreateRequestDto;
import org.docshift.usermanagement.domain.dto.AppUserResponseDto;
import org.docshift.usermanagement.domain.dto.AppUserWithProfileDto;
import org.docshift.usermanagement.domain.dto.ObjectCreateResponseDto;
import org.docshift.usermanagement.service.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
@Validated
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(@Qualifier("appUserPostgreService")AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/create")
    public ResponseEntity<ObjectCreateResponseDto> createUser(@RequestBody @Valid AppUserCreateRequestDto appUserCreateRequestDto) {
        ObjectCreateResponseDto objectCreateResponseDto = appUserService.save(appUserCreateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(objectCreateResponseDto);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserResponseDto> getUserNameByUsername(@PathVariable("username") String username) {
        AppUserResponseDto appUserResponseDto = appUserService.getAppUserByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(appUserResponseDto);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<AppUserResponseDto>> getAllUser() {
        List<AppUserResponseDto> appUserResponseDtos = appUserService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(appUserResponseDtos);
    }

    @GetMapping("/allusersWithProfiles")
    public ResponseEntity<List<AppUserWithProfileDto>> getAllUsersWithProfiles() {
        List<AppUserWithProfileDto> appUserResponseDtos = appUserService.getAllUsersWithProfiles();

        return ResponseEntity.status(HttpStatus.OK).body(appUserResponseDtos);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<ObjectCreateResponseDto> deleteUserByUserName(@PathVariable("username") String username) {
        ObjectCreateResponseDto objectCreateResponseDto = appUserService.deleteUserByUserName(username);

        return ResponseEntity.status(HttpStatus.OK).body(objectCreateResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody AppUserWithProfileDto appUserWithProfileDto) {
        appUserService.updateAppUser(appUserWithProfileDto);

        return ResponseEntity.status(HttpStatus.OK).body("User has been updated");
    }
}
