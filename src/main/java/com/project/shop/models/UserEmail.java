package com.project.shop.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserEmail {
	private String email;
	private String code;
	private String password;
	private String completionpassword;
}
