package com.rabindra.LoginRegistration.appuser.regis.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.rabindra.LoginRegistration.appuser.AppUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

	
	@SequenceGenerator(
			name ="confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
		)
		@Id
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator= "confirmation_token_sequence"
		)
	private Long id;
	
	@Column(nullable=false)
	private String token;
	
	@Column(nullable=false)
	private LocalDateTime createDate;
	
	@Column(nullable=false)
	private LocalDateTime expireDate;
	
    private LocalDateTime confirmedAt;

	
	@ManyToOne
	@JoinColumn(
			nullable=false,
			name = "app_user_id"
	)
	private AppUser appUser;

	public ConfirmationToken(String token, LocalDateTime createDate, LocalDateTime expireDate,AppUser appUser) {
		this.token = token;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.appUser = appUser;
	}

	
	
	
}
