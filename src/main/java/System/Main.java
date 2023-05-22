
package System;

import com.sendmail.SendMail;
import configsec.ConfigSec;
import java.util.Date;


public class Main {
    public static void main(String args[]){
        Date date = new Date();
        int today = date.getDate();
        ConfigSec config = new ConfigSec();
        
        /*if(args.length>0){
                                
            
        }*/
        
        config.setFrom("");
        config.setPass("");       
        config.writeConfig();
        
        String auth[] = config.readConfig();
        SendMail sendMail = new SendMail();
        
        if(auth.length>0){
            sendMail.setFrom(auth[0]);
            sendMail.setPass(auth[1]);
        }
        
        
        sendMail.setTo("");       
        sendMail.sendEmail();
        
        
        
        
        
        /*if(today == 21){
            
        }else{
            System.exit(0);
        }*/
        
       System.exit(0);
    }
}
