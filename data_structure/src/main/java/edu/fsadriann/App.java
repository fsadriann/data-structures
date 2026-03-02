package edu.fsadriann;

import edu.fsadriann.app.priorityqueue.PriorityQueue;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Prueba de PriorityQueue ===\n");

        // crear cola de prioridades
        PriorityQueue<Integer> cola = new PriorityQueue<>();

        // agregar elementos
        System.out.println("Agregando: 10, 20, 30, 40");
        cola.insert(10);
        cola.insert(20);
        cola.insert(30);
        cola.insert(40);

        System.out.println("Cola: " + cola);
        System.out.println("Tamaño: " + cola.size());
        System.out.println("Primero (peek): " + cola.peek());

        // insertar con prioridad alta (al inicio)
        System.out.println("\nAgregando 5 con prioridad alta (posicion 0)");
        cola.insert(5, 0);
        System.out.println("Cola: " + cola);

        // extraer elementos
        System.out.println("\nExtrayendo elementos:");
        while (!cola.isEmpty()) {
            System.out.println("  -> " + cola.extract());
        }

        System.out.println("\nCola vacia? " + cola.isEmpty());
    }
}

