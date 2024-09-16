// this class is responsible for finding the best conversion rate between two currencies
// it uses the floyd-warshall algorithm to find the shortest paths in the graph

public class BestConversionRateFinder {
    private CurrencyExchangeGraph graph;

    // constructor to initialize the best conversion rate finder with a currency exchange graph
    public BestConversionRateFinder(CurrencyExchangeGraph graph) {
        this.graph = graph;
    }

    // method to find the best conversion rate from source to target currency
    // it uses the floyd-warshall algorithm to compute the shortest paths in the graph
    public void findBestConversionRate(String sourceCurrency, String targetCurrency) {
        int n = graph.getNumberOfCurrencies();
        String[] currencyNames = graph.getCurrencyNames();
        double[][] exchangeRates = graph.getExchangeRates();

        // convert currency names to indices
        int sourceIndex = findCurrencyIndex(sourceCurrency, currencyNames);
        int targetIndex = findCurrencyIndex(targetCurrency, currencyNames);

        if (sourceIndex == -1 || targetIndex == -1) {
            System.out.println("invalid source or target currency.");
            return;
        }

        // initialize distance and path matrices for floyd-warshall algorithm
        double[][] dist = new double[n][n];
        int[][] next = new int[n][n];

        // fill the distance matrix with exchange rates and set up the path matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 1;
                } else {
                    dist[i][j] = exchangeRates[i][j];
                }
                next[i][j] = j;
            }
        }

        // apply floyd-warshall algorithm to find the best conversion rates between all pairs of currencies
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][j] < dist[i][k] * dist[k][j]) {
                        dist[i][j] = dist[i][k] * dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // retrieve and print the best conversion rate and the path from source to target
        printBestConversionPath(dist, next, sourceIndex, targetIndex, currencyNames);
    }

    // helper method to find the index of a currency in the currency names array
    private int findCurrencyIndex(String currency, String[] currencyNames) {
        for (int i = 0; i < currencyNames.length; i++) {
            if (currencyNames[i].equals(currency)) {
                return i;
            }
        }
        return -1;
    }

    // method to print the best conversion path and rate from source to target currency
    private void printBestConversionPath(double[][] dist, int[][] next, int source, int target, String[] currencyNames) {
        if (dist[source][target] <= 1) {
            System.out.println("no profitable conversion from " + currencyNames[source] + " to " + currencyNames[target] + ".");
            return;
        }

        System.out.println("best conversion rate from " + currencyNames[source] + " to " + currencyNames[target] + " is: " + dist[source][target]);

        System.out.print("conversion path: ");
        int current = source;
        while (current != target) {
            System.out.print(currencyNames[current] + " -> ");
            current = next[current][target];
        }
        System.out.println(currencyNames[target]);
    }

    // main method for testing the best conversion rate finder
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

        // create the best conversion rate finder and find the best conversion rate
        BestConversionRateFinder finder = new BestConversionRateFinder(graph);
        finder.findBestConversionRate("A", "C");
    }
}
