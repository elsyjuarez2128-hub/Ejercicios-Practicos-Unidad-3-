package com.company.ArbolBinario;

/**
 *
 * @author Elsy Joselyn Godinez Juarez
 * Tema: ArbolBinario Unidad 3
 * GTID141
 */
class ArbolBinario {
    
private NodoArbol raiz;
public ArbolBinario() {
this.raiz = null;
}

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
    // Dentro de la clase ArbolBinario...
/**
* Método Público de Inserción: Punto de entrada para el usuario.
* Inicia la recursión desde la raíz.
*/
public void insertar(int valor) {
this.raiz = insertarRecursivo(this.raiz, valor);
}
/**
* Método Privado y Recursivo de Inserción.
* @param actual El nodo que se está examinando en la recursión.
* @param valor El valor a insertar.
* @return El nodo modificado o el nuevo nodo creado.
*/
private NodoArbol insertarRecursivo(NodoArbol actual, int valor) {
// Caso Base: Si el nodo actual es null, encontramos la posición, creamos el nuevo nodo y lo retornamos.

if (actual == null) {
return new NodoArbol(valor);
}
if (valor < actual.getDato()) { // Usamos getDato() por el encapsulamiento estricto
actual.hijoIzquierdo = insertarRecursivo(actual.hijoIzquierdo, valor);
} else if (valor > actual.getDato()) {
actual.hijoDerecho = insertarRecursivo(actual.hijoDerecho, valor);
}

// Si valor == actual.getDato(), se ignora (no permite duplicados).

return actual; // Retorna el nodo actual sin cambios si no fue caso base.
}
// Dentro de la clase ArbolBinario...

/**
* Método Público de Recorrido Inorden.
* Inicia la recursión desde la raíz y muestra el resultado.
*/
public void recorrerInorden() {
System.out.print("Recorrido Inorden: ");
recorrerInordenRecursivo(this.raiz);
System.out.println();
}
/**
 * 
* Método Privado y Recursivo de Recorrido Inorden (Izquierda -> Raíz ->
Derecha).
*/
private void recorrerInordenRecursivo(NodoArbol nodo) {
if (nodo != null) {
recorrerInordenRecursivo(nodo.hijoIzquierdo); // 1. Izquierda
System.out.print(nodo.getDato() + " "); // 2. Raíz (Imprimir)
recorrerInordenRecursivo(nodo.hijoDerecho); // 3. Derecha
}
}
}


