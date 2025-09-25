package com.hc.Demo1;

// Main.java
public class Main {
    public static void main(String[] args) {
        // 创建老师和学生
        Teacher teacher = new Teacher("张老师", 35, "数学");
        Student student = new Student("小明", 18, "S001");

        System.out.println("=== 教学互动开始 ===");
        System.out.println("老师信息：" + teacher.getName() + "，年龄：" + teacher.getAge() + "，科目：" + teacher.getSubject());
        System.out.println("学生信息：" + student.getName() + "，年龄：" + student.getAge() + "，学号：" + student.getStudentId());
        System.out.println();

        // 第一轮互动
        System.out.println("【第一题】");
        Question question1 = teacher.sendQuestion("1+1等于几？", 1);
        Answer answer1 = student.answerQuestion(question1);
        Score score1 = teacher.evaluateAnswer(answer1);
        student.receiveScore(score1);
        System.out.println();

        // 第二轮互动
        System.out.println("【第二题】");
        Question question2 = teacher.sendQuestion("什么是面向对象编程？", 5);
        Answer answer2 = student.answerQuestion(question2);
        Score score2 = teacher.evaluateAnswer(answer2);
        student.receiveScore(score2);
        System.out.println();

        // 第三轮互动（高难度题目）
        System.out.println("【第三题】");
        Question question3 = teacher.sendQuestion("请解释Java虚拟机的工作原理", 8);
        Answer answer3 = student.answerQuestion(question3);
        Score score3 = teacher.evaluateAnswer(answer3);
        student.receiveScore(score3);

        System.out.println("=== 教学互动结束 ===");
    }
}