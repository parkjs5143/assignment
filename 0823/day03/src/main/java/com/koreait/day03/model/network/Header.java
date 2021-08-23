package com.koreait.day03.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {

    // api 통신시간
    private LocalDateTime transactionTime;
    // api 응답코드
    private String resultCode;
    // api설명
    private  String description;

    private T data;

    // Ok
    public static<T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }// Data
    public static<T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    // error
    public static<T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description("ERROR")
                .build();
    }
}
