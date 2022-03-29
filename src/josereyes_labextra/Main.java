/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josereyes_labextra;

import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author josec
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner lea=new Scanner(System.in).useDelimiter("\n");
        ManageBank mg=new ManageBank();
        int opc=0;
        System.out.println("***Bienvenido al Banco***");
        do{
            System.out.println("***Menu***");
            System.out.println("1.Agregar cuenta");
            System.out.println("2.Depositar");
            System.out.println("3.Retirar");
            System.out.println("4.Registrar Intereses");
            System.out.println("5.Importar");
            System.out.println("6.Salir");
            System.out.print("Ingrese una opcion: ");
            opc=lea.nextInt();
            switch(opc){
                    case 1:
                        System.out.println("***Agregar Cuenta***");
                        System.out.print("Ingrese un código: ");
                        int cod = lea.nextInt();
                        System.out.print("Ingrese un nombre: ");
                        String nombre = lea.next();
                        System.out.println("Tipo:");
                        System.out.println("0.NORMAL");
                        System.out.println("1.PLANILLA");
                        System.out.println("2.VIP");
                        System.out.print("Ingrese tipo: ");
                        TipoCuenta tipo = TipoCuenta.values()[lea.nextInt()];
                        try{
                            mg.addCuenta(cod, nombre, tipo);
                        }catch(AccountAlreadyExists ex){
                            System.out.println(ex.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("***Depositar***");
                        System.out.print("Ingrese su código: ");
                        cod = lea.nextInt();
                        System.out.print("Ingrese un monto: ");
                        Double monto = lea.nextDouble();
                        if(mg.deposito(cod, monto)){
                            System.out.println("Se depositaron Lps."+monto+" a la cuenta "+cod);
                        }else{
                            System.out.println("!!!No se pudo realizar el depósito :( !!!");
                        }
                        break;
                    case 3:
                        System.out.println("***Retirar***");
                        System.out.print("Ingrese su código: ");
                        cod = lea.nextInt();
                        System.out.print("Ingrese un monto: ");
                        monto = lea.nextDouble();
                        if(mg.retiro(cod, monto)){
                            System.out.println("Se retiraron Lps."+monto+" de la cuenta "+cod);
                        }else{
                            System.out.println("!!!No se pudo efectuar el retiro :( !!!");
                        }
                        break;
                    case 4:
                        System.out.println("***Registrar Intereses***");
                        mg.registrarIntereses();
                        System.out.println("Los intereses fueron registradoes exitosamente");
                        break;
                    case 5:
                        System.out.println("***Importar***");
                        System.out.print("Ingrese dirección: ");
                        String filename = lea.next();
                        mg.Import(filename);
                        break;
                    case 6:
                        System.out.println("***Saliendo del sistema****");
                        break;
                }
            
        }while(opc!=6);
        
    }
    
}
