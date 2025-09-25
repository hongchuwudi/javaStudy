package com.hc.Demo1;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Question {
    private String content;
    private int difficulty;
}