package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");


//        DayOne dayOne = new DayOne();
//        long result = dayOne.processInputFile("src/main/resources/day_one.txt");
//        System.out.println(result);
//
//        //DayTwo
//        DayTwo dayTwo = new DayTwo();
//        long dayTwoResult = dayTwo.safeReportsCounterMethod("src/main/resources/day_two.txt");
//        System.out.println(dayTwoResult);


//        //DayThree
//        DayThree dayThree = new DayThree();
//        long l = dayThree.corruptedData("src/main/resources/day_three.txt");
//        System.out.println(l);
//
//        long l2 = dayThree.corruptedData2("src/main/resources/day_three.txt");
//        System.out.println(l2);

//        //DayFour
//        DayFour dayFour = new DayFour();
//        long l = dayFour.wordSearch("src/main/resources/day_four.txt");
//        System.out.println(l);

        DayFive dayFive = new DayFive();
        long l = dayFive.pageOrderCorrectnessChecker("src/main/resources/day_five.txt");
        System.out.println(l);
    }
}