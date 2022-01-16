package org.docshift.usermanagement.dao.fragment;

import org.docshift.usermanagement.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Transactional
public class AppUserFragmentDaoImpl implements AppUserFragmentDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public AppUser getUserByUserName(String username) {
        String hql = "from AppUser au where au.username=:username";

        AppUser appUser = ( AppUser) entityManager
                .createQuery(hql)
                .setParameter("username", username)
                .getSingleResult();

        return appUser;
    }

    @Override
    public void deleteUserByUserName(String username) {
        String hql = "delete from AppUser au where au.username=:username";

         entityManager
                .createQuery(hql)
                .setParameter("username", username)
                 .executeUpdate();
    }
}
