import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите математическое выражение (два числа и знак оператора).");
        String input = scan.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String result;
        String [] expression = input.split("[+\\-*/]");
        boolean containsChar = containsCharCheck(input);
        if (!containsChar) throw new Exception("Выражение должно содержать знаки '+, -, *, /'");
        if (expression.length !=2) throw new Exception("Введите два числа!");
        boolean isRomanNumber;
        if(!Roman.isRoman(expression[0]) && !Roman.isRoman(expression[1])) {
            num1 = Integer.parseInt(expression[0]);
            num2 = Integer.parseInt(expression[1]);
            isRomanNumber = false;
        } else if (Roman.isRoman(expression[0]) && Roman.isRoman(expression[1])) {
            num1 = Roman.convertToArabic(expression[0]);
            num2 = Roman.convertToArabic(expression[1]);
            isRomanNumber = true;
        } else { throw new Exception("Числа должны быть в одном формате и меньше 10!");
        }
        if(num1>10 || num2>10 || num1<1 || num2<1){
            throw new Exception("Введите числа от 1 до 10!");
        }
        int arabic = calculation(num1, num2, input);
        if(isRomanNumber){
            if(arabic<=0){
                throw new Exception("Римское число не может быть отрицательным или равным нулю!");
            } result=Roman.convertToRoman(arabic);
        } else{
            result = String.valueOf(arabic);
        }
        return result;
    }
    static int calculation(int a, int b, String operand){
        if (operand.contains("+")) return a+b;
        if (operand.contains("-")) return a-b;
        if (operand.contains("*")) return a*b;
        else return a/b;
    }
    static boolean containsCharCheck(String val){
        String[] operand= {"+","-","*","/"};
        for (int i=0; i< operand.length; i++){
            if (val.contains(operand[i])){
                return true;
            }
        }
        return false;
    }

     class Roman{
        static String [] romanOnesArray= new String[]{"I","II","III","IV","V","VI","VII","VIII","IX","X"};
         static String[] romanTensArray= new String[]{"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};

         static int convertToArabic(String roman){
             for(int i=0; i<romanOnesArray.length; i++){
                 if(roman.equals(romanOnesArray[i])){
                     return i+1;
                 }
             } return -1;
         }
        static String convertToRoman(int arabic){
             if(arabic<=10){
                 return romanOnesArray[arabic-1];
             } else {
                 int tens= arabic/10;
                 int ones=arabic-(10*tens);
                 if(ones==0){
                     return romanTensArray[tens-1];
                 } else{
                 return romanTensArray[tens-1]+romanOnesArray[ones-1];}
             }
        }
        static boolean isRoman(String val) {
            for (String s : romanOnesArray)
                if (val.equals(s)) {
                    return true;
                }
            return false;
        }
    }
}
