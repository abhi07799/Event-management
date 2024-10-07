package com.event.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime accountCreatedDate;
	
	@Column(nullable = false)
	private String userRole;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventModel event;

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
	
}
