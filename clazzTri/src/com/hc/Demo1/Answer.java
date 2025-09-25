package com.hc.Demo1;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Answer {
    private String content;
    private Question question;
}