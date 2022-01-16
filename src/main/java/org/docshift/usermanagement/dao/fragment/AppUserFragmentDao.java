package org.docshift.usermanagement.dao.fragment;

import org.docshift.usermanagement.domain.AppUser;

public interface AppUserFragmentDao {
    public AppUser getUserByUserName(String username);
    public void deleteUserByUserName(String username);
}
