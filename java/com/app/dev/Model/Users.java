package com.app.dev.Model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class Users implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true, nullable = false)
	private String username;
	private String password;
	private boolean enabled;
	private String verificationCode;
	private LocalDateTime verificationexpire;
	private String userEmail;
	@Enumerated(EnumType.STRING)
	private Role role;

	public Users() {

	}

	public Users(String userEmail, String username, String password) {
		this.userEmail = userEmail;
		this.password = password;
		this.username = username;
	}

	public Users(Long id, String username, String password, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users(Long id, String username, String password, boolean enabled, String verificationCode,
			LocalDateTime verificationexpire, String userEmail, Role role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.verificationCode = verificationCode;
		this.verificationexpire = verificationexpire;
		this.userEmail = userEmail;
		this.role = role != null ? role : Role.USER;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationcode) {
		this.verificationCode = verificationcode;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (role == null) {
			return List.of();
		}
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	public LocalDateTime getVerificationexpire() {
		return verificationexpire;
	}

	public void setVerificationexpire(LocalDateTime verificationexpire) {
		this.verificationexpire = verificationexpire;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
