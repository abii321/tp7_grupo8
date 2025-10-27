package ar.edu.unju.escmi.tp7.utils;

import java.util.Scanner;

public class InputUtil {
    static Scanner sc = new Scanner(System.in);

    public static int inputInt( String msg){
        int aux =0; boolean valido = false;
        while(!valido){
            try{
                System.out.println(msg); aux=sc.nextInt();
                sc.nextLine();
                valido=true;
            } catch (Exception e){
                System.out.println("Debe ingresar un numero entero valido");
                sc.nextLine();
            }
        }
        return aux;
    }

    public static long inputLong(String msg){
        long aux =0; boolean valido = false;
        while(!valido){
            try{
                System.out.println(msg); aux=sc.nextInt();
                sc.nextLine();
                valido=true;
            } catch (Exception e){
                System.out.println("Debe ingresar un numero entero valido");
                sc.nextLine();
            }
        }
        return aux;
    }

}
