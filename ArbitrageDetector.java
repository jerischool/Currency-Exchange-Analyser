
import java.util.*;

// this class is responsible for detecting arbitrage opportunities in a currency exchange system
// it uses the bellman-ford algorithm to find negative weight cycles in a logarithmic graph
public class ArbitrageDetector {

    private CurrencyExchangeGraph graph;

    // constructor to initialize the arbitrage detector with a currency exchange graph
    public ArbitrageDetector(CurrencyExchangeGraph graph) {
        this.graph = graph;
    }

    // method to detect arbitrage opportunities in the currency exchange graph
    // returns true if an arbitrage is detected, otherwise false
    public boolean detectArbitrage() {
        // get the number of currencies and the logarithmic graph representation of the exchange rates
        int n = graph.getNumberOfCurrencies();
        double[][] logGraph = graph.convertToLogGraph();

        // initialize the distance array with positive infinity and set the source to zero
        double[] distances = new double[n];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[0] = 0; // arbitrary source vertex (e.g., the first currency)

        // array to track predecessors to reconstruct the path if a cycle is found
        int[] predecessors = new int[n];
        Arrays.fill(predecessors, -1);

        // perform the bellman-ford algorithm to find the shortest paths
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    // relax the edge if a shorter path is found
                    if (distances[u] != Double.POSITIVE_INFINITY && distances[u] + logGraph[u][v] < distances[v] - 1e-9) {
                        distances[v] = distances[u] + logGraph[u][v];
                        predecessors[v] = u;
                    }
                }
            }
        }

        // check for negative weight cycles by trying to relax edges one more time
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                // use a slightly larger epsilon to avoid false positives
                if (distances[u] != Double.POSITIVE_INFINITY && distances[u] + logGraph[u][v] < distances[v] - 1e-7) {
                    // negative cycle detected; reconstruct the arbitrage cycle
                    printArbitrageCycle(predecessors, u);
                    return true;
                }
            }
        }

        // no arbitrage found
        System.out.println("no arbitrage opportunity detected.");
        return false;
    }

    // method to print the currency sequence that forms an arbitrage cycle
    // it traces back the predecessors to form the cycle
    private void printArbitrageCycle(int[] predecessors, int startVertex) {
        // create a list to store the cycle
        List<Integer> cycle = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        // find the start of the cycle by traversing the predecessor array
        int current = startVertex;
        while (!visited.contains(current)) {
            visited.add(current);
            current = predecessors[current];
        }

        // reconstruct the cycle by tracing the predecessors
        int cycleStart = current;
        do {
            cycle.add(current);
            current = predecessors[current];
        } while (current != cycleStart);
        cycle.add(cycleStart); // add the start to complete the cycle

        // print the cycle with the currency names
        String[] currencyNames = graph.getCurrencyNames();
        System.out.print("arbitrage opportunity detected: ");
        for (int i = cycle.size() - 1; i >= 0; i--) {
            System.out.print(currencyNames[cycle.get(i)] + " ");
        }
        System.out.println();
    }
}
