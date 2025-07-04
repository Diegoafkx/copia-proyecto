/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 *
 * @author Diego Arreaza y Vyckhy Sarmiento
 */
public class Lista {
    
     //atributos
    private Nodo pFirst;
    private int size;
    private Nodo pLast;
    
    //constructor
    public Lista() {
        this.pFirst = null;
        this.size = 0;
        this.pLast = null;
    }
    
    //getters y setters
    public Nodo getpFirst() {
        return pFirst;
    }

    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Nodo getpLast() {
        return pLast;
    }

    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }
  
    //Es vacio
    public boolean EsVacio(){
        return this.pFirst == null;
    }
    
    //vaciar lista
    public void VaciarLista(){
      this.pFirst = null;
      this.pLast = null;
      this.size = 0;   
    }
    
    //Primero de la lista
    public Nodo Primero(){
        return pFirst;
    }
    //
    public Nodo Ultimo(){
        return pLast;
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
    
    //insertar al inicio de la lista
    public Nodo InsertarInicio(Object dato){
        Nodo nuevo = new Nodo(dato);
        if (EsVacio()){//si la lista esta vacia
            pFirst = nuevo;
            pLast = nuevo;
        }else{
            nuevo.setpNext(pFirst);//el apuntador de nuevo nodo va a apuntar a lo mismo que apunta pfirst(el primero de la list)
            pFirst = nuevo;
        }size ++;
        return nuevo;
    }
    
    
    //inseratar al final de la lista
    public void Insertar(Nodo nodo){
        if (EsVacio()){
            pFirst = nodo;
            pLast = nodo; 
        }else{
            Nodo aux = pFirst;
            while (aux.getpNext() != null){
                aux = aux.getpNext();
            }aux.setpNext(nodo);
        }
        size++;
    }

    //Funcion para eliminar al inicio
    public void EliminarAlInicio(){
        if (!EsVacio()){
            pFirst = pFirst.getpNext();
            size--;
        }
    }
    
    //insertar un nodo con un dato en especifica posicion
    public void InsertarPorPosicion(Object dato, int posicion){
        Nodo nuevo = new Nodo(dato);
        if (posicion ==0){
           nuevo.setpNext(pFirst);
           pFirst = nuevo;
        }else{
            if (posicion == size-1){
                Nodo aux = pFirst;
                while(aux.getpNext() != null){
                    aux = aux.getpNext();
                }
                aux.setpNext(nuevo);
            }else{
                Nodo aux = pFirst;
                for(int i = 0; i < posicion-1; i++){
                    aux.getpNext();
                }
                Nodo siguiente = aux.getpNext();
                aux.setpNext(nuevo);
                nuevo.setpNext(siguiente);
            }
        }size++;
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
    
    //Consulta la posición de un elemento en la lista
    public int Posicion(String referencia){
        if (buscar(referencia)) {
            Nodo aux = pFirst;
            int cont = 0;
            while(referencia != aux.getData()){
                cont ++;
                aux = aux.getpNext();
            }
            return cont;
        } else {
            return -1;
        }
    }
   
    
 // Función Próximo - devuelve el siguiente nodo usando posición, hay que usar getdato al lado
    public Nodo Proximo(int posicion) {
        if (posicion >= 0 && posicion < size - 1) { // Validar que no sea la última posición
            Nodo actual = pFirst;
            for (int i = 0; i < posicion + 1; i++) { // ir a la posición siguiente
                actual = actual.getpNext();
            }
            return actual;
        } else {
            return null; // Si es la última posición o posición inválida
        }
    }

    // Insertar después de la posición especificada
    public void InsertarDespues(Object dato, int posicion) {
        if (posicion >= 0 && posicion < size) {
            Nodo pNew = new Nodo(dato);

            if (posicion == size - 1) { // Si es la última posición
                pLast.setpNext(pNew);
                pLast = pNew;
                size++;
            } else { // Si no es la última posición
                Nodo nodoActual = pFirst;
                for (int i = 0; i < posicion; i++) {
                    nodoActual = nodoActual.getpNext();
                }
                pNew.setpNext(nodoActual.getpNext());
                nodoActual.setpNext(pNew);
                size++;
            }
        }
    }
    
    //Metodo para editar el valor de un nodo en una posicion
    public void editarPorPosicion(int posicion , Object dato){

        if(posicion>=0 && posicion<size){
            if(posicion == 0){
                pFirst.setData(dato);
            }
            else{
                Nodo aux = pFirst;

                for (int i = 0; i < posicion; i++) {
                    aux = aux.getpNext();
                }
                aux.setData(dato);
            }
        }
    }
    
    //como su nombre lo indica
    public void EliminarPorPosicion(int posicion){

        if(posicion>=0 && posicion<size){
            if(posicion == 0){
                pFirst = pFirst.getpNext();
            }
            else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getpNext();
                }
                Nodo siguiente = aux.getpNext();
                aux.setpNext(siguiente.getpNext());
            }
            size--;
        }
    }
}
