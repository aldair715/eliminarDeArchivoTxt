/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tarjeta;


import java.util.Scanner;

/**
 *
 * @author HP 8va Gen
 */
public class PrincipalClienteTarjeta {
    public static void main(String[] args) {
        boolean p=false;
        int N=0;
        operaciones oper=new operaciones();
        Scanner leer=new Scanner(System.in);
        while(p==false)
        {
            System.out.println("**************MENU*****************");
            System.out.println("1.crear archivo");
            System.out.println("2.Registrar cliente-tarjeta");
            System.out.println("3.Guardar los registros");
            System.out.println("4.Mostrar Datos");
            System.out.println("5.Bloquear Tarjeta");
            System.out.println("6.Depositar Monto");
            System.out.println("7.Retirar MONTO");
            System.out.println("8.Salir");
            
            System.out.println("*********************************");
            System.out.println("Digite opcion");
            N=leer.nextInt();
            switch(N)
            {
                case 1:
                    oper.creacionArchivo();
                break;
                case 2:
                    String res="S";
                    while(res.equalsIgnoreCase("S"))
                    {
                        
                        oper.crearCliente();
                        leer.nextLine();
                        System.out.println("Desea seguir registrando clientes S/N");
                        res=leer.nextLine();
                    }
                    
                break;
                case 3:
                    oper.guardarObjeto();
                break;
                case 4:
                    leer.nextLine();
                    oper.leerClientes();
                    oper.mostrarCliente();
                break;
                case 5:
                    oper.leerClientes();
                    System.out.println("Digite el numero de cedula del cliente");
                    int cedula=leer.nextInt();
                    oper.cambiarEstado(cedula);
         
                    oper.guardarObjeto();
                 break;
                case 6:
                    oper.leerClientes();
                    System.out.println("Digite el monto a mandar");
                    double monto=leer.nextDouble();
                    System.out.println("Digite el numero de cedula");
                    int ced=leer.nextInt();
                    oper.depositar(ced, monto);
                    oper.guardarObjeto();
                 break;
                 case 7:
                    oper.leerClientes();
                    System.out.println("Digite el monto a mandar");
                    double monto1=leer.nextDouble();
                    System.out.println("Digite el numero de cedula");
                    int ced1=leer.nextInt();
                    oper.retirar(ced1, monto1);
                    oper.guardarObjeto();
                 break;
                default:
                    p=true;
                break;
            }
        }
    }
}
