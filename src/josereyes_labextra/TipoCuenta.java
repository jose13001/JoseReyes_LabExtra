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
public enum TipoCuenta {
    NORMAL(0.02,500),PLANILLA(0,200),VIP(0.04,1000);
    public double tasaInteres;
    public double minisaldo;

    private TipoCuenta(double tasaInteres, double minisaldo) {
        this.tasaInteres = tasaInteres;
        this.minisaldo = minisaldo;
    }
    
    
}
