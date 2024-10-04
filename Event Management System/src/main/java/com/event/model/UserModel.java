package com.event.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "full_name", nullable = false)
	private String userFullName;
	
	@Column(unique = true, nullable = false)
	private String userMail;
	
	@Column(unique = true, nullable = false)
	private String userMobile;
	
	@Column(nullable = false)
	private String userPassword;
	
	private String userAddress;
	
	@Column(nullable = false)
	private String userRole;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(userRole));
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return this.userPassword;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.userMail;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
