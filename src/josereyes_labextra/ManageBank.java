/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josereyes_labextra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;

/**
 *
 * @author josec
 */
public class ManageBank {
    RandomAccessFile cuentas;
    
    public ManageBank(){
        try{
            File file = new File("BANCO");
            if(!file.exists())
                file.mkdir();
            cuentas=new RandomAccessFile("BANCO/cuentas.bnk","rw");
        }catch(FileNotFoundException e){
            
        }
    }
    
    public long buscar(int code)throws IOException{
        cuentas.seek(0);
        while(cuentas.getFilePointer()<cuentas.length()){
            if(code==cuentas.readInt()){
                return cuentas.getFilePointer();
            }else{
                cuentas.readUTF();
                cuentas.skipBytes(16);
                cuentas.readUTF();
            }
        }
        return 1;
    }
    
    public void addCuenta(int cod,String nombre,TipoCuenta tipo)throws AccountAlreadyExists{
        try{
            if(buscar(cod)==1){
                cuentas.seek(cuentas.length());
                cuentas.writeInt(cod);
                cuentas.writeUTF(nombre);
                cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
                cuentas.writeDouble(tipo.minisaldo);
                cuentas.writeUTF(tipo.name());
                System.out.println("!!!Cuenta creado con exito!!!");
            }else{
                throw new AccountAlreadyExists(cod);
            }
            
        }catch(IOException e){
            
        }
    }
    
    
    
}
