/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;


/**
 *
 * @author Diego Arreaza y Vyckhy Sarmiento
 */
public class ArbolBinarioDeBusqueda {
    //atributos
    private Nodo root;
    private int size;
    
    /**
     * Constructor de la clase ArbolBinarioDeBusqueda.
     * Inicializa un árbol vacío con la raíz nula y tamaño cero.
     */
    public ArbolBinarioDeBusqueda() {
        this.root = null;
        this.size = 0;
    }

    public Nodo getRoot() {
        return root;
    }

    public void setRoot(Nodo root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
     public boolean EsVacio(){
        return this.root == null;
    }
    
    public void Vaciar(){
        this.root = null;
    }
    
    /**
     * Método auxiliar recursivo para insertar un nodo en el árbol.
     * Los nodos se ordenan primero por frecuencia (menor a la izquierda, mayor a la derecha),
     *
     * @param actual El nodo actual que se está evaluando en la recursión.
     * @param nuevoNodo El nodo que se desea insertar.
     * @return El nodo actual después de la inserción, o el nuevo nodo si el lugar es nulo.
     */
    private Nodo insertarRecursivo(Nodo actual, Nodo nuevoNodo) {
        if (actual == null) {
            return nuevoNodo;
        }
        patronADN patronActual = (patronADN) actual.getData();
        patronADN patronNuevo = (patronADN) nuevoNodo.getData();
        if (patronNuevo.getFrecuencia() < patronActual.getFrecuencia()) {
            actual.setIzq(insertarRecursivo(actual.getIzq(), nuevoNodo));
        } else if (patronNuevo.getFrecuencia() > patronActual.getFrecuencia()) {
            actual.setDer(insertarRecursivo(actual.getDer(), nuevoNodo));
        } else {
            int comparacionTriplete = patronNuevo.getTriplete().compareTo(patronActual.getTriplete());
            if (comparacionTriplete < 0) { 
                actual.setIzq(insertarRecursivo(actual.getIzq(), nuevoNodo));
            } else if (comparacionTriplete > 0) { 
                actual.setDer(insertarRecursivo(actual.getDer(), nuevoNodo));
            } else {
                return actual; 
            }
        }
        return actual;
    }
    
    /**
     * Inserta un nuevo patrón de ADN en el árbol.
     * Si el patrón ya existe con la misma frecuencia y triplete, no se inserta duplicado.
     *
     * @param nuevoPatron El objeto patronADN a insertar.
     */
    public void insertar(patronADN nuevoPatron) {
        Nodo nuevoNodo = new Nodo(nuevoPatron);
        this.root = insertarRecursivo(this.root, nuevoNodo);
    }

    /**
     * Busca un patrón de ADN específico en el árbol por su triplete y frecuencia.
     *
     * @param triplete El triplete del patrón a buscar.
     * @param frecuencia La frecuencia del patrón a buscar.
     * @return El objeto patronADN si se encuentra, null en caso contrario.
     */
    public patronADN buscar(String triplete, int frecuencia) {
        return buscarRecursivo(root, triplete, frecuencia);
    }

    private patronADN buscarRecursivo(Nodo actual, String triplete, int frecuencia) {
        if (actual == null) {
            return null; 
        }
        int comparacionFrecuencia = frecuencia - ((patronADN) actual.getData()).getFrecuencia();
        if (comparacionFrecuencia < 0) {
            return buscarRecursivo(actual.getIzq(), triplete, frecuencia);
        } else if (comparacionFrecuencia > 0) {
            return buscarRecursivo(actual.getDer(), triplete, frecuencia);
        } else {
            int comparacionTriplete = triplete.compareTo(((patronADN) actual.getData()).getTriplete());
            if (comparacionTriplete < 0) {
                return buscarRecursivo(actual.getIzq(), triplete, frecuencia);
            } else if (comparacionTriplete > 0) {
                return buscarRecursivo(actual.getDer(), triplete, frecuencia);
            } else {
                return (patronADN) actual.getData(); 
            }
        }
    }

    /**
     * Encontrar el patrón más frecuente (el elemento con la mayor frecuencia, que debería ser el máximo en el árbol).
     * @return El objeto patronADN más frecuente, o null si el árbol está vacío.
     */
    public patronADN patronMasFrecuente() {
        if (root == null) return null;
        return (patronADN) encontrarMaximo(root).getData();
    }

    /**
     * Encontrar el patrón menos frecuente (el elemento con la menor frecuencia, que debería ser el mínimo en el árbol).
     * @return El objeto patronADN menos frecuente, o null si el árbol está vacío.
     */
    public patronADN patronMenosFrecuente() {
        if (root == null) return null;
        return (patronADN) encontrarMinimo(root).getData();
    }

    /**
     * Método auxiliar privado para encontrar el nodo con el valor mínimo (más a la izquierda).
     * @param nodo El nodo desde el cual comenzar la búsqueda.
     * @return El nodo con el valor mínimo.
     */
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }

    /**
     * Método auxiliar privado para encontrar el nodo con el valor máximo (más a la derecha).
     * @param nodo El nodo desde el cual comenzar la búsqueda.
     * @return El nodo con el valor máximo.
     */
    private Nodo encontrarMaximo(Nodo nodo) {
        while (nodo.getDer() != null) {
            nodo = nodo.getDer();
        }
        return nodo;
    }

