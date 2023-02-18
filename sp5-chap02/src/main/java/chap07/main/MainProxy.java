package chap07.main;

import chap07.calculator.ExeTimeCalculator;
import chap07.calculator.ImpeCalculator;
import chap07.calculator.RecCalculator;

public class MainProxy {
    public static void main(String[] args) {
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(ttCal1.factorial(20));
        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(ttCal2.factorial(20));
    }
}
