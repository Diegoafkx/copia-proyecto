/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 * Implementación personalizada de una Tabla Hash para almacenar objetos {@code patronADN}.
 * Utiliza encadenamiento separado (separate chaining) para el manejo de colisiones,
 * donde cada 'cubeta' de la tabla es una {@link Lista}.
 *
 * @author Diego Arreaza y Vyckhy Sarmiento
 */
public class Hashtable {
    private Lista[] tabla;
    private int size;      
    private int capacidad; 

    /**
     * Constructor de la clase Hashtable.
     * Inicializa la tabla hash con una capacidad predefinida y crea una nueva {@link Lista}
     * para cada una de las cubetas.
     *
     * @param capacidadInicial El tamaño inicial del arreglo de la tabla hash.
     * @throws IllegalArgumentException si la capacidad inicial es menor o igual a cero.
     */
    public Hashtable(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            throw new IllegalArgumentException("La capacidad inicial debe ser un número positivo.");
        }
        this.capacidad = capacidadInicial;
        this.tabla = new Lista[capacidadInicial]; 
        for (int i = 0; i < capacidadInicial; i++) {
            tabla[i] = new Lista(); 
        }
        this.size = 0;
    }

    /**
     * Calcula el valor hash para una clave dada, determinando la cubeta
     * donde se almacenará el elemento.
     * Esta es una función hash simple basada en la suma de los valores ASCII de los caracteres
     * de la clave, y luego tomando el módulo de la capacidad para asegurar que el índice
     * esté dentro de los límites del arreglo. Se usa {@code Math.abs()} para asegurar un índice positivo.
     *
     * @param clave La clave (String del triplete de ADN) para la cual calcular el hash.
     * @return El índice de la cubeta en la tabla hash.
     */
    private int hash(String clave) {
        int hashValue = 0;
        for (int i = 0; i < clave.length(); i++) {
            hashValue = (hashValue + clave.charAt(i)); 
        }
        return Math.abs(hashValue % capacidad);
    }

    /**
     * Inserta un {@code patronADN} en la tabla hash utilizando su triplete como clave.
     * Si la clave ya existe (es decir, el triplete de ADN ya está en la tabla),
     * actualiza la frecuencia del patrón existente y añade la nueva posición
     * de ocurrencia. Si la clave es nueva, añade el patrón a la tabla como un nuevo elemento.
     *
     * @param clave La clave (triplete de ADN, ej. "ATG") a insertar o actualizar.
     * @param valor El objeto {@code patronADN} asociado a la clave. Se asume que este
     * objeto contiene la nueva posición a añadir si el triplete ya existe,
     * o es el patrón completo si es nuevo.
     */
    public void put(String clave, patronADN valor) {
    int indice = hash(clave);
    Lista cubeta = tabla[indice];
    Nodo current = cubeta.getpFirst();
    while (current != null) {
        if (current.getData() instanceof patronADN) {
            patronADN p = (patronADN) current.getData();
            if (p.getTriplete().equals(clave)) {
                p.incrementarFrecuencia();
                if (valor.getPosiciones() != null && valor.getPosiciones().getpFirst() != null) {
                    p.agregarPosicion((int) valor.getPosiciones().getpFirst().getData());
                }
                return; 
            }
        }
        current = current.getpNext();
    }
    cubeta.Insertar(new Nodo(valor));
    size++; 
    }

    /**
     * Recupera un objeto {@code patronADN} de la tabla hash utilizando su clave (triplete de ADN).
     *
     * @param clave La clave (triplete de ADN, ej. "ATG") a buscar.
     * @return El objeto {@code patronADN} asociado a la clave, o {@code null} si no se encuentra.
     */
    public patronADN get(String clave) {
        int indice = hash(clave);
        Lista cubeta = tabla[indice];
        
        Nodo current = cubeta.getpFirst();
        while (current != null) {
            if (current.getData() instanceof patronADN) {
                patronADN p = (patronADN) current.getData();
                if (p.getTriplete().equals(clave)) {
                    return p; 
                }
            }
            current = current.getpNext();
        }
        return null; 
    }

    /**
     * Retorna el número de elementos únicos (patrones ADN) actualmente almacenados en la tabla hash.
     * Este tamaño representa la cantidad de tripletes de ADN distintos que han sido identificados.
     *
     * @return El número de elementos únicos en la tabla.
     */
    public int size() {
        return size;
    }

    /**
     * Verifica si la tabla hash contiene una clave específica (un triplete de ADN).
     *
     * @param clave La clave (triplete de ADN, ej. "ATG") a verificar.
     * @return {@code true} si la clave está en la tabla, {@code false} en caso contrario.
     */
    public boolean contiene_la_clave(String clave) {
       return get(clave) != null; 
    }
    
    /**
     * Genera un reporte detallado de las colisiones detectadas en la tabla hash.
     * Una colisión ocurre cuando dos o más claves distintas se mapean a la misma cubeta.
     * Este reporte muestra cuántos elementos hay en cada cubeta y calcula el total de colisiones
     * (elementos adicionales después del primero en una cubeta) y el número de cubetas afectadas.
     *
     * @return Una cadena de texto con el reporte de colisiones.
     */
    public String generarReporteColisiones() {
        StringBuilder sb = new StringBuilder(); 
        int totalColisiones = 0; 
        int cubetasConColisiones = 0; 
        sb.append("--- Reporte de Colisiones de la Tabla Hash ---\n");
        sb.append("Capacidad total de la tabla (número de cubetas): ").append(capacidad).append("\n");
        sb.append("Número total de patrones ADN únicos almacenados: ").append(size).append("\n\n");

        for (int i = 0; i < capacidad; i++) {
            Lista cubeta = tabla[i];
            int elementosEnCubeta = cubeta.getSize(); 
            if (elementosEnCubeta > 1) {
                totalColisiones += (elementosEnCubeta - 1);
                cubetasConColisiones++;
                sb.append("Cubeta ").append(i).append(": ").append(elementosEnCubeta).append(" elementos (").append(elementosEnCubeta - 1).append(" colisiones)\n");
                Nodo current = cubeta.getpFirst();
                while(current != null){
                    if(current.getData() instanceof patronADN){
                        patronADN p = (patronADN)current.getData();
                        sb.append("    - Triplete: ").append(p.getTriplete()).append(", Frecuencia: ").append(p.getFrecuencia()).append("\n");
                    }
                    current = current.getpNext();
                }
            } else if (elementosEnCubeta == 1) {
                sb.append("Cubeta ").append(i).append(": 1 elemento\n");
            } else {
          
            }
        }
        sb.append("\nTotal de colisiones registradas (elementos adicionales en cubetas): ").append(totalColisiones).append("\n");
        sb.append("Número de cubetas que experimentaron colisiones: ").append(cubetasConColisiones).append("\n");
        sb.append("--- Fin del Reporte ---");
        return sb.toString();
    }

    /**
     * Obtiene una colección de todos los objetos {@code patronADN} almacenados en la tabla hash
     * en forma de un arreglo. Este método es útil para iterar sobre todos los patrones,
     * por ejemplo, para poblar un árbol binario de búsqueda o para visualizar todos los datos
     * en una lista en la interfaz gráfica.
     *
     * @return Un arreglo de {@code patronADN} que contiene todos los objetos {@code patronADN} de la tabla.
     * Retorna un arreglo vacío (de tamaño 0) si la tabla hash no contiene elementos.
     */
    public patronADN[] getAllPatrones() {
        patronADN[] allPatrones = new patronADN[size]; 
        int count = 0; 
        for (int i = 0; i < capacidad; i++) {
            Lista cubeta = tabla[i];
            Nodo current = cubeta.getpFirst(); 
            while (current != null) {
                if (current.getData() instanceof patronADN) {
                    allPatrones[count++] = (patronADN) current.getData();
                }
                current = current.getpNext();
            }
        }
        return allPatrones;
    }
}
