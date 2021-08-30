package com.koreait.day3.generic;

public class Util {
    // 제네릭 메소드
    // 리턴타입이 무엇인지와 상관없이 제네릭 메서드라는 것을 컴파일러에게 알려줌
    // 리턴타입을 정의하기 전에 제네릭 타입에 대한 정의를 반드시 명시
    // 제네릭 클래스가 아닌 일반 클래스 내부에도 제네릭 메서드를 사용할 수 있음
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<T>();
        box.setT(t);

        return box;
    }
}
