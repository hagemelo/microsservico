package br.com.jhage.core.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Configuration;

import br.com.jhage.core.exception.FirstNameException;

/***
 * 
 * @author Alexsander Melo
 * @since 09/01/2013
 * @version 1.0 Classe responsavel por Extrair o Primeiro e o Ultimo Nome de uma
 *          pessoa
 */
@Configuration
public class FirstAndLastNameHelper {
	
	
	private Pattern padrao = null;
	private Matcher matcher = null;
	
	private static final String PATTERN_FIRSTNAME_REMOVE = "\\s\\w*";
	private static final String PATTERN_LASTNAME_REMOVE = "\\w*\\s";
	
	public FirstAndLastNameHelper() {
		
	}
	
	public String getFirstName(final String value) throws FirstNameException {

		padrao = Pattern.compile(PATTERN_FIRSTNAME_REMOVE);
		matcher = padrao.matcher(value.trim());
		return matcher.replaceAll(" ").trim();
	}

	public String getLastName(final String value) throws Exception {

		padrao = Pattern.compile(PATTERN_LASTNAME_REMOVE);
		matcher = padrao.matcher(value.trim());
		return matcher.replaceAll(" ").trim();
	}

}
