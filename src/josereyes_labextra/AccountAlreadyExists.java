/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package josereyes_labextra;

/**
 *
 * @author josec
 */
public class AccountAlreadyExists extends Exception{

    public AccountAlreadyExists(int codigo) {
        super("La cuenta: "+codigo+" ya existe en el sistema");
    }
    
    
}
