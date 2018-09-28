/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.vo;

/**
 * Contact VO class.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class ContactVO {

	private String fullName;
	private String cnpj;
	private String cpf;
	private String phone;
	private String email;
	private String message;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}		

}
