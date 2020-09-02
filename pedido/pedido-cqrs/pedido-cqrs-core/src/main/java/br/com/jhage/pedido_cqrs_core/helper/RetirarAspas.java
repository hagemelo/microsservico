package br.com.jhage.pedido_cqrs_core.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.jhage.pedido_cqrs_core.excecao.RetirarAspasSimplesException;

public class RetirarAspas {

	public static String retirarAspasSimples(final String value) throws RetirarAspasSimplesException {
		try {
			Pattern padrao = null;
			Matcher matcher = null;
			padrao = Pattern.compile("'");
			matcher = padrao.matcher(value.trim());
			return matcher.replaceAll("").trim();
		} catch (Exception e) {

			throw new RetirarAspasSimplesException();
		}
	}

}
