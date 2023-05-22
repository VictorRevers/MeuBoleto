
package configsec;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConfigSec {
    private String from;
    private String pass;
    
    public void setFrom(String from){
        this.from = from;
    }
    
    public String getFrom(){
        return this.from;
    }
    
    public void setPass(String pass){
         this.pass = pass;
    }
    
    public String getPass(){
        return this.pass;
    }
    
    
    private File configFile(){
        File file = new File("C://MeuBoleto/config.txt");
        
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
            
            if(file != null){
                try{
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    bw.write(this.from);
                    bw.newLine();
                    bw.write(this.pass);
                    
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
    
    public String[] readConfig(){
        boolean fileDir = this.configDir();
        
        if(fileDir){
            File file = this.configFile();
            int i = 0;
            String[] configs = new String[2];
            
            if(file != null){
                try{
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    
                    while(br.ready()){
                        
                        configs[i] = br.readLine();
                        i++;
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
