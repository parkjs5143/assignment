package com.koreait.day3.generic;

public class Main {
    public static void main(String[] args) {
        Box<Integer> box1 = Util.<Integer>boxing(100);
        int intValue = box1.getT();

        Box<String> box2 = Util.boxing("문자열");
        String stringValue = box2.getT();

        System.out.println("intValue : " + box1);
        System.out.println("stringValue : " + box2);
    }
}
