package com.hc.Demo1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class Teacher extends Person implements Complain {
    private String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    // 发布题目
    public Question sendQuestion(String content, int difficulty) {
        System.out.println(getName() + "老师发布题目：" + content);
        complain();
        return new Question(content, difficulty);
    }

    // 批改答案
    public Score evaluateAnswer(Answer answer) {
        System.out.println(getName() + "老师正在批改" + answer.getQuestion().getContent());

        int score = 10 - answer.getQuestion().getDifficulty();
        score = Math.max(0, Math.min(10, score));

        complain();
        System.out.println(getName() + "老师给出分数：" + score);
        return new Score(score, answer);
    }

    @Override
    public void complain() {
        System.out.println(getName() + "老师吐槽：批作业好累啊！");
    }
}