package basar.domain;

public final class PriceFormatUtil {

	
	public static String formatPriceLongToString(long price){
		if(price < 0){
			return "-1,00";
		}
		String strnumber = String.valueOf(price);
		if(strnumber.length() < 2){
			return "0,0" + strnumber;
		}
		String euro = strnumber.substring(0, strnumber.length()-2);
		String cent = strnumber.substring(strnumber.length()-2);
		if(cent.length() == 1){
			cent = cent +"0";
		}
		if(euro.length() == 0){
			euro = "0";
		}
		return euro + "," + cent;
	}
	
}
