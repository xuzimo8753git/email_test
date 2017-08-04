package pojo;

import util.PropterUtil;

import java.util.Properties;

public class MyEmail {
    private static MyEmail email;
    public static MyEmail getEmail(){

        if (email!=null){
            return email;
        }
        else {
            email=new MyEmail();
            Properties properties = PropterUtil.getProperties("email_config.properties","utf-8");
            email.from=properties.getProperty("from");
            email.host=properties.getProperty("host");
            email.user=properties.getProperty("user");
            email.pwd=properties.getProperty("pwd");
            return  email;
        }
    }

    private String from;//发送方邮箱
    private String user;//发送方邮箱帐号
    private String pwd;//发送方邮箱密码
    private String host;

    public String getFrom() {
        return from;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "MyEmail{" +
                "from='" + from + '\'' +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
