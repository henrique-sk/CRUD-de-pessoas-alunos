package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class Formatadores {
	// FORMATADORES DE DATAS
	public static Date stringParaData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		
		Date paraData = null;
		try {
			paraData = sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("Formato de data incompatível");
		}
		
		return paraData;
	}
	
	public static String formataData(Date dataParam) {
		String data = DateFormat.getDateTimeInstance(DateFormat.SHORT,
				DateFormat.SHORT).format(dataParam);
		
		return data;
	}
	
	public static String formataNascimento(Date dataParam) {
		String data = DateFormat.getDateInstance(DateFormat.SHORT)
				.format(dataParam);
		
		return data;
	}
	
	// FORMATADOR DE NOTA
	public static boolean isNumeric(String nota) {
		Pattern padrao = Pattern.compile("-?\\d+(\\.\\d+)?");
	    if (nota == null) {
	        return false; 
	    }
	    return padrao.matcher(nota).matches();
	}

	//FORMATADOR DE TELEFONE
	public static String formataFone(String telefone) {
		MaskFormatter mf;
		try {
			mf = new MaskFormatter("(##) #####-####");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(telefone);
		} catch (ParseException e) {
			return telefone;
		}
	}
	
}
