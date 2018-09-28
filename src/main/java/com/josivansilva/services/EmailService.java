/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.services;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.josivansilva.constants.Constants;
import com.josivansilva.vo.ContactVO;
import com.josivansilva.vo.EmailConfigurationVO;

/** 
 * EmailService class.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class EmailService {

	public final static EmailService INSTANCE = new EmailService();
	
	/**
	 * Default private constructor.
	 */
	private EmailService () {}
	
	/**
	 * Sends a HTML email.
	 * 
	 * @param emailConfigVO the email configuration bean.
	 * @param htmlContent the HTML content.
	 * @return a boolean indicating the success operation.
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean sendHtmlEmail (final EmailConfigurationVO emailConfigVO, String htmlContent) throws MessagingException, IOException {
		//logger.info ("Starting executing the method sendHtmlEmail.");
		boolean sent = false;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", emailConfigVO.getHost());
		props.put("mail.smtp.port", emailConfigVO.getPort());

		Session session = Session.getInstance (props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication (emailConfigVO.getUser(), emailConfigVO.getPassword());
			}
		  });		

		Message message = new MimeMessage(session);
		message.setFrom (new InternetAddress (emailConfigVO.getFrom()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailConfigVO.getTo()));
		message.setSubject (emailConfigVO.getSubject());
		message.setText (htmlContent);

		Transport.send (message);

		sent = true;
		
		System.out.println ("\nsent: " + sent + " \n");
		
	    return sent;
	}

	/**
	 * Sends a contact email.
	 * 
	 * @return a boolean indicating the success operation.
	 * @throws Exception
	 */
	public boolean sendContactEmail (ContactVO contactVO) throws Exception {
		boolean sent = false;
		EmailConfigurationVO emailConfigVO = null;
		String htmlContent = null;
		emailConfigVO = new EmailConfigurationVO ();
		emailConfigVO.setHost (Constants.DEFAULT_SMTP_HOST);
		emailConfigVO.setPort (Constants.DEFAULT_SMTP_PORT);
		emailConfigVO.setFrom (Constants.DEFAULT_SENDER);
		emailConfigVO.setMailer (Constants.DEFAULT_HEADER_MAILER);
		emailConfigVO.setUser (Constants.DEFAULT_SMTP_AUTH_USER);
		emailConfigVO.setPassword (Constants.DEFAULT_SMTP_AUTH_PWD);
		emailConfigVO.setTo (Constants.DEFAULT_RECEIVER);
		emailConfigVO.setSubject (Constants.CONTACT_SUBJECT);
		htmlContent = this.buildHtmlContent (contactVO);
		try {
			sent = sendHtmlEmail (emailConfigVO, htmlContent);			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return sent;
	}
	
	/**
	 * Builds the Email Html content.
	 * 
	 */
	private String buildHtmlContent (ContactVO contactVO) {
		StringBuilder sb = new StringBuilder();
		sb.append ("Contato Form\n\n");
		sb.append ("Nome completo: " + contactVO.getFullName() + "\n");
		sb.append ("CNPJ: " + contactVO.getCnpj() + "\n");
		sb.append ("CPF: " + contactVO.getCpf() + "\n");
		sb.append ("Fone: " + contactVO.getPhone() + "\n");
		sb.append ("Email: " + contactVO.getEmail() + "\n");
		sb.append ("Mensagem: " + contactVO.getMessage() + "\n");
		return sb.toString();
	}
	
}
