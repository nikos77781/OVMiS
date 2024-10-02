import java.util.Scanner;

public class Attemp2 {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-12 ));
    }

    private static String add(String num) {

    }

    private static String tenToOther(String num1, String numSystem) {
        String sign = "";
        String result = "";
        String numbers = "0123456789ABCDEF";
        if(num1.charAt(0) == '-') {
            sign = "-";
            num1 = num1.substring(1);
        }
        String tempStr = num1;
        while(true){
            if(Integer.parseInt(tempStr) >= Integer.parseInt(numSystem)) {
                result = numbers.charAt(Integer.parseInt(tempStr) % Integer.parseInt(numSystem)) + result;
                tempStr = Integer.toString(Integer.parseInt(tempStr) / Integer.parseInt(numSystem));
            }
            else {
                result = tempStr + result;
                break;
            }
        }
        return sign + result;
    }

    private static String otherToTen(String num2, String numSystem) {
        String sign = "";
        int result = 0;
        String numbers = "0123456789ABCDEF";
        if(num2.charAt(0) == '-') {
            sign = "-";
            num2 = num2.substring(1);
        }
        for(int i = num2.length() - 1, digit = 0; i >= 0; i--, digit++) {
            result += numbers.indexOf(num2.charAt(i)) * (int) Math.pow(Integer.parseInt(numSystem), digit);
        }

        return sign + result;
    }

    private static String binToDir(String n) {
        String sign = "0";
        if(n.charAt(0) == '-') {
            sign = "1";
            n = n.substring(1);
        }
        String nuls = "";
        String result = n;
        for(int i = 2, flag = 0; i < 6 && flag == 0; i++) {
            if(n.length() < (int) Math.pow(2,i)) {
                for(int j = (int) (Math.pow(2, i)) - n.length() - 1; j > 0; j--) nuls = nuls + "0";
                flag = 1;
            }
        }
        return sign + nuls + result;
    }

    private static String binToAdditional(String num) {
        String temp = "";
        String result = "";
        for(int i = 0; i < num.length(); i++) {
            if(num.charAt(i) == '1') temp += "0";
            else temp += "1";
        }

        return result;
    }

}
