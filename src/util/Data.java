package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	public static Date stringParaData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");	
		
		Date paraData = null;
		try {
			paraData = sdf.parse(data);
		} catch (ParseException e) {
			System.out.println("Formato de data incompatível. ERRO -> "
					+ e.getMessage());
		}
		
		return paraData;
	}

}
