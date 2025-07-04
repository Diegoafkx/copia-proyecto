/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 *
 * @author Diego Arreaza y Vyckhy Sarmiento
 * @param <T>
 */
public class Nodo <T> {
  
    //atributos del Nodo
    
    protected Nodo pNext;
    protected T data;
    protected Nodo izq;
    protected Nodo der;
    protected Nodo Valor;
    protected String clave;
    
    //constructor

    /**
     *
     * @param data
     */
    public Nodo(T data) {
        this.data = data;
        this.izq = null;
        this.der = null;
        this.pNext =null;
    }
    
    //gettters y setters para el arbol

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public Nodo getpNext() {
        return pNext;
    }

    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
    
}
