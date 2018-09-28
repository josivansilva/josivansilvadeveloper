/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.controller;

import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Language Controller.
 * 
 * @author josivan@josivansilva.com
 *
 */
@ManagedBean(name = "languageController")
@RequestScoped
public class LanguageController { 
	
	private String localeCode;

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	
	/**
	 * Checks language from query string parameters.
	 */
	private void checkLanguageFromParam () {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();		
		if (params.get("lang") != null && !"".equals(params.get("lang"))) {
			localeCode = params.get("lang");
		} else {
			localeCode = "en_US";
		}
	}	

	/**
	 * Set the Locale based on the lang string param.
	 */
	public void countryLocaleCodeChanged() {
		checkLanguageFromParam ();
		Locale locale = null;		
		if (localeCode.equals("pt_BR")) {			
			locale = new Locale ("pt", "BR");		
		} else if (localeCode.equals("en_US")) {			
			locale = new Locale("en", "US");		
		} else {			
			locale = new Locale ("en", "US");			
		}
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);		
	}

}
