import java.util.Scanner;

public class Main {
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

    private static boolean checkNumSystem(String numSystem) {
        if(numSystem.isEmpty()) return false;
        String numbers = "0123456789";
        for(int i = 0; i < numSystem.length(); i++)
            if(!numbers.contains(numSystem.charAt(i)+ "")) return false;
        if(Integer.parseInt(numSystem) > 16 || Integer.parseInt(numSystem) < 2) return false;
        return true;
    }

    private static boolean checkNumber(String number, String numSystem) {
        if(number.isEmpty()) return false;
        String numbers = "0123456789ABCDEF";
        if(number.charAt(0) == '-') number = number.substring(1);
        for(int i = 0; i < number.length(); i++) {
            if(!((numbers.substring(0, Integer.parseInt(numSystem))).contains(number.charAt(i) + ""))) return false;
        }
        return true;
    }

    private static void program() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите изначальную СС:");
        String numSystem1 = in.nextLine();
        while(!checkNumSystem(numSystem1)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите изначальную СС:");
            numSystem1 = in.nextLine();
        }
        System.out.println("Введите СС в которую нужно перевести:");
        String numSystem2 = in.nextLine();
        while(!checkNumSystem(numSystem2)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите СС в которую нужно перевести:");
            numSystem2 = in.nextLine();
        }
        System.out.println("Введите число:");
        String number;
        number = in.nextLine();
        while(!checkNumber(number, numSystem1)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите число:");
            number = in.nextLine();
        }

        String res = tenToOther(otherToTen(number, numSystem1), numSystem2);
        System.out.println("Результат: " + res);
        if(Integer.parseInt(numSystem2) == 2) System.out.println("ПК " + binToDir(res));

    }

    public static void main(String[] args) {
        program();
    }
}