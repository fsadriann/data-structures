package edu.fsadriann;

import edu.fsadriann.model.array.Array;

public class TestArray {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        
        // agregar elementos
        arr.add(10);
        arr.add(20);
        arr.add(30);
        System.out.println("agregados: 10, 20, 30 | tamaño: " + arr.size());
        
        // obtener elementos
        System.out.println("elementos: " + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2));
        
        // buscar
        System.out.println("indice de 20: " + arr.indexOf(20));
        
        // invertir
        arr.reverse();
        System.out.println("después de invertir: " + arr.get(0) + ", " + arr.get(1) + ", " + arr.get(2));
        
        // eliminar
        arr.remove(1);
        System.out.println("después de eliminar en posición 1: " + arr.get(0) + ", " + arr.get(1));
        
        // modificar
        arr.set(0, 100);
        System.out.println("después de cambiar posición 0: " + arr.get(0));
        
        // limpiar
        arr.clear();
        System.out.println("después de limpiar - tamaño: " + arr.size() + " | vacío: " + arr.isEmpty());
    }
}
