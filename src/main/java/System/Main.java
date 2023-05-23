
package System;

import com.sendmail.SendMail;
import configsec.ConfigSec;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    public static void main(String args[]){
        Date date = new Date();
        int today = date.getDate();
        ConfigSec config = new ConfigSec();
        int option = 0;
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask(){
            public void run(){
                String[] auth = config.readConfig();
                SendMail sendMail = new SendMail();
        
                if(auth[0] != null && auth[1] != null && auth[2] != null){
                    sendMail.setFrom(auth[0]);
                    sendMail.setPass(auth[1]);
                    sendMail.setTo(auth[2]);
            
                    sendMail.sendEmail();
                }else{
                    System.out.println("VOCÊ PRECISA CONFIGURAR AS INFORMAÇÕES DE ENVIO E AUTENTICAÇÃO!");
                }
                System.exit(0);
            }
        };
        
        int limitTime = 5000;
        timer.schedule(task, limitTime);
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("***INSIRA 1 PARA CONFIGURAR AS INFORMAÇÕES DE ENVIO E AUTENTICAÇÃO!***");
        System.out.println("***INSIRA 2 PARA CADASTRAR NOVO BOLETO!***");
        
        System.out.println("->: ");  option = sc.nextInt();
        timer.cancel();
        
        
        switch(option){
            case 1:
                Scanner sc2 = new Scanner(System.in);
                System.out.println("From: "); String from = sc2.nextLine();
            
                System.out.println("Pass: "); String pass = sc2.nextLine();
            
                System.out.println("To: "); String to = sc2.next();
            
            
                config.setFrom(from);
                config.setPass(pass);
                config.setTo(to);
                config.writeConfig();         
                sc2.close();
            break;
            case 2: 
                Scanner sc3 = new Scanner(System.in);
                System.out.println("INSIRA O BOLETO NO SEGUINTE FORMATO: NOME DO BOLETO, DIA VENCIMENTO Ex:(cartão, 10)");
            break;
            default: 
                System.out.println("Opção inexistente!");
        }
        
        
        
        sc.close();
                                      
       System.exit(0);
    }
}