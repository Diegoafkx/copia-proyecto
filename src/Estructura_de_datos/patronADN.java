/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructura_de_datos;

/**
 * Representa un patrón de ADN (triplete) con su información asociada.
 * Almacena la secuencia del triplete, su frecuencia, las posiciones donde aparece,
 * y el aminoácido que codifica junto con sus abreviaturas.
 *
 * @author vyckhy y Diego Arreaza
 */

public class patronADN {
   
    private String triplete;       
    private int frecuencia;        
    private Lista posiciones;       
    private String aminoacido;      
    private String abreviatura3;   
    private String abreviatura1;  
    
    /**
     * Constructor principal para un nuevo patrón de ADN.
     * Inicializa la frecuencia en 1 y agrega la primera posición.
     *
     * @param triplete La secuencia de 3 nucleótidos del patrón.
     * @param posicionInicial La primera posición (índice) donde se encuentra este triplete.
     */
    public patronADN(String triplete, int posicionInicial) {
        this.triplete = triplete;
        this.frecuencia = 1; 
        this.posiciones = new Lista();
        Nodo aux = new Nodo(posicionInicial);
        this.posiciones.Insertar(aux); 
        this.convertirAaminoacido(triplete); 
    }

    // --- Getters (métodos para obtener los valores de los atributos) ---
    
    /**
     * Obtiene la secuencia de 3 nucleótidos del triplete de ADN.
     * @return El triplete de ADN como String.
     */
    public String getTriplete() {
        return triplete;
    }
    /**
     * Obtiene la frecuencia (número de ocurrencias) de este triplete en la cadena de ADN.
     * @return La frecuencia del triplete.
     */
    public int getFrecuencia() {
        return frecuencia;
    }
    /**
     * Obtiene la lista de posiciones (índices) donde este triplete aparece en la cadena de ADN.
     * @return La {@link Lista} que contiene las posiciones.
     */
    public Lista getPosiciones() {
        return posiciones;
    }
    /**
     * Obtiene el nombre completo del aminoácido codificado por este triplete.
     * @return El nombre del aminoácido (ej., "Fenilalanina", "Parada", "Desconocido").
     */
    public String getAminoacido() {
        return aminoacido;
    }
    /**
     * Obtiene la abreviatura de tres letras del aminoácido codificado.
     * @return La abreviatura de tres letras (ej., "Phe", "STOP", "N/A").
     */
    public String getAbreviatura3() {
        return abreviatura3;
    }
    /**
     * Obtiene la abreviatura de una letra del aminoácido codificado.
     * @return La abreviatura de una letra (ej., "F", "*", "?").
     */
    public String getAbreviatura1() {
        return abreviatura1;
    }
    /**
     * Establece la frecuencia (número de ocurrencias) de este triplete.
     * @param frecuencia La nueva frecuencia del triplete.
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    /**
     * Establece el nombre completo del aminoácido codificado.
     * @param aminoacido El nombre del aminoácido.
     */
    public void setAminoacido(String aminoacido) {
        this.aminoacido = aminoacido;
    }
    /**
     * Establece la abreviatura de tres letras del aminoácido codificado.
     * @param abreviatura3 La abreviatura de tres letras.
     */
    public void setAbreviatura3(String abreviatura3) {
        this.abreviatura3 = abreviatura3;
    }
    
    /**
     * Establece la abreviatura de una letra del aminoácido codificado.
     * @param abreviatura1 La abreviatura de una letra.
     */
    public void setAbreviatura1(String abreviatura1) {
        this.abreviatura1 = abreviatura1;
    }
    /**
     * Incrementa la frecuencia de este patrón en uno.
     * Se usa cuando se encuentra una nueva aparición de un patrón ya registrado.
     */
    public void incrementarFrecuencia() {
        this.frecuencia++;
    }
    
    /**
     * Agrega una nueva posición a la lista de ocurrencias de este patrón.
     * @param posicion La nueva posición (índice) donde se encontró el patrón.
     */
    public void agregarPosicion(int posicion) {
        Nodo aux = new Nodo(posicion);
        this.posiciones.Insertar(aux); 
    }
    
    /**
     * Método crucial para convertir un triplete de ADN en su aminoácido correspondiente
     * y rellenar los atributos 'aminoacido', 'abreviatura3' y 'abreviatura1'.
     *
     * @param tripleteADN El triplete de ADN (ej. "GTT").
     */
    private void convertirAaminoacido(String tripleteADN) {
        String codonARN = tripleteADN.toUpperCase().replace('T', 'U');
        switch (codonARN) {
            case "UUU", "UUC" -> {
                this.aminoacido = "Fenilalanina";
                this.abreviatura3 = "Phe";
                this.abreviatura1 = "F";
            }
            case "UUA", "UUG", "CUU", "CUC", "CUA", "CUG" -> {
                this.aminoacido = "Leucina";
                this.abreviatura3 = "Leu";
                this.abreviatura1 = "L";
            }
            case "AUU", "AUC", "AUA" -> {
                this.aminoacido = "Isoleucina";
                this.abreviatura3 = "Ile";
                this.abreviatura1 = "I";
            }
            case "AUG" -> {
                this.aminoacido = "Metionina";
                this.abreviatura3 = "Met";
                this.abreviatura1 = "M";
            }
            case "GUU", "GUC", "GUA", "GUG" -> {
                this.aminoacido = "Valina";
                this.abreviatura3 = "Val";
                this.abreviatura1 = "V";
            }
            case "UCU", "UCC", "UCA", "UCG", "AGU", "AGC" -> {
                this.aminoacido = "Serina";
                this.abreviatura3 = "Ser";
                this.abreviatura1 = "S";
            }
            case "CCU", "CCC", "CCA", "CCG" -> {
                this.aminoacido = "Prolina";
                this.abreviatura3 = "Pro";
                this.abreviatura1 = "P";
            }
            case "ACU", "ACC", "ACA", "ACG" -> {
                this.aminoacido = "Treonina";
                this.abreviatura3 = "Thr";
                this.abreviatura1 = "T";
            }
            case "GCU", "GCC", "GCA", "GCG" -> {
                this.aminoacido = "Alanina";
                this.abreviatura3 = "Ala";
                this.abreviatura1 = "A";
            }
            case "UAU", "UAC" -> {
                this.aminoacido = "Tirosina";
                this.abreviatura3 = "Tyr";
                this.abreviatura1 = "Y";
            }
            case "CAU", "CAC" -> {
                this.aminoacido = "Histidina";
                this.abreviatura3 = "His";
                this.abreviatura1 = "H";
            }
            case "CAA", "CAG" -> {
                this.aminoacido = "Glutamina";
                this.abreviatura3 = "Gln";
                this.abreviatura1 = "Q";
            }
            case "AAU", "AAC" -> {
                this.aminoacido = "Asparagina";
                this.abreviatura3 = "Asn";
                this.abreviatura1 = "N";
            }
            case "AAA", "AAG" -> {
                this.aminoacido = "Lisina";
                this.abreviatura3 = "Lys";
                this.abreviatura1 = "K";
            }
            case "GAU", "GAC" -> {
                this.aminoacido = "Ácido Aspártico";
                this.abreviatura3 = "Asp";
                this.abreviatura1 = "D";
            }
            case "GAA", "GAG" -> {
                this.aminoacido = "Ácido Glutámico";
                this.abreviatura3 = "Glu";
                this.abreviatura1 = "E";
            }
            case "UGU", "UGC" -> {
                this.aminoacido = "Cisteína";
                this.abreviatura3 = "Cys";
                this.abreviatura1 = "C";
            }
            case "UGG" -> {
                this.aminoacido = "Triptófano";
                this.abreviatura3 = "Trp";
                this.abreviatura1 = "W";
            }
            case "CGU", "CGC", "CGA", "CGG", "AGA", "AGG" -> {
                this.aminoacido = "Arginina";
                this.abreviatura3 = "Arg";
                this.abreviatura1 = "R";
            }
            case "GGU", "GGC", "GGA", "GGG" -> {
                this.aminoacido = "Glicina";
                this.abreviatura3 = "Gly";
                this.abreviatura1 = "G";
            }
            case "UAA", "UAG", "UGA" -> {
                this.aminoacido = "Parada";
                this.abreviatura3 = "STOP";
                this.abreviatura1 = "*";
            }
            default -> {
                this.aminoacido = "Desconocido";
                this.abreviatura3 = "N/A";
                this.abreviatura1 = "?";
            }
        }
        // Fenilalanina (Phenylalanine)
        // Leucina (Leucine)
        // Isoleucina (Isoleucine)
        // Metionina (Methionine)
        // Valina (Valine)
        // Serina (Serine)
        // Prolina (Proline)
        // Treonina (Threonine)
        // Alanina (Alanine)
        // Tirosina (Tyrosine)
        // Histidina (Histidine)
        // Glutamina (Glutamine)
        // Asparagina (Asparagine)
        // Lisina (Lysine)
        // Ácido Aspártico (Aspartic Acid)
        // Ácido Glutámico (Glutamic Acid)
        // Cisteína (Cysteine)
        // Triptófano (Tryptophan)
        // Arginina (Arginine)
        // Glicina (Glycine)
            }
    
    /**
 * Obtiene la representación en ARN de este triplete (cambia T por U)
 * @return El triplete convertido a ARN
 */
public String getTripleteARN() {
    return this.triplete.toUpperCase().replace('T', 'U');
}

/**
 * Obtiene la información completa del aminoácido en formato String
 * @return String con: Triplete ARN, Aminoácido, Abreviaturas
 */
public String getInfoAminoacido() {
    return String.format("ARN: %s | Aminoácido: %s (%s/%s)", 
        getTripleteARN(),
        this.aminoacido,
        this.abreviatura3,
        this.abreviatura1);
}
}