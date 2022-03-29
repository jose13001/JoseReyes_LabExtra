/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josereyes_labextra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    
    public boolean deposito(int cod,double monto)throws IOException{
        long x=buscar(cod);
        if(x!=1){
            cuentas.readUTF();
            cuentas.skipBytes(8);
            double SaldoActual=cuentas.readDouble();
            cuentas.seek(x);
            cuentas.readUTF();
            cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
            cuentas.writeDouble(SaldoActual+monto);
            return true;
        }else{
            System.out.println("La Cuenta: "+cod+" no esta registrada");
        }
        return false;
    }
    
    public boolean retiro(int cod,double monto)throws IOException{
       long x=buscar(cod);
        if(x!=1){
            cuentas.readUTF();
            cuentas.skipBytes(8);
            double SaldoActual=cuentas.readDouble();
            if(SaldoActual>monto){
            cuentas.seek(x);
            cuentas.readUTF();
            cuentas.writeLong(Calendar.getInstance().getTimeInMillis());
            cuentas.writeDouble(SaldoActual-monto);
            return true;
            }else{
                System.out.println("!!!!La Cuenta no tiene esa cantidad de dinero!!!");
            }
        }else{
            System.out.println("La Cuenta: "+cod+" no esta registrada");
        }
        return false;
    }
    
    public void registrarIntereses()throws IOException{
        cuentas.seek(0);
        while(cuentas.getFilePointer()<cuentas.length()){
            cuentas.skipBytes(4);
            cuentas.readUTF();
            cuentas.skipBytes(8);
            long saldo=cuentas.getFilePointer();
            double Saldo=cuentas.readDouble();
            TipoCuenta tipo=TipoCuenta.valueOf(cuentas.readUTF());
            double interes=tipo.tasaInteres*Saldo;
            cuentas.seek(saldo);
            cuentas.writeDouble(Saldo+interes);
            cuentas.readUTF();
        }
        
        
    }
    
    public void Import(String filename)throws IOException{
        FileWriter fw=new FileWriter(filename);
        cuentas.seek(0);
        while(cuentas.getFilePointer()<cuentas.length()){
            int codigo=cuentas.readInt();
            String nombre=cuentas.readUTF();
            cuentas.skipBytes(8);
            double saldo=cuentas.readDouble();
            String tipo=cuentas.readUTF();
            fw.write(codigo+"\n"+nombre+"\n"+"Lps."+saldo+"/n"+"Tipo:"+tipo+"\n");
            fw.flush();
        }
    }
    
}
