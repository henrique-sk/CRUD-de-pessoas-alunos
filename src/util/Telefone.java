package util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Telefone {
	
	public static String formataFone(String telefone) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter("(##) ####-####");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(telefone);
		} catch (ParseException e) {
			return telefone;
		}
	}

}
