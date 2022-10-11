package util;

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
}
