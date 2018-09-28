/* Copyright josivanSilva (Developer); 2015-2017 */

package com.josivansilva.util;

import java.util.InputMismatchException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Utils class.
 * 
 * @author josivan@josivansilva.com
 *
 */
public class Utils {

	public static final String BASENAME = "com.josivansilva.messages";
	
	/**
	 * Gets a resource bundle property value given its key.
	 * 
	 * @param key the key.
	 * @return the property value.
	 */
	/*public static String getResourceBundleProperty (String key) {
		String propertyValue = "";
		ResourceBundle bundle = ResourceBundle.getBundle (BASENAME, FacesContext.getCurrentInstance().getViewRoot().getLocale());
		propertyValue = bundle.getString (key);		
		return propertyValue;
	}*/
	
	/**
	 * Checks if a string is empty or not.
	 * 
	 * @param s the string.
	 * @return the result.
	 */
	public static boolean isEmpty (String s) {
		boolean success = false;
		if (s == null || s.length() == 0) {
			success = true;
		}
		return success;
	}
	
	/**
	 * Checks if an email is valid or not.
	 * 
	 * @param s the email.
	 * @return the result.
	 */
	public static boolean isValidEmail (String s) {
	    String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    return s.matches (pattern);
	}
	
	/**
	 * Checks is a given mobile number is valid or not.
	 * 
	 * <ul>Valid mobile numbers:
	 *  <li>51983204050</li>
	 *  <li>5183204050</li>
	 *  <li>51-58320-4050</li>
	 *  <li>51-8320-4050</li>
	 *  <li>51 58320 4050</li>
	 *  <li>(51)-58320-4050</li>
	 *  <li>(51) 8320 4050</li>  
	 *  <li>(51) 83204050</li>
	 * </ul>
	 *  
	 * @param phone the phone number.
	 * @return the operation result.
	 */
	public static boolean isValidPhone (String phone) {
		boolean isValid = false;
		if (phone.matches("\\d{10,11}")
				|| phone.matches("\\d{2}[-\\s]\\d{4,5}[-\\s]?\\d{4}")
				|| phone.matches("\\(\\d{2}\\)[-\\s]\\d{4,5}[-\\s]?\\d{4}")) {
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * Checks if the given cnpj is valid.
	 * 
	 * @param CNPJ the cnpj.
	 * @return the flag containing the result.
	 */
	public static boolean isCNPJ (String CNPJ) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
	        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
	        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
	        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
	        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		    (CNPJ.length() != 14))
		       return(false);

		 char dig13, dig14;
		 int sm, i, r, num, peso;

		 // "try" - protege o código para eventuais erros de conversao de tipo (int)
		 try {
			  // Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
				// converte o i-ésimo caractere do CNPJ em um número:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posição de '0' na tabela ASCII)
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);

		      // Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);

		      // Verifica se os dígitos calculados conferem com os dígitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		      
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
	}
	
	/**
	 * Validates a CPF.
	 * 
	 * @param cpf the CPF.
	 * @return the operation result.
	 */
	public static boolean isCPF (String CPF) {
		if (CPF.equals("00000000000") 
				|| CPF.equals("11111111111") 
				|| CPF.equals("22222222222") 
				|| CPF.equals("33333333333") 
				|| CPF.equals("44444444444") 
				|| CPF.equals("55555555555") 
				|| CPF.equals("66666666666") 
				|| CPF.equals("77777777777") 
				|| CPF.equals("88888888888") 
				|| CPF.equals("99999999999") 
				|| CPF.length() != 11) {
			return false;
		}	       

		char dig10, dig11;
		int sm, i, r, num, peso;
		
		try {
		      // Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i = 0; i < 9; i++) {              
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0         
				// (48 eh a posicao de '0' na tabela ASCII)         
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if (r == 10 || r == 11) {
		    	  dig10 = '0'; 
		      } else {
		    	  dig10 = (char)(r + 48); // converte no respectivo caractere numerico  
		      }

		      // Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for ( i = 0; i < 10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if (r == 10 || r == 11) {
		    	  dig11 = '0'; 
		      } else {
		    	  dig11 = (char)(r + 48);  
		      }

		      // Verifica se os digitos calculados conferem com os digitos informados.
		      if (dig10 == CPF.charAt(9) && dig11 == CPF.charAt (10)) {
		    	  return true; 
		      } else {
		    	  return false;
		      }
		      
		} catch (InputMismatchException e) {
		        return false;
		}		
	}
	
	/**
	 * Gets the CPF as number.
	 * 
	 * @param cnpj the cnpj.
	 * @return the cnpj as number.
	 */
	public static String getCnpjAsNumber (String cnpj) {
		String result = null;
		result = cnpj.replaceAll ("\\.", "");
		result = result.replace("/", "");
		result = result.replace("-", "");
		return result;
	}
	
	/**
	 * Gets the CPF as number.
	 * 
	 * @param cpf the cpf.
	 * @return the cpf as number.
	 */
	public static String getCpfAsNumber (String cpf) {
		String result = null;
		result = cpf.replaceAll ("\\.", "");
		result = result.replace("-", "");
		return result;
	}
	
}
