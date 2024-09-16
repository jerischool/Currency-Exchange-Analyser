
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CurrencyExchangeGraphTest {

    @Test
    void testConversionToLogGraph() {
        // example input data
        int numberOfCurrencies = 3;
        String[] currencies = {"A", "B", "C"};
        double[][] rates = {
            {1, 0.651, 0.581},
            {1.531, 1, 0.952},
            {1.711, 1.049, 1}
        };

        // create the graph
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);

        // expected logarithmic values (computed manually)
        double[][] expectedLogGraph = {
            {0, -Math.log10(0.651), -Math.log10(0.581)},
            {-Math.log10(1.531), 0, -Math.log10(0.952)},
            {-Math.log10(1.711), -Math.log10(1.049), 0}
        };

        // actual log graph from the method
        double[][] actualLogGraph = graph.convertToLogGraph();

        // compare each value in the matrices
        for (int i = 0; i < numberOfCurrencies; i++) {
            for (int j = 0; j < numberOfCurrencies; j++) {
                assertEquals(expectedLogGraph[i][j], actualLogGraph[i][j], 0.0001, "log graph conversion failed");
            }
        }
    }

    @Test
    void testGetCurrencyNames() {
        String[] currencies = {"A", "B", "C"};
        double[][] rates = {{1, 0.651, 0.581}, {1.531, 1, 0.952}, {1.711, 1.049, 1}};
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(3, currencies, rates);

        assertArrayEquals(currencies, graph.getCurrencyNames(), "currency names mismatch");
    }
}
