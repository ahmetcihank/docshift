package org.docshift.usermanagement.dao;

import org.docshift.usermanagement.dao.fragment.AppUserFragmentDao;
import org.docshift.usermanagement.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> , AppUserFragmentDao {
   AppUser findByUsername(String username);
   boolean existsByUsername(String username);
}
