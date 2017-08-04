package service;

public interface ISendEmailService {

    public void send(String content,String title,String address,String affix,String affixName);
}
