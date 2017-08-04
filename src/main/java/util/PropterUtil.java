package util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by xyj on 2017/2/9.
 */
public class PropterUtil {

    public static void main(String  [] args)
    {
        Properties p = getProperties("email_config.properties","utf-8");
        System.out.println(p);
    }

    public static Properties getProperties(String filepath,String charset) {
        Properties pro = new Properties();
        Reader reader = null;
        InputStream is = null;
        try{
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filepath);
            reader = new InputStreamReader(is, Charset.forName(charset));
            pro.load(reader);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return pro;
    }
}
