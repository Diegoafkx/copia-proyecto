/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 * Implementación de lista enlazada simple para manejar colecciones de nodos.
 * Utilizada como cubeta en la tabla hash y para almacenar posiciones de patrones.
 * @author Vyckhy Sarmiento y Diego Arreaza
 */
public class Lista {
    
     //atributos
    private Nodo pFirst;
    private int size;
    
    //constructor
    public Lista() {
        this.pFirst = null;
        this.size = 0;
    }
    

    public int getSize() {
        return size;
    }
    
    public Nodo getpFirst(){
        return pFirst;
    }
    
    //Es vacio
    public boolean EsVacio(){
        return this.pFirst == null;
    }

    //Metodo para obtener el valor de un nodo en una determinada posición
    public Nodo AccederAlValor(int posicion){
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                return pFirst;
            }else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                return aux;
            }
        }
        return null;
    }

    //inseratar al final de la lista
    public void Insertar(Nodo nodo){
        if (EsVacio()){
            pFirst = nodo;
        }else{
            Nodo aux = pFirst;
            while (aux.getpNext() != null){
                aux = aux.getpNext();
            }aux.setpNext(nodo);
        }
        size++;
    }

    // Funcion para buscar un elemento en la lista, si lo encuentra es true
    public boolean buscar(String referencia){
        Nodo aux = pFirst;
        while(aux != null){
            if (referencia.equals(aux.clave) ){ 
                return true;
            }
            aux = aux.pNext;
        }
        return false;
    }

}
