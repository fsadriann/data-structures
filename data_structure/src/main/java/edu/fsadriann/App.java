package edu.fsadriann;

import edu.fsadriann.app.priorityqueue.PriorityQueue;

/**
 * Ejemplos de uso de la Cola de Prioridades
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE COLA DE PRIORIDADES ===\n");

        // Ejemplo 1: Cola de prioridades con números
        ejemploConNumeros();
        
        // Ejemplo 2: Cola de prioridades con Strings
        ejemploConStrings();
        
        // Ejemplo 3: Cola de prioridades con objetos Person
        ejemploConPersonas();
        
        // Ejemplo 4: Inserción con prioridad específica
        ejemploConPrioridades();
    }

    /**
     * Ejemplo básico con números enteros
     */
    private static void ejemploConNumeros() {
        System.out.println("--- Ejemplo 1: Cola de Prioridades con Números ---");
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Insertar elementos (se agregan al final)
        pq.insert(10);
        pq.insert(20);
        pq.insert(30);
        pq.insert(40);
        
        System.out.println("Cola después de insertar: " + pq);
        System.out.println("Tamaño: " + pq.size());
        System.out.println("Elemento con mayor prioridad (peek): " + pq.peek());
        
        // Extraer elementos (de mayor a menor prioridad)
        System.out.println("\nExtrayendo elementos:");
        while (!pq.isEmpty()) {
            System.out.println("  Extraído: " + pq.extract() + " | Restantes: " + pq.size());
        }
        
        System.out.println("¿Cola vacía? " + pq.isEmpty());
        System.out.println();
    }

    /**
     * Ejemplo con cadenas de texto
     */
    private static void ejemploConStrings() {
        System.out.println("--- Ejemplo 2: Cola de Prioridades con Textos ---");
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        
        pq.insert("Tarea Baja Prioridad");
        pq.insert("Tarea Media Prioridad");
        pq.insert("Tarea Baja Prioridad 2");
        
        System.out.println("Cola de tareas: " + pq);
        System.out.println("Próxima tarea: " + pq.peek());
        
        // Verificar si contiene un elemento
        System.out.println("¿Contiene 'Tarea Media Prioridad'? " + 
                          pq.contains("Tarea Media Prioridad"));
        
        System.out.println();
    }

    /**
     * Ejemplo con objetos Person
     */
    private static void ejemploConPersonas() {
        System.out.println("--- Ejemplo 3: Cola de Prioridades con Personas ---");
        
        PriorityQueue<Person> pq = new PriorityQueue<>();
        
        pq.insert(new Person("Ana", 25));
        pq.insert(new Person("Carlos", 30));
        pq.insert(new Person("Beatriz", 28));
        
        System.out.println("Cola de personas:");
        pq.forEach(person -> {
            System.out.println("  - " + person);
            return null;
        });
        
        System.out.println("\nAtendiendo a la primera persona: " + pq.extract());
        System.out.println("Siguiente en la cola: " + pq.peek());
        
        System.out.println();
    }

    /**
     * Ejemplo de inserción con prioridades específicas
     */
    private static void ejemploConPrioridades() {
        System.out.println("--- Ejemplo 4: Inserción con Prioridades Específicas ---");
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        
        // Insertar al final (menor prioridad)
        pq.insert("Tarea Normal 1");
        pq.insert("Tarea Normal 2");
        pq.insert("Tarea Normal 3");
        
        System.out.println("Cola inicial: " + pq);
        
        // Insertar con ALTA prioridad (índice 0)
        pq.insert("🚨 URGENTE: Crisis del Sistema", 0);
        System.out.println("Después de agregar tarea urgente: " + pq);
        
        // Insertar con prioridad MEDIA (índice 2)
        pq.insert("⚠️ Importante: Revisión requerida", 2);
        System.out.println("Después de agregar tarea importante: " + pq);
        
        System.out.println("\nAtendiendo tareas por orden de prioridad:");
        int contador = 1;
        while (!pq.isEmpty()) {
            System.out.println("  " + contador + ". " + pq.extract());
            contador++;
        }
        
        System.out.println();
        
        // Ejemplo adicional: Invertir prioridades
        System.out.println("--- Invertir Prioridades ---");
        PriorityQueue<Integer> numeros = new PriorityQueue<>();
        numeros.insert(1);
        numeros.insert(2);
        numeros.insert(3);
        numeros.insert(4);
        
        System.out.println("Cola original: " + numeros);
        numeros.reverse();
        System.out.println("Cola invertida: " + numeros);
        System.out.println("Nueva máxima prioridad: " + numeros.peek());
        
        // Limpiar la cola
        numeros.clear();
        System.out.println("Después de limpiar, tamaño: " + numeros.size());
        
        System.out.println();
    }
}

