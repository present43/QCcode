package org.mindrot.jbcrypt;

public class TestVerify {

    public static void main(String[] args) {
        //验证密码 返回布尔值
        boolean liuzheng = BCrypt.checkpw("123456", "$2a$10$SY2QzgAfcjoyLyWtUPWJoe5YAeCXsP6olY/Hp1SICTXz6wuUu.rmG");
        System.out.println(liuzheng);
    }
}
