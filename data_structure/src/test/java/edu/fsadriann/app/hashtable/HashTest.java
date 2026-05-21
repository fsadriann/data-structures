package edu.fsadriann.app.hashtable;

import edu.fsadriann.app.array.Array;
import edu.fsadriann.app.linkedlist.singly.singly.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private Hash<String> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new Hash<>(10);
    }

    // ─── Constructor ───────────────────────────────────────────────────────────

    @Test
    void constructorConCapacidad_creaTablaVacia() {
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());
    }

    @Test
    void constructorConArray_creaTablaDesdeArreglo() {
        Array<LinkedList<String>> tabla = new Array<>(5);
        for (int i = 0; i < 5; i++) tabla.add(new LinkedList<>());
        Hash<String> ht = new Hash<>(tabla);
        assertTrue(ht.isEmpty());
    }

    // ─── hash ──────────────────────────────────────────────────────────────────

    @Test
    void hash_elementoNull_retornaMenosUno() {
        assertEquals(-1, hashTable.hash(null));
    }

    @Test
    void hash_elementoValido_retornaIndicePositivo() {
        int index = hashTable.hash("hola");
        assertTrue(index >= 0);
    }

    @Test
    void hash_mismoElemento_siempreRetornaMismoIndice() {
        assertEquals(hashTable.hash("java"), hashTable.hash("java"));
    }

    // ─── insert ────────────────────────────────────────────────────────────────

    @Test
    void insert_elementoNuevo_retornaTrue() {
        assertTrue(hashTable.insert("uno"));
    }

    @Test
    void insert_elementoDuplicado_retornaFalse() {
        hashTable.insert("uno");
        assertFalse(hashTable.insert("uno"));
    }

    @Test
    void insert_elementoNull_retornaFalse() {
        assertFalse(hashTable.insert(null));
    }

    @Test
    void insert_variosElementos_incrementaSize() {
        hashTable.insert("uno");
        hashTable.insert("dos");
        hashTable.insert("tres");
        assertEquals(3, hashTable.size());
    }

    // ─── search ────────────────────────────────────────────────────────────────

    @Test
    void search_elementoExistente_retornaTrue() {
        hashTable.insert("uno");
        assertTrue(hashTable.search("uno"));
    }

    @Test
    void search_elementoInexistente_retornaFalse() {
        assertFalse(hashTable.search("fantasma"));
    }

    @Test
    void search_elementoNull_retornaFalse() {
        assertFalse(hashTable.search(null));
    }

    // ─── get ───────────────────────────────────────────────────────────────────

    @Test
    void get_elementoExistente_retornaElemento() {
        hashTable.insert("uno");
        assertEquals("uno", hashTable.get("uno"));
    }

    @Test
    void get_elementoInexistente_retornaNull() {
        assertNull(hashTable.get("fantasma"));
    }

    @Test
    void get_elementoNull_retornaNull() {
        assertNull(hashTable.get(null));
    }

    // ─── delete ────────────────────────────────────────────────────────────────

    @Test
    void delete_elementoExistente_retornaTrue() {
        hashTable.insert("uno");
        assertTrue(hashTable.delete("uno"));
    }

    @Test
    void delete_elementoExistente_decrementaSize() {
        hashTable.insert("uno");
        hashTable.insert("dos");
        hashTable.delete("uno");
        assertEquals(1, hashTable.size());
    }

    @Test
    void delete_elementoInexistente_retornaFalse() {
        assertFalse(hashTable.delete("fantasma"));
    }

    @Test
    void delete_elementoNull_retornaFalse() {
        assertFalse(hashTable.delete(null));
    }

    @Test
    void delete_elementoEliminado_noSeEncuentraConSearch() {
        hashTable.insert("uno");
        hashTable.delete("uno");
        assertFalse(hashTable.search("uno"));
    }

    // ─── isEmpty ───────────────────────────────────────────────────────────────

    @Test
    void isEmpty_tablaRecienCreada_retornaTrue() {
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void isEmpty_tablaConElementos_retornaFalse() {
        hashTable.insert("uno");
        assertFalse(hashTable.isEmpty());
    }

    @Test
    void isEmpty_despuesDeEliminarTodos_retornaTrue() {
        hashTable.insert("uno");
        hashTable.delete("uno");
        assertTrue(hashTable.isEmpty());
    }

    // ─── size ──────────────────────────────────────────────────────────────────

    @Test
    void size_tablaVacia_retornaCero() {
        assertEquals(0, hashTable.size());
    }

    @Test
    void size_despuesDeInsertarYEliminar_esConsistente() {
        hashTable.insert("uno");
        hashTable.insert("dos");
        hashTable.delete("uno");
        assertEquals(1, hashTable.size());
    }

    // ─── clear ─────────────────────────────────────────────────────────────────

    @Test
    void clear_tablaConElementos_quedaVacia() {
        hashTable.insert("uno");
        hashTable.insert("dos");
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void clear_tablaConElementos_sizeEsCero() {
        hashTable.insert("uno");
        hashTable.insert("dos");
        hashTable.clear();
        assertEquals(0, hashTable.size());
    }

    @Test
    void clear_despuesDeClear_sePuedeInsertarDeNuevo() {
        hashTable.insert("uno");
        hashTable.clear();
        assertTrue(hashTable.insert("uno"));
    }

    // ─── colisiones ────────────────────────────────────────────────────────────

    @Test
    void insert_elementosConColision_ambosSeInsertan() {
        // Forzar colisión insertando muchos elementos
        for (int i = 0; i < 9; i++) {
            hashTable.insert("elemento" + i);
        }
        assertEquals(9, hashTable.size());
    }

    @Test
    void search_elementosConColision_encuentraCadaUno() {
        for (int i = 0; i < 9; i++) {
            hashTable.insert("elemento" + i);
        }
        for (int i = 0; i < 9; i++) {
            assertTrue(hashTable.search("elemento" + i));
        }
    }
}