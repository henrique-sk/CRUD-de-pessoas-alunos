package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.SistemaException;

public class Data {
	
	public static Date stringParaData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");	
		
		Date paraData = null;
		try {
			paraData = sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("Formato de data incompatível. ERRO -> "
					+ e.getMessage());
		}
		
		return paraData;
	}
	
	public static String dataParaString(Date data) throws SistemaException {
		
		String paraString = DateFormat.getInstance().format(data);
		
		return paraString;		
	}

}
