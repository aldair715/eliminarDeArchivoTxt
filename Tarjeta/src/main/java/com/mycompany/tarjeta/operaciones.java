/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tarjeta;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP 8va Gen
 */
public class operaciones {
    List<cliente> listaCliente;
    Scanner leer=new Scanner(System.in);
    public operaciones()
    {
        listaCliente=new ArrayList<>();
    }
    public void crearCliente()
    {
        cliente cliente=new cliente();
        TarjetaDebito tarjeta=new TarjetaDebito();
        System.out.println("Digite nombre");
        cliente.setNombre(leer.nextLine());
        System.out.println("Digite Paterno");
        cliente.setPaterno(leer.nextLine());
        System.out.println("Digite Materno");
        cliente.setMaterno(leer.nextLine());
        System.out.println("Digite Cedula de identidad");
        cliente.setCedula(leer.nextInt());
        //llenando dato de tarjeta
        leer.nextLine();
        System.out.println("Quiere colocar tarjeta????");
        String s=leer.nextLine();
        if(s.equalsIgnoreCase("s"))
        {
            System.out.println("Digite NroCuenta");
            tarjeta.setNroCuenta(leer.nextInt());
            System.out.println("Digite Nro Tarjeta");
            tarjeta.setNroTarjeta(leer.nextInt());
            System.out.println("Coloque el saldo inicial de la tarjeta");
            tarjeta.setSaldo(leer.nextDouble());
            tarjeta.setEstado("ACTIVO");
            cliente.setTarjeta(tarjeta);
            listaCliente.add(cliente);
            
            System.out.println("REGISTRO EFECTUADO");
        }
        
    }
    public void mostrarCliente()
    {
        if(listaCliente!=null)
        {
            System.out.println("CLIENTES ACTIVOS");
            for(cliente cli:listaCliente)
            {
                cli.mostrar();
            }
        }
    }
    //creacion de un archivo
    public void creacionArchivo()
    {
        Path path=Paths.get("C:\\programacionIII\\archivoCliente.txt");
        try{
            if(!Files.exists(path))
            {
                Files.createFile(path);
                System.out.println("Archivo cliente se creo exitosamente");
            }
            else{
                System.out.println("archivo ya existe");
            }
            
        }
        catch(Exception error)
        {
            
        }
    }
    //guardando la lista cliente dentro del archivo
    public void guardarObjeto()
    {
        String ruta="C:\\programacionIII\\archivoCliente.txt";
        try{
            FileOutputStream archivo=new FileOutputStream(ruta);
            ObjectOutputStream oos=new ObjectOutputStream(archivo);
            oos.writeObject(listaCliente);
            oos.close();
            archivo.close();
        }
        catch(FileNotFoundException ex)
        {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE,null,ex);
        } catch (IOException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //leer la lista clientes dentro del archivo
    public void leerClientes()
    {
        String ruta="C:\\programacionIII\\archivoCliente.txt";
        try{
            FileInputStream archivo=new FileInputStream(ruta);
            ObjectInputStream ois=new ObjectInputStream(archivo);
            if(ois!=null)
            {
                listaCliente=(List<cliente>)ois.readObject();
            }
            else
            {
                System.out.println("El objeto es nulo o esta vacio");
            }
            
        }
        catch(FileNotFoundException e)
        {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null,e);
        } catch (IOException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //cambiar el estado de la tarjeta
    public void cambiarEstado(int cedula)
    {
        int sw=0;
        if(listaCliente!=null)
        {
            for(cliente cli:listaCliente)
            {
                if(cli.getCedula()==cedula)
                {
                    sw=1;
                    cli.getTarjeta().setEstado("INACTIVO");
                    System.out.println("SE BLOQUEO LA TARJETA");
                }
            }
            if(sw==0)
            {
                System.out.println("Cliente no registrado");
            }
        }
    }
    //deposito en la tarjeta
    public void depositar(int cedula, double monto)
    {
        int sw=0;
        
        if(listaCliente!=null)
        {
            for(cliente cli:listaCliente)
            {
                if(cli.getCedula()==cedula)
                {
                    sw=1;
                    if(cli.getTarjeta().getEstado().equals("ACTIVO"))
                    {
                        double saldo=cli.getTarjeta().getSaldo();
                        saldo=saldo+monto;
                        cli.getTarjeta().setSaldo(saldo);
                        System.out.println("El saldo actual es : "+saldo);
                       
                    }
                    else
                    {
                        System.out.println("TARJETA BLOQUEADA");
                    }
                }
            }
            if(sw==0)
            {
                System.out.println("CLIENTE NO ENCONTRADO");
            }
        }
    }
    //retirar monto
    public void retirar(int cedula, double monto)
    {
        int sw=0;
        if(listaCliente!=null)
        {
            for(cliente cli:listaCliente)
            {
                if(cli.getCedula()==cedula)
                {
                    sw=1;
                    if(cli.getTarjeta().getEstado().equals("ACTIVO") && cli.getTarjeta().getSaldo()>=monto)
                    {
                        double saldo=cli.getTarjeta().getSaldo();
                        saldo=saldo-monto;
                        cli.getTarjeta().setSaldo(saldo);
                        System.out.println("El saldo actual es : "+saldo);
                    }
                    else
                    {
                        System.out.println("TARJETA BLOQUEADA o MONTO INSUFICIENTE");
                    }
                }
            }
            if(sw==0)
            {
                System.out.println("CLIENTE NO ENCONTRADO");
            }
        }
    }
}
