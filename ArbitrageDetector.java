
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
    // it returns true if an arbitrage is detected, along with the currency sequence that demonstrates it
    public boolean detectArbitrage() {
        // get the number of currencies and the logarithmic graph representation of the exchange rates
        int n = graph.getNumberOfCurrencies();
        double[][] logGraph = graph.convertToLogGraph();

        // initialize the distance array with positive infinity and set the source to zero
        // use an array to keep track of predecessors for each currency
        double[] distances = new double[n];
        int[] predecessors = new int[n];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[0] = 0; // starting with the first currency
        Arrays.fill(predecessors, -1);

        // perform the bellman-ford algorithm
        // iterate n-1 times over all edges to update distances
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (distances[u] != Double.POSITIVE_INFINITY && distances[u] + logGraph[u][v] < distances[v]) {
                        distances[v] = distances[u] + logGraph[u][v];
                        predecessors[v] = u;
                    }
                }
            }
        }

        // check for negative weight cycles by trying to relax edges one more time
        // if successful, a negative weight cycle exists, indicating an arbitrage opportunity
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (distances[u] != Double.POSITIVE_INFINITY && distances[u] + logGraph[u][v] < distances[v]) {
                    // arbitrage detected; retrieve the cycle
                    printArbitrageCycle(predecessors, v);
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
    private void printArbitrageCycle(int[] predecessors, int start) {
        int n = graph.getNumberOfCurrencies();
        String[] currencyNames = graph.getCurrencyNames();

        // use a set to find the start of the cycle
        Set<Integer> visited = new HashSet<>();
        int current = start;
        while (!visited.contains(current)) {
            visited.add(current);
            current = predecessors[current];
        }

        // reconstruct the cycle
        List<Integer> cycle = new ArrayList<>();
        int cycleStart = current;
        do {
            cycle.add(current);
            current = predecessors[current];
        } while (current != cycleStart);
        cycle.add(cycleStart); // complete the cycle

        // print the cycle with the currency names
        System.out.print("arbitrage opportunity detected: ");
        for (int i = cycle.size() - 1; i >= 0; i--) {
            System.out.print(currencyNames[cycle.get(i)] + " ");
        }
        System.out.println();
    }

    // main method for testing the arbitrage detector
    public static void main(String[] args) {
        // example input data for testing
        int numberOfCurrencies = 3;
        String[] currencies = {"A", "B", "C"};
        double[][] rates = {
            {1, 0.651, 0.581},
            {1.531, 1, 0.952},
            {1.711, 1.049, 1}
        };

        // create the currency exchange graph
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);

        // create the arbitrage detector and run the detection
        ArbitrageDetector detector = new ArbitrageDetector(graph);
        detector.detectArbitrage();
    }
}
