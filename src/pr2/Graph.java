//Graph.java: contiene la clase Graph con los métodos básicos 
//y el algoritmo de camino más corto (usaremos BFS)

package pr2;

import java.util.*;

public class Graph<V> {
    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /******************************************************************
    * Añade el vértice `v` al grafo.
    *
    * @param v vértice a añadir.
    * @return `true` si no estaba anteriormente y `false` en caso
    * contrario.
    ******************************************************************/
    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        } else {
            adjacencyList.put(v, new HashSet<>());
            return true;
        }
    }

    /******************************************************************
    * Añade un arco entre los vértices `v1` y `v2` al grafo. En
    * caso de que no exista alguno de los vértices, lo añade
    * también.
    *
    * @param v1 el origen del arco.
    * @param v2 el destino del arco.
    * @return `true` si no existía el arco y `false` en caso contrario.
    ******************************************************************/
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        if (adjacencyList.get(v1).contains(v2)) {
            return false;
        } else {
            adjacencyList.get(v1).add(v2);
            return true;
        }
    }

    /******************************************************************
    * Obtiene el conjunto de vértices adyacentes a `v`.
    *
    * @param v vértice del que se obtienen los adyacentes.
    * @return conjunto de vértices adyacentes.
    ******************************************************************/
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (!adjacencyList.containsKey(v)) {
            throw new Exception("El vértice no existe");
        }
        return adjacencyList.get(v);
    }

    /******************************************************************
    * Comprueba si el grafo contiene el vértice dado.
    *
    * @param v vértice para el que se realiza la comprobación.
    * @return `true` si `v` es un vértice del grafo.
    ******************************************************************/
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /******************************************************************
    * Método `toString()` reescrito para la clase `Grafo.java`.
    * @return una cadena de caracteres con la lista de
    * adyacencia.
    ******************************************************************/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : adjacencyList.keySet()) {
            sb.append(v.toString()).append(": ");
            for (V adj : adjacencyList.get(v)) {
                sb.append(adj.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
    * Obtiene, en caso de que exista, el camino más corto entre
    * `v1` y `v2`. En caso contrario, devuelve `null`.
    *
    * @param v1 el vértice origen.
    * @param v2 el vértice destino.
    * @return lista con la secuencia de vértices del camino más corto
    * entre `v1` y `v2`
    */
    public List<V> shortestPath(V v1, V v2) {
        if (!adjacencyList.containsKey(v1) || !adjacencyList.containsKey(v2)) {
            return null;
        }

        Queue<List<V>> queue = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        List<V> startPath = new ArrayList<>();
        startPath.add(v1);
        queue.add(startPath);
        visited.add(v1);

        while (!queue.isEmpty()) {
            List<V> path = queue.poll();
            V last = path.get(path.size() - 1);

            if (last.equals(v2)) {
                return path;
            }

            for (V neighbor : adjacencyList.getOrDefault(last, new HashSet<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<V> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }
}
