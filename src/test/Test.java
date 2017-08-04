package test;

import service.ISendEmailService;
import service.SendEmailServiceImpl;
import thread.EmailThread;

public class Test {
    public static void main(String[] args){
        ISendEmailService service=new SendEmailServiceImpl();
//        service.send("你好这是我今天做的项目","徐子默的标题","442956559@qq.com");

//        Thread thread1 = new Thread(new EmailThread("你好这是我今天做的项目163163","徐子默的标题2","zhangpenghao102@163.com"));
//        Thread thread = new Thread(new EmailThread("你好这是我今天做的项目qq","徐子默的标题","442956559@qq.com"));
        Thread thread2 = new Thread(new EmailThread("你好张鹏浩这是我今天做的项目0","徐先生","zhangpenghao102@163.com","D:\\sdf.txt","a.txt"));
//        thread.run();
//        thread1.run();
        thread2.run();
    }
}