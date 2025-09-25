package com.hc.Demo1;

// Student.java
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Student extends Person implements Complain {
    private String studentId;

    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    // 回答问题
    public Answer answerQuestion(Question question) {
        System.out.println(getName() + "正在回答：" + question.getContent());
        complain();

        String answerContent = question.getContent() + "的答案是：需要认真学习";
        return new Answer(answerContent, question);
    }

    // 接收分数
    public void receiveScore(Score score) {
        System.out.println(getName() + "收到分数：" + score.getValue() + "分");
        complain();
    }

    @Override
    public void complain() {
        System.out.println(getName() + "学生吐槽：题目好难啊！");
    }
}