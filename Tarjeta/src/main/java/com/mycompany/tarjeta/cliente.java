/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tarjeta;


import java.io.Serializable;

/**
 *
 * @author HP 8va Gen
 */
public class cliente implements Serializable{
    static final long  serialVersionUID=43L;
    //atributos
    private String nombre;
    private String paterno;
    private String materno;
    private int cedula;
    //definiendo agregacion;
    private TarjetaDebito tarjeta;
    //metodo mostrar()
    public void mostrar()
    {
        System.out.println("Nombre:"+this.nombre);
        System.out.println("Paterno:"+this.paterno);
        System.out.println("Materno:"+this.materno);
        System.out.println("Cedula:"+this.cedula);
        System.out.println("-----------------DATOS DE LA TARJETA------------");
        if(tarjeta!=null)
        {
            tarjeta.mostrar();
        }
        else
        {
            System.out.println("Cliente no tiene tarjeta de debito agregada");
            
        }
        System.out.println("---------------------------------");
        
    }
    //getter and setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public TarjetaDebito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaDebito tarjeta) {
        this.tarjeta = tarjeta;
    }
    
}
