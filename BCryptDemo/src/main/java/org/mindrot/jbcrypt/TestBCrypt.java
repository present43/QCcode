package org.mindrot.jbcrypt;

public class TestBCrypt {

    public static void main(String[] args) {

        //创建盐 随机的
        String gensalt = BCrypt.gensalt();
        System.out.println(BCrypt.gensalt());

        // 加入盐使密码 加密
        BCrypt.hashpw("123456", gensalt);
        System.out.println(BCrypt.hashpw("123456", gensalt));
    }
}
