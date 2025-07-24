package br.com.farias.rest_com_springboot_e_java.converters;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;

        // converter o padrão BR 10,25 para o padrão US 10.25
        String number = strNumber.replaceAll(",", ".");
        // agora converte a String em double
        if(isNumeric(number)) return Double.parseDouble(number);

        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;

        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
