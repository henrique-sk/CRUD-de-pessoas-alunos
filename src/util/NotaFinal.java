package util;

import java.util.regex.Pattern;

public class NotaFinal {
	
	public static boolean isNumeric(String nota) {
		Pattern padrao = Pattern.compile("-?\\d+(\\.\\d+)?");
	    if (nota == null) {
	        return false; 
	    }
	    return padrao.matcher(nota).matches();
	}

}
