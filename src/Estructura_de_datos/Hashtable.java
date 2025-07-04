/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 *
 * @author Windows 10 Pro
 */
public class Hashtable {
    private Lista Claves_Valor;
    private int size;
    
    public Hashtable(){
        Claves_Valor = new Lista();
        size = 0;
    }
    
    public void put(String clave, patronADN valor){
        Nodo new_n = new Nodo(valor);
        new_n.clave = clave;
        Claves_Valor.Insertar(new_n);
        size++;
    }
    
    public int size(){
        return size;
    }
    
    public String[] get_claves(){
        String [] aux =new String[Claves_Valor.getSize()];
        for (int i = 0; i < Claves_Valor.getSize(); i++) {
            aux[i] = ( Claves_Valor.AccederAlValor(i).clave);
        }
        return aux;
    }

    public boolean contiene_la_clave(String aux) {
       return Claves_Valor.buscar(aux);
    }

    public patronADN get(String aux) {
        for (int i = 0; i < Claves_Valor.getSize(); i++) {
            if((Claves_Valor.AccederAlValor(i).clave).equals(aux)){
                return (patronADN) Claves_Valor.AccederAlValor(i).data;
            }
        }
        return null;
    }
}
