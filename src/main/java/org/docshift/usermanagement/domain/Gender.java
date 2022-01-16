package org.docshift.usermanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String gender;
}
