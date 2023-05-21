
package System;

import com.sendmail.SendMail;

public class Main {
    public static void main(String args[]){
        SendMail sendMail = new SendMail();
        sendMail.setTo("toemail@");
        
        sendMail.sendEmail();
    }
}
