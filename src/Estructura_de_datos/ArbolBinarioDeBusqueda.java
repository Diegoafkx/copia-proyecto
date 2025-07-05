/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;
/**
 * Árbol binario de búsqueda para organizar patrones de ADN por frecuencia.
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
    /**
     * Obtiene la raíz del árbol binario.
    * 
    * @return Nodo raíz del árbol. Retorna null si el árbol está vacío.
    */
    public Nodo getRoot() {
        return root;
    }
    /**
    * Establece una nueva raíz para el árbol.
    * 
    * @param root Nuevo nodo raíz.
    * @throws IllegalArgumentException Si root es null y el árbol no está vacío.
    */
    public void setRoot(Nodo root) {
        this.root = root;
    }
/**
 * Obtiene el número de nodos en el árbol.
 * 
 * @return Tamaño actual del árbol (número de patrones almacenados).
 */
    public int getSize() {
        return size;
    }
/**
 * Establece el tamaño del árbol. 
 * (Usar con precaución, preferiblemente solo en inicializaciones)
 * 
 * @param size Nuevo tamaño a asignar.
 * @throws IllegalArgumentException Si size es negativo.
 */
    public void setSize(int size) {
        this.size = size;
    }
 /**
 * Verifica si el árbol está vacío.
 * 
 * @return true si no contiene nodos, false en caso contrario.
 */
     public boolean EsVacio(){
        return this.root == null;
    }
    /**
 * Vacía el árbol eliminando todas las referencias a nodos.
 * Postcondición: size = 0 y root = null.
 */
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
/**
 * Busca un patrón de ADN de forma recursiva en el subárbol con raíz en el nodo actual.
 * La búsqueda se realiza primero por frecuencia y luego por orden lexicográfico del triplete.
 * 
 * @param actual     Nodo actual en el recorrido recursivo (null si es fin de rama).
 * @param triplete   Secuencia de 3 nucleótidos a buscar (ej: "ATG").
 * @param frecuencia Frecuencia del patrón buscado.
 * @return           El objeto patronADN encontrado, o null si no existe.
 * 
 * @complexity O(h) donde h es la altura del subárbol.
 *             Mejor caso: O(1) (raíz), Peor caso: O(n) (árbol degenerado).
 * 
 * @see patronADN
 */
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
            Nodo aux =new Nodo(nodo.data);
            aux.Valor = nodo.Valor;
            resultado.Insertar(aux); 
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
    /**
 * Elimina un nodo con un patrón específico de forma recursiva.
 * Maneja tres casos: nodo sin hijos, con un hijo o con dos hijos.
 * 
 * @param actual     Nodo actual en el recorrido recursivo.
 * @param triplete   Triplete de ADN a eliminar (ej: "ATG").
 * @param frecuencia Frecuencia del patrón a eliminar.
 * @return           Nodo modificado después de la eliminación.
 * @throws IllegalStateException Si el árbol está vacío.
 */
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

/**
 * Realiza un recorrido in-order recursivo para generar una lista de cadenas formateadas
 * con información de patrones, diseñado específicamente para su visualización en GUI.
 * @param nodo      Nodo actual en la recursión (null indica fin de rama).
 * @param resultado Lista acumulativa donde se almacenan las cadenas formateadas.
 * @implNote El formato de cada entrada es: "Triplete: XXX | Frecuencia: Y"
 * @see Lista
 * @see Nodo
 */
private void inOrdenRecursivoGUI(Nodo nodo, Lista resultado) {
    if (nodo != null) {
        inOrdenRecursivoGUI(nodo.getIzq(), resultado); 
        
        patronADN patron = (patronADN) nodo.getData();
        String infoPatron = "Triplete: " + patron.getTriplete() + 
                           ", Frecuencia: " + patron.getFrecuencia();
        resultado.Insertar(new Nodo(infoPatron)); 
        
        inOrdenRecursivoGUI(nodo.getDer(), resultado); 
    }
}
    
}

