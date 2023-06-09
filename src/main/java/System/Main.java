
package System;

import com.sendmail.SendMail;
import configsec.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    
    public static TimerTask task(){
        ConfigSec config = new ConfigSec();
        ConfigBill configBill = new ConfigBill();
        Date date = new Date();
        int today = date.getDate();
        
        
        TimerTask task = new TimerTask(){
            public void run(){              
                String[] auth = config.readConfig();
                SendMail sendMail = new SendMail();
                ArrayList<String> bills = configBill.readConfig();
                
                if(auth[0] != null && auth[1] != null && auth[2] != null){
                    int countToday = 0;
                    if(bills.size()>0){
                        for(int i = 0; i<bills.size(); i++){
                        
                            String bill = bills.get(i);
                            
                            String[] billDatas = bill.split(",",2);
                            if(Integer.parseInt(billDatas[1]) == today){
                                sendMail.setFrom(auth[0]);
                                sendMail.setPass(auth[1]);
                                sendMail.setTo(auth[2]);
            
                                sendMail.sendEmail(billDatas[0]);
                                countToday++;
                            }
                                                    
                        }
                        if(countToday == 0){
                                System.out.println("NENHUM BOLETO POR HOJE!");
                        }   
                    }else{
                        System.out.println("NENHUM BOLETO FOI CADASTRADO!");
                    }
                }else{
                    System.out.println("VOCÊ PRECISA CONFIGURAR AS INFORMAÇÕES DE ENVIO E AUTENTICAÇÃO!");                  
                }                
                        
                System.exit(0);
            }
        };
        
        return task;
        
    }
    
    public static void menu(TimerTask task, int limitTime){
        Scanner sc = new Scanner(System.in);
        Timer timer = new Timer();      
        ConfigSec config = new ConfigSec();
        ConfigBill configBill = new ConfigBill();
        int option = 0;
        
        
        timer.schedule(task, limitTime);
        
        System.out.println("***PARA PROSSEGUIR COM A EXECUÇÃO AGUARDE 5 SEGUNDOS!***");
        System.out.println("***INSIRA 1 PARA CONFIGURAR AS INFORMAÇÕES DE ENVIO E AUTENTICAÇÃO!***");
        System.out.println("***INSIRA 2 PARA CADASTRAR NOVO BOLETO!***");
        
        
        System.out.println("->: ");  option = sc.nextInt();
        timer.cancel();
        
        
        switch(option){
            case 1:
                Scanner sc2 = new Scanner(System.in);
                //sc.close();
                System.out.println("From: "); String from = sc2.nextLine();
            
                System.out.println("Pass: "); String pass = sc2.nextLine();
            
                System.out.println("To: "); String to = sc2.next();
            
            
                config.setFrom(from);
                config.setPass(pass);
                config.setTo(to);
                config.writeConfig();
                
                //sc2.close();
                
                menu(task(), limitTime);
            break;
            case 2: 
                Scanner sc3 = new Scanner(System.in);
                
                System.out.println("NOME DO BOLETO: "); String bill = sc3.nextLine();
                System.out.println("DIA DE VENCIMENTO: "); String day = sc3.next();
                
                configBill.setBill(bill.toUpperCase());
                configBill.setDay(Integer.parseInt(day));
                configBill.writeConfig();
                
                //sc3.close();
                      
                menu(task(), limitTime);
            break;
            default: 
                System.out.println("Opção inexistente!");
                //sc.close();
                menu(task(), limitTime);
        }       
        
       
        
    }
    
    public static void main(String args[]){       
        int limitTime = 5000;
        
        menu(task(), limitTime);
        
       System.exit(0);
    }
}