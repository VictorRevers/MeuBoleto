package configsec;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class ConfigBill {
    private String bill;
    private int day;
    
    public void setBill(String bill){
        this.bill = bill;
    }
    
    public void setDay(int day){
        this.day = day;
    }
    
    private File configFile(){
        File file = new File("C://MeuBoleto/bills.txt");
        
        if(!file.exists()){
               try{
                   file.createNewFile();
                   return file;
               }catch(Exception e){
                   System.out.println("ERRO: "+ e);
                   return null;
               }
        }else{
            return file;
        }
    }
     
    private boolean configDir(){
        File fileDir = new File("C:\\MeuBoleto");
          
        if(!fileDir.exists()){
            try{
                fileDir.mkdir();
                return true;
            }catch(Exception e){
                System.out.println("Erro: "+ e);
                return false;
            }                                  
        }else{                              
            return true;                    
        }
    }
    
     public void writeConfig(){
        boolean fileDir = this.configDir();
        
        if(fileDir){
            File file = this.configFile();
            ArrayList<String> bills = this.readConfig();
            
            if(file != null){
                try{
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                             
                    if(bills.size() > 0){
                        bw.newLine();
                        bw.append(this.bill+","+this.day);
                    }else{
                        bw.append(this.bill+","+this.day);
                    }
                        
                                                       
                    bw.close();
                    fw.close();
                }catch(Exception e){
                    System.out.println("Erro: "+ e);
                }
                
             
            }else{
                System.out.println("Erro ao configurar arquivo");
                System.exit(0);
            }
        }
                
    }
     
    public ArrayList<String> readConfig(){
        boolean fileDir = this.configDir();
        
        if(fileDir){
            File file = this.configFile();
            //int i = 0;
            ArrayList<String> configs = new ArrayList();
            
            if(file != null){
                try{
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    
                    while(br.ready()){
                        
                        configs.add(br.readLine());
                        
                    }
                    
                    br.close();
                    fr.close();
                    
                    return configs;
                }catch(Exception e){
                    System.out.println("Erro: "+ e);
                    return null;
                }
            }else{
                System.out.println("Erro ao configurar arquivo");                
                return null;                           
            }    
        }else{
            return null;
        }
        
    }
     
     
    
}
