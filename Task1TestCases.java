
import static org.junit.Assert.*;
import org.junit.Test;

// test class for the ArbitrageDetector class
public class Task1TestCases {

    // Test Case 1: No Arbitrage (Five Currencies)
    @Test
    public void testNoArbitrage() {
        System.out.println("Running testNoArbitrage...");

        // setup: exchange rates with no arbitrage (five currencies)
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 0.85, 110.0, 0.75, 1.3}, // USD to others
            {1.176, 1, 129.53, 0.88, 1.52}, // EUR to others
            {0.0091, 0.0077, 1, 0.0068, 0.012}, // JPY to others
            {1.33, 1.136, 147.5, 1, 1.74}, // GBP to others
            {0.77, 0.65, 82.5, 0.57, 1} // AUD to others
        };

        // create the currency exchange graph and arbitrage detector
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        ArbitrageDetector detector = new ArbitrageDetector(graph);

        // assert that no arbitrage is detected
        assertTrue("No arbitrage opportunity should be detected.", detector.detectArbitrage());
    }

    // Test Case 2: Arbitrage Exists (Five Currencies)
    @Test
    public void testArbitrageExists() {
        System.out.println("Running testArbitrageExists...");

        // setup: exchange rates with an arbitrage opportunity (five currencies)
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 0.9, 110.0, 0.75, 1.3}, // USD to others
            {1.1, 1, 120.0, 0.8, 1.4}, // EUR to others
            {0.0091, 0.008, 1, 0.0069, 0.011}, // JPY to others
            {1.34, 1.25, 144.0, 1, 1.75}, // GBP to others
            {0.75, 0.7, 83.0, 0.57, 1} // AUD to others
        };

        // create the currency exchange graph and arbitrage detector
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        ArbitrageDetector detector = new ArbitrageDetector(graph);

        // assert that an arbitrage is detected
        assertTrue("An arbitrage opportunity should be detected.", detector.detectArbitrage());
    }

    // Test Case 3: Real-World Exchange Rates (No Arbitrage)
    @Test
    public void testRealWorldNoArbitrage() {
        System.out.println("Running testRealWorldNoArbitrage...");

        // setup: real-world exchange rates with no arbitrage (exchange rates as of a specific date)
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 1.1121, 0.0071, 1.3182, 0.6731}, // USD to others
            {0.8992, 1, 0.0064, 1.1853, 0.6056}, // EUR to others
            {139.8800, 155.5605, 1, 184.3898, 94.2092}, // JPY to others
            {0.7586, 0.8437, 0.0054, 1, 0.5109}, // GBP to others
            {1.4848, 1.6512, 0.0106, 1.9572, 1} // AUD to others
        };

        // create the currency exchange graph and arbitrage detector
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        ArbitrageDetector detector = new ArbitrageDetector(graph);

        // assert that no arbitrage is detected
        assertTrue("No arbitrage opportunity should be detected with real-world exchange rates.", detector.detectArbitrage());
    }

    // Test Case 4: Real-World Exchange Rates (Arbitrage Exists)
    @Test
    public void testRealWorldArbitrageExists() {
        System.out.println("Running testRealWorldArbitrageExists...");

        // setup: hypothetical real-world exchange rates with potential arbitrage
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 1.1121, 0.0071, 1.3182, 0.6731}, // USD to others
            {0.8992, 1, 0.0064, 1.1853, 0.6056}, // EUR to others
            {139.8800, 155.5605, 1, 184.3898, 94.2092}, // JPY to others
            {0.7586, 0.8437, 0.0054, 1, 0.5109}, // GBP to others
            {1.4848, 1.6512, 0.0106, 1.9572, 1} // AUD to others
        };

        // create the currency exchange graph and arbitrage detector
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        ArbitrageDetector detector = new ArbitrageDetector(graph);

        // assert that an arbitrage is detected
        assertTrue("An arbitrage opportunity should be detected with real-world exchange rates.", detector.detectArbitrage());
    }
}
