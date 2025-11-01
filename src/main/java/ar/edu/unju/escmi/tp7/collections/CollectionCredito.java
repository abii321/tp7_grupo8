package ar.edu.unju.escmi.tp7.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.dominio.Credito;

public class CollectionCredito {

	public static List<Credito> creditos = new ArrayList<Credito>();

	public static void agregarCredito(Credito credito) {    
		try {
			creditos.add(credito);
		} catch (Exception e) {
			System.out.println("\nNO SE PUEDE GUARDAR EL CREDITO");
		}	
	}

	public static void buscarCreditoDeTarjetaEsp(long nroTc){
		boolean ban=false;
		for(int i=0; i<creditos.size(); i++){
			if(creditos.get(i).getTarjetaCredito().getNumero()==nroTc){
				creditos.get(i).mostarCredito(); //
				ban=true;
			}
		}
		if(!ban) System.out.println("El usuario no tiene creditos");
	}
}