    /**
     * Realiza un recorrido en orden del árbol y devuelve una lista de patrones de ADN ordenados por frecuencia.
     * @return Una Lista de objetos patronADN en orden ascendente de frecuencia.
     */
    public Lista recorridoInOrden() {
        Lista resultado = new Lista();
        recorridoInOrdenRecursivo(root, resultado);
        return resultado;
    }

    /**
     * Método auxiliar recursivo para el recorrido en orden.
     * @param nodo El nodo actual en el recorrido.
     * @param resultado La lista donde se añadirán los patrones.
     */
    private void recorridoInOrdenRecursivo(Nodo nodo, Lista resultado) {
        if (nodo != null) {
            recorridoInOrdenRecursivo(nodo.getIzq(), resultado);
            resultado.Insertar(nodo); 
            recorridoInOrdenRecursivo(nodo.getDer(), resultado);
        }
    }

    /**
     * Elimina un patrón de ADN del árbol.
     * Este método elimina el primer patrón que encuentra con la frecuencia y triplete dados.
     *
     * @param triplete El triplete del patrón a eliminar.
     * @param frecuencia La frecuencia del patrón a eliminar.
     */
    public void eliminar(String triplete, int frecuencia) {
        root = eliminarRecursivo(root, triplete, frecuencia);
    }

    private Nodo eliminarRecursivo(Nodo actual, String triplete, int frecuencia) {
        if (actual == null) {
            return null; 
        }

        int comparacionFrecuencia = frecuencia - ((patronADN) actual.getData()).getFrecuencia();
        
        if (comparacionFrecuencia < 0) { 
            actual.setIzq(eliminarRecursivo(actual.getIzq(), triplete, frecuencia));
            return actual;
        } else if (comparacionFrecuencia > 0) { 
            actual.setDer(eliminarRecursivo(actual.getDer(), triplete, frecuencia));
            return actual;
        } else { 
            int comparacionTriplete = triplete.compareTo(((patronADN) actual.getData()).getTriplete());

            if (comparacionTriplete < 0) { 
                actual.setIzq(eliminarRecursivo(actual.getIzq(), triplete, frecuencia));
                return actual;
            } else if (comparacionTriplete > 0) { 
                actual.setDer(eliminarRecursivo(actual.getDer(), triplete, frecuencia));
                return actual;
            } else { 
                if (actual.getIzq() == null) {
                    size--;
                    return actual.getDer();
                } else if (actual.getDer() == null) {
                    size--;
                    return actual.getIzq();
                } else {
                    Nodo sucesor = encontrarMinimo(actual.getDer());
                    actual.setData(sucesor.getData());
                    actual.setDer(eliminarRecursivo(actual.getDer(), ((patronADN) sucesor.getData()).getTriplete(), ((patronADN) sucesor.getData()).getFrecuencia()));
                    return actual;
                }
            }
        }
    }
    
    /**
     * Realiza un recorrido in-orden del árbol y recolecta la información de los patrones ADN.
     * Los patrones se presentan en formato: "Triplete: [triplete], Frecuencia: [frecuencia], Posiciones: [posiciones]"
     * @return Una cadena de texto con la información de todos los patrones ADN ordenados por frecuencia.
     */
    public String obtenerPatronesEnOrden() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    /**
     * Método auxiliar recursivo para el recorrido in-orden.
     * @param nodo El nodo actual en el recorrido.
     * @param sb El StringBuilder para acumular la información.
     */
    private void inOrderTraversal(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            inOrderTraversal(nodo.getIzq(), sb);
            patronADN patron = (patronADN) nodo.getData();
            sb.append("Triplete: ").append(patron.getTriplete())
              .append(", Frecuencia: ").append(patron.getFrecuencia())
              .append(", Posiciones: [");
            Lista posiciones = patron.getPosiciones();
            Nodo currentPos = posiciones.getpFirst();
            while (currentPos != null) {
                sb.append(currentPos.getData());
                if (currentPos.getpNext() != null) {
                    sb.append(", ");
                }
                currentPos = currentPos.getpNext();
            }
            sb.append("]\n");
            
            inOrderTraversal(nodo.getDer(), sb);
        }
    }
    /**
 * Realiza un recorrido in-orden y devuelve una lista de Strings con la información de los patrones.
 * @return Lista de Strings en formato: "Triplete: [triplete], Frecuencia: [frecuencia]".
 */
public Lista recorridoInOrdenParaGUI() {
    Lista resultado = new Lista();
    inOrdenRecursivoGUI(this.root, resultado);
    return resultado;
}

// Método auxiliar recursivo
private void inOrdenRecursivoGUI(Nodo nodo, Lista resultado) {
    if (nodo != null) {
        inOrdenRecursivoGUI(nodo.getIzq(), resultado); // Recorre subárbol izquierdo
        
        patronADN patron = (patronADN) nodo.getData();
        String infoPatron = "Triplete: " + patron.getTriplete() + 
                           ", Frecuencia: " + patron.getFrecuencia();
        resultado.Insertar(new Nodo(infoPatron)); // Agrega el patrón formateado a la lista
        
        inOrdenRecursivoGUI(nodo.getDer(), resultado); // Recorre subárbol derecho
    }
}
    
}

