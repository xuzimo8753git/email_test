package thread;

import service.ISendEmailService;
import service.SendEmailServiceImpl;

public class EmailThread implements Runnable {

    private String title;
    private String content;
    private String address;
    private String affix;
    private String affixName;

    public EmailThread(){}

    public EmailThread(String title,String content,String address,String affix,String affixName){
        this.address=address;
        this.title=title;
        this.content=content;
        this.affix=affix;
        this.affixName=affixName;
    }
    @Override
    public void run() {
        ISendEmailService service=new SendEmailServiceImpl();
        service.send(this.title,this.content,this.address,this.affix,this.affixName);
    }
}
