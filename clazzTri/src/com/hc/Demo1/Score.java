package com.hc.Demo1;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Score {
    private int value;
    private Answer answer;
}