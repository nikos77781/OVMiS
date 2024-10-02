import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String binAddition(String n1, String n2) {
        String result = "";
        int sum = 0;
        int addition = 0;
        while(n1.length() < n2.length()) n1 = "0" + n1;
        while(n2.length() < n1.length()) n2 = "0" + n2;
        for(int i = n1.length() - 1; i > -1; i--) {
            sum = Integer.parseInt(n1.charAt(i) + "") + Integer.parseInt(n2.charAt(i) + "") + addition;
            if(sum > 1) {
                result = sum - 2 + result;
                addition = 1;
            }
            else {
                result = sum + result;
                addition = 0;
            }
        }
        if(addition == 1) result = addition + result;
        return result;
    }
    private static String binSubtraction(String n1, String n2) {
        String result = "";
        int sum = 0;
        int addition = 0;

        while(n1.length() < n2.length()) n1 = "0" + n1;
        while(n2.length() < n1.length()) n2 = "0" + n2;
        for(int i = n1.length() - 1; i > 0; i--) {
            sum = Integer.parseInt(n1.charAt(i) + "") - Integer.parseInt(n2.charAt(i) + "") - addition;
            if(sum == -1) {
                result = "1" + result;
                addition = 1;
            }
            else if(sum == -2) {
                result = "0" + result;
                addition = 1;
            }
            else {
                result = String.valueOf(sum) + result;
                addition = 0;
            }
        }
        result = result.replaceFirst("^0+", "");
        return result;
    }
    private static String binMultiplication(String n1, String n2) {
        String result = "0";
        String j = "";
        for(int i = n2.length() - 1; i > -1; i--, j += "0") {
            if(n2.charAt(i) == '1') result = (binAddition(result, n1 + j));
        }

        return result;
    }
    private static String binDivision(String n1, String n2) {
        String result = "";
        String delimoe = n1.charAt(0) + "";
        if(n2 == "0") return "На ноль делить нельзя!";
        for(int i = 1; i < n1.length(); i++) {
            delimoe += n1.charAt(i);
            if(Integer.parseInt(delimoe) < Integer.parseInt(n2)) {
                if(result != "") result += "0";
            }
            else {
                result += "1";
                delimoe = binSubtraction(delimoe, n2);
            }
        }
        if(result == "") return "0";
        return result;
    }
    private static boolean checkNumSystem(String numSystem) {
        if(numSystem.isEmpty()) return false;
        String numbers = "0123456789";
        for(int i = 0; i < numSystem.length(); i++)
            if(!numbers.contains(numSystem.charAt(i)+ "")) return false;
        if(Integer.parseInt(numSystem) > 16 || Integer.parseInt(numSystem) < 2) return false;
        return true;
    }

    private static boolean checkOperation(String operation) {
        if(operation.isEmpty()) return false;
        String numbers = "+-*/";
        if(numbers.contains(operation) && operation.length() == 1) return true;
        return false;
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
        return "ПК " + sign + nuls + result;
    }
    private static void program() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите СС:");
        String numSystem1 = in.nextLine();
        while(!checkNumSystem(numSystem1)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите СС:");
            numSystem1 = in.nextLine();
        }
        System.out.println("Введите первое число:");
        String number1;
        number1 = in.nextLine();
        while(!checkNumber(number1, numSystem1)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите первое число:");
            number1 = in.nextLine();
        }
        System.out.println("Введите второе число:");
        String number2;
        number2 = in.nextLine();
        while(!checkNumber(number2, numSystem1)) {
            System.out.println("Неверный ввод!");
            System.out.println("Введите второе число:");
            number2 = in.nextLine();
        }
        String sign1 = "", sign2 = "";
        if(number1.charAt(0) == '-') {
            sign1 = "-";
            number1 = number1.substring(1);
        }
        if(number2.charAt(0) == '-') {
            sign2 = "-";
            number2 = number2.substring(1);
        }

        number1 = tenToOther(otherToTen(number1, numSystem1), "2");
        number2 = tenToOther(otherToTen(number2, numSystem1), "2");

        String operation;
        do {
            System.out.println("Введите операцию ( + - * / ):");
            operation = in.nextLine();
        }
        while(!checkOperation(operation));


        switch(operation) {
            case ("+"): {
                if(sign1 == sign2) System.out.println(binToDir(sign1 + binAddition(number1, number2)));
                else if(sign1 == "-" && sign2 == "" && Integer.parseInt(number1) > Integer.parseInt(number2))
                    System.out.println(binToDir(sign1 + binSubtraction(number1, number2)));
                else if(sign1 == "-" && sign2 == "" && Integer.parseInt(number1) < Integer.parseInt(number2))
                    System.out.println(binToDir(sign2 + binSubtraction(number2, number1)));
                else if(sign1 == "" && sign2 == "-" && Integer.parseInt(number1) < Integer.parseInt(number2)) {
                    System.out.println(binToDir(sign2 + binSubtraction(number2, number1)));
                    System.out.println(sign2 + binSubtraction(number2, number1));}
                else if(sign1 == "" && sign2 == "-" && Integer.parseInt(number1) > Integer.parseInt(number2))
                    System.out.println(binToDir(sign1 + binSubtraction(number1, number2)));
                break;
            }
            case ("-"): {
                if(sign1 == "-" && sign2 == "-") System.out.println(binToDir(sign1 + binAddition(number1, number2)));
                else if(sign1 == "-" && sign2 == "") System.out.println(binToDir(sign1 + binAddition(number1, number2)));
                else if(sign1 == "" && sign2 == "-") System.out.println(binToDir(sign1 + binAddition(number2, number1)));
                else if(sign1 == "" && sign2 == "" && Integer.parseInt(number2) > Integer.parseInt(number1)) System.out.println(binToDir("-" + binSubtraction(number2, number1)));
                else System.out.println(binToDir(sign2 + binSubtraction(number1, number2)));
                break;
            }
            case ("*"): {
                if(sign1 == sign2) System.out.println(binToDir(sign2 + binMultiplication(number1, number2)));
                else if(sign1 != sign2) System.out.println(binToDir("-" + binMultiplication(number1, number2)));
                break;
            }
            case ("/"): {
                if(Integer.parseInt(number2) > Integer.parseInt(number1)) System.out.println("0");
                else if(sign1 == sign2) System.out.println(binToDir(sign2 + binDivision(number1, number2)));
                else System.out.println(binToDir("-" + binDivision(number1, number2)));
                break;
            }
        }
        System.out.println("\n---------------------------------------\n");
    }
    public static void main(String[] args) {
        while(true) program();
    }
}