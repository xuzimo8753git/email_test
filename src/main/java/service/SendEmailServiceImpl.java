package service;

import pojo.MyEmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SendEmailServiceImpl implements ISendEmailService{


    @Override
    public void send(String content, String title, String address,String affix,String affixName) {
        {
            MyEmail myEmail=MyEmail.getEmail();
            String host = myEmail.getHost();
            String user = myEmail.getUser();
            String pwd = myEmail.getPwd();

            Properties props = new Properties();

            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", host);
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");

            // 用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);

            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            // 用（你可以在控制台（console)上看到发送邮件的过程）
            session.setDebug(true);

            // 用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
            try {
                // 加载发件人地址
                message.setFrom(new InternetAddress(myEmail.getFrom()));
                // 加载收件人地址
                message.addRecipients(Message.RecipientType.TO, address);
                List<InternetAddress> list = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
                String []median=toString().split(",");//对输入的多个邮件进行逗号分割
                for(int i=0;i<median.length;i++){
                    list.add(new InternetAddress(median[i]));
                }
                InternetAddress[] listaddress =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
                message.addRecipients(Message.RecipientType.TO, listaddress);
                // 加载标题
                message.setSubject(title);

                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                Multipart multipart = new MimeMultipart();

                // 设置邮件的文本内容
                BodyPart contentPart = new MimeBodyPart();
                contentPart.setText(content);
                multipart.addBodyPart(contentPart);
                // 添加附件
                if(affix != null && !"".equals(affix))
                {
                    BodyPart messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(affix);
                    // 添加附件的内容
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    // 添加附件的标题
                    // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                    sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                    // 添写附件名称中文会有乱码问题,尝试更改下面编码格式在发送,注意添写附件名需要加上后缀(例:.txt)
                    messageBodyPart.setFileName("=?GBK?B?"+ enc.encode(affixName.getBytes()) + "?=");
                    multipart.addBodyPart(messageBodyPart);
                }

                // 将multipart对象放到message中
                message.setContent(multipart);
                // 保存邮件
                message.saveChanges();
                // 发送邮件
                Transport transport = session.getTransport("smtp");
                // 连接服务器的邮箱
                transport.connect(host, user, pwd);
                // 把邮件发送出去
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
