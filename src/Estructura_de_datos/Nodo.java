/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;
/**
 * Implementación de un nodo genérico para estructuras de datos dinámicas.
 * Utilizado como unidad básica en árboles binarios y listas enlazadas.
 * @param <T> Tipo de dato a almacenar en el nodo 
 * @author Diego Arreaza y Vyckhy Sarmiento
 */
public class Nodo <T> {

    protected Nodo pNext;
    protected T data;
    protected Nodo izq;
    protected Nodo der;
    protected Nodo Valor;
    protected String clave;
    
    /**
     * Constructor principal que inicializa un nodo con datos.
     * @param data Información a almacenar en el nodo (no puede ser null)
     * @throws IllegalArgumentException Si data es null
     */
    public Nodo(T data) {
        this.data = data;
        this.izq = null;
        this.der = null;
        this.pNext =null;
    }
    
   /**
     * Obtiene el dato almacenado en el nodo.
     * @return El objeto de tipo T almacenado
     */
    public T getData() {
        return data;
    }
    /**
     * Establece/modifica el dato del nodo.
     * @param data Nuevo dato a almacenar (no null)
     */
    public void setData(T data) {
        this.data = data;
    }
    /**
     * Obtiene el hijo izquierdo (para árboles).
     * @return Nodo izquierdo o null si no existe
     */
    public Nodo getIzq() {
        return izq;
    }
   /**
     * Establece el hijo izquierdo.
     * @param izq Nuevo nodo izquierdo
     */
    public void setIzq(Nodo izq) {
        this.izq = izq;
    }
    /**
     * Obtiene el hijo derecho (para árboles).
     * @return Nodo derecho o null si no existe
     */
    public Nodo getDer() {
        return der;
    }
   /**
     * Establece el hijo derecho.
     * @param der Nuevo nodo derecho
     */
    public void setDer(Nodo der) {
        this.der = der;
    }
    /**
     * Obtiene la referencia al siguiente nodo (para listas).
     * @return Nodo siguiente o null si es el último
     */
    public Nodo getpNext() {
        return pNext;
    }
   /**
     * Establece la referencia al siguiente nodo.
     * @param pNext Nuevo nodo siguiente
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
    
}
