/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import com.josivansilva.services.EmailService;
import com.josivansilva.util.Utils;
import com.josivansilva.vo.ContactVO;

/**
 * Contact Controller.
 * 
 * @author josivan@josivansilva.com
 *
 */
@ManagedBean(name = "contactController")
@RequestScoped
public class ContactController extends AbstractController {

	private String fullNameForm;
	private String cnpjForm;
	private String cpfForm;
	private String phoneForm;
	private String emailForm;
	private String messageForm;
					
	private EmailService emailService = EmailService.INSTANCE;
		
	
	public String getFullNameForm() {
		return fullNameForm;
	}

	public void setFullNameForm(String fullNameForm) {
		this.fullNameForm = fullNameForm;
	}

	public String getCnpjForm() {
		return cnpjForm;
	}

	public void setCnpjForm(String cnpjForm) {
		this.cnpjForm = cnpjForm;
	}

	public String getCpfForm() {
		return cpfForm;
	}

	public void setCpfForm(String cpfForm) {
		this.cpfForm = cpfForm;
	}

	public String getPhoneForm() {
		return phoneForm;
	}

	public void setPhoneForm(String phoneForm) {
		this.phoneForm = phoneForm;
	}

	public String getEmailForm() {
		return emailForm;
	}

	public void setEmailForm(String emailForm) {
		this.emailForm = emailForm;
	}

	public String getMessageForm() {
		return messageForm;
	}

	public void setMessageForm(String messageForm) {
		this.messageForm = messageForm;
	}
	
	@PostConstruct
    public void init() {
		resetForm ();	
    }	
	
	/**
	 * Performs the send action.
	 * 
	 * @param event the action event.
	 */	
	public void processForm (ActionEvent actionEvent) {
		boolean success = false;		
		try {			 
			if (isValidForm()) {
				ContactVO contactVO = new ContactVO();
				contactVO.setFullName (this.fullNameForm);
				contactVO.setCnpj(this.cnpjForm);
				contactVO.setCpf(this.cpfForm);
				contactVO.setPhone (this.phoneForm);
				contactVO.setEmail (this.emailForm);				
				contactVO.setMessage (this.messageForm);
				success = emailService.sendContactEmail (contactVO);
				if (success) {
					super.addInfoMessage ("Contato enviado com sucesso.");					
				} else {
					super.addErrorMessage ("Ocorreu um erro no envio do contato.");														
				}
				resetForm ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
		
	/**
	 * Validates the contact form.
	 * 
	 */
	private boolean isValidForm () {
		if (Utils.isEmpty (this.fullNameForm)
				|| Utils.isEmpty (this.phoneForm)
				|| Utils.isEmpty (this.emailForm)	
				|| Utils.isEmpty (this.messageForm)) {
			super.addWarnMessage ("Preencha os campos corretamente.");
			return false;
		} else if (Utils.isEmpty (this.cnpjForm) && Utils.isEmpty (this.cpfForm)) {
			super.addWarnMessage ("Preencha o campo CNPJ ou o campo CPF.");
			return false;
		} else if (!Utils.isEmpty (this.cnpjForm) && !Utils.isCNPJ (Utils.getCnpjAsNumber(this.cnpjForm))) {
			super.addWarnMessage ("CNPJ incorreto.");
			return false;
		} else if (!Utils.isEmpty (this.cpfForm) && !Utils.isCPF (Utils.getCpfAsNumber(this.cpfForm))) {
	 		super.addWarnMessage ("CPF incorreto.");
			return false;
		} else if (!Utils.isEmpty (this.phoneForm) && !Utils.isValidPhone(this.phoneForm)) {
			super.addWarnMessage ("Fone incorreto.");
			return false;
		} else if (!Utils.isEmpty (this.emailForm) && !Utils.isValidEmail (this.emailForm)) {
			super.addWarnMessage ("Email incorreto.");
			return false;
		}
		return true;    
	}
	
	/**
	 * Resets the form.
	 */
	private void resetForm () {
		this.fullNameForm       = null;
		this.cnpjForm           = null;
		this.cpfForm            = null;
		this.phoneForm          = null;
		this.emailForm          = null;
		this.messageForm        = null;
	}	
	
}
