// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499

package com.VOICE.VOICESOFTWARE.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.VOICE.VOICESOFTWARE.DomainObjects.AccountUserRoles;

public interface RoleRepository extends CrudRepository<AccountUserRoles, Integer> {

	public AccountUserRoles findByRolename(String rolename) ;
}