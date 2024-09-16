// this class represents a currency exchange graph using an adjacency matrix.
// it supports operations like converting exchange rates to a logarithmic graph,
// which is useful for detecting arbitrage opportunities.

public class CurrencyExchangeGraph {

    // number of currencies (nodes in the graph)
    private int numberOfCurrencies;

    // array to hold the names of the currencies
    private String[] currencyNames;

    // adjacency matrix to store exchange rates between currencies
    private double[][] exchangeRates;

    // constructor to initialize the currency exchange graph
    // takes the number of currencies, their names, and the exchange rates matrix as input
    public CurrencyExchangeGraph(int numberOfCurrencies, String[] currencyNames, double[][] exchangeRates) {
        // validate the input data to prevent invalid initialization
        if (currencyNames.length != numberOfCurrencies) {
            throw new IllegalArgumentException("currencyNames length must match numberOfCurrencies.");
        }
        if (exchangeRates.length != numberOfCurrencies) {
            throw new IllegalArgumentException("exchangeRates matrix must have a size of " + numberOfCurrencies + "x" + numberOfCurrencies + ".");
        }
        for (double[] row : exchangeRates) {
            if (row.length != numberOfCurrencies) {
                throw new IllegalArgumentException("exchangeRates matrix must have " + numberOfCurrencies + " columns in every row.");
            }
        }

        this.numberOfCurrencies = numberOfCurrencies;
        this.currencyNames = currencyNames;
        this.exchangeRates = exchangeRates;
    }

    // method to print the exchange rates matrix
    // useful for debugging to ensure the matrix is set up correctly
    public void printExchangeRates() {
        System.out.println("currency exchange rates matrix:");
        for (int i = 0; i < numberOfCurrencies; i++) {
            for (int j = 0; j < numberOfCurrencies; j++) {
                System.out.print(exchangeRates[i][j] + " ");
            }
            System.out.println();
        }
    }

    // method to convert the exchange rates to a negative logarithmic form
    // this transformation is necessary for using algorithms like Bellman-Ford to detect arbitrage
    // it uses the formula: -log(rate) to transform the rates
    public double[][] convertToLogGraph() {
        double[][] logGraph = new double[numberOfCurrencies][numberOfCurrencies];
        for (int i = 0; i < numberOfCurrencies; i++) {
            for (int j = 0; j < numberOfCurrencies; j++) {
                // converting the exchange rate to its negative logarithmic form
                // this prepares the data for detecting negative weight cycles (arbitrage)
                logGraph[i][j] = -Math.log10(exchangeRates[i][j]);
            }
        }
        return logGraph;
    }

    // getter for the number of currencies
    // returns the number of nodes in the currency exchange graph
    public int getNumberOfCurrencies() {
        return numberOfCurrencies;
    }

    // getter for the currency names
    // returns the array of currency names
    public String[] getCurrencyNames() {
        return currencyNames;
    }

    // getter for the exchange rates matrix
    // returns the adjacency matrix representing exchange rates
    public double[][] getExchangeRates() {
        return exchangeRates;
    }

    // main method for testing the CurrencyExchangeGraph class
    // this is a simple test case demonstrating how to create and use the graph
    public static void main(String[] args) {
        // example input data (this is just for testing; the actual input will be provided later)
        int numberOfCurrencies = 3;
        String[] currencies = {"A", "B", "C"};
        double[][] rates = {
            {1, 0.651, 0.581},
            {1.531, 1, 0.952},
            {1.711, 1.049, 1}
        };

        // creating the graph
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);

        // print the original exchange rates matrix to verify the input
        graph.printExchangeRates();

        // convert the exchange rates to a logarithmic graph and print it
        // this is a crucial step for arbitrage detection algorithms
        double[][] logGraph = graph.convertToLogGraph();
        System.out.println("logarithmic graph:");
        for (int i = 0; i < numberOfCurrencies; i++) {
            for (int j = 0; j < numberOfCurrencies; j++) {
                System.out.print(logGraph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
