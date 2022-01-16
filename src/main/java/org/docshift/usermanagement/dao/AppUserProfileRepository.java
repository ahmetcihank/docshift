package org.docshift.usermanagement.dao;

import org.docshift.usermanagement.domain.AppUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserProfileRepository extends JpaRepository<AppUserProfile, Long> {
}
