package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
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
}
