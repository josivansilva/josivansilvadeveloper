/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Abstract Controller.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class AbstractController {
	
	/** 
	 * Adds a info message to the faces message.
	 * 
	 * @param message the message.
	 */
	protected void addInfoMessage (String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }
    
	/** 
	 * Adds a warn message to the faces message.
	 * 
	 * @param message the message.
	 */
	protected void addWarnMessage (String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", message));
    }
     
    /** 
	 * Adds an error message to the faces message.
	 * 
	 * @param message the message.
	 */
	protected void addErrorMessage (String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message));
    }
    
    /** 
	 * Adds a fatal message to the faces message.
	 * 
	 * @param message the message.
	 */
	protected void addFatalMessage (String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", message));
    }
    
}
