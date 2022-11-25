package util;

public class Utils {
	public static String convertRedErrorMsg(String msg) {
		return "\u001B[31m" + msg + "\u001B[0m" + "\n";
	}
}
