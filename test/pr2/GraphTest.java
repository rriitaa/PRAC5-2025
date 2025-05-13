//GraphTest.java: contiene una prueba simple que verifica que el camino más corto funciona

package pr2;

import java.util.*;

public class GraphTest {
    @Test
    public void shortestPathFindsAPath() {
        System.out.println("\nTest shortestPathFindsAPath");
        System.out.println("----------------------------");

        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        List<Integer> expectedPath = Arrays.asList(1, 5, 4);
        assertEquals(expectedPath, g.shortestPath(1, 4));
    }
}
