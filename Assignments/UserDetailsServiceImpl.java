// @Project Manager Sam github.com/samm-w
// @author Mason github.com/pellemas
// @author Connor  github.com/HackdaNorth
// @author Taranpreet github.com/kaur6499
package com.VOICE.VOICESOFTWARE.UseCaseManagers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.VOICE.VOICESOFTWARE.Repositories.*;
import com.VOICE.VOICESOFTWARE.DomainObjects.*;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AccountRepository accRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Find the user based on the username
		Account user = accRepo.findByAccEmail(email);
		// If the user doesn't exist
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found.");
		}
		// Change the list of user roles into a list of GrantedAuthority
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (AccountUserRoles role : user.getRoles()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		// Create a Spring User based on the information above
		UserDetails  userDetails = (UserDetails)new User(user.getAccEmail(), 
				user.getAccPassword(), grantList);
		return userDetails;
	}


}