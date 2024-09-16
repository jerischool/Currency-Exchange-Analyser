
import static org.junit.Assert.*;
import org.junit.Test;

// test class for the BestConversionRateFinder class
public class Task2TestCases {

    // Test Case 5: Direct Conversion (Five Currencies)
    @Test
    public void testDirectConversion() {
        System.out.println("Running testDirectConversion...");

        // setup: exchange rates with direct conversion (five currencies)
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 0.85, 110.0, 0.75, 1.3}, // USD to others
            {1.176, 1, 129.53, 0.88, 1.52}, // EUR to others
            {0.0091, 0.0077, 1, 0.0068, 0.012}, // JPY to others
            {1.33, 1.136, 147.5, 1, 1.74}, // GBP to others
            {0.77, 0.65, 82.5, 0.57, 1} // AUD to others
        };

        // create the currency exchange graph and best conversion rate finder
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        BestConversionRateFinder finder = new BestConversionRateFinder(graph);

        // capture the best conversion rate
        double bestRate = finder.findBestConversionRate("USD", "EUR");

        // allow a tolerance in the assertion to avoid precision errors
        assertFalse("Expected a direct conversion rate close to 0.85", bestRate > 0.84 && bestRate < 0.86);
    }

    // Test Case 6: Intermediate Conversion (Five Currencies)
    @Test
    public void testIntermediateConversion() {
        System.out.println("Running testIntermediateConversion...");

        // setup: exchange rates with intermediate conversion (five currencies)
        int numberOfCurrencies = 5;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "AUD"};
        double[][] rates = {
            {1, 0.85, 110.0, 0.75, 1.3}, // USD to others
            {1.176, 1, 129.53, 0.88, 1.52}, // EUR to others
            {0.0091, 0.0077, 1, 0.0068, 0.012}, // JPY to others
            {1.33, 1.136, 147.5, 1, 1.74}, // GBP to others
            {0.77, 0.65, 82.5, 0.57, 1} // AUD to others
        };

        // create the currency exchange graph and best conversion rate finder
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        BestConversionRateFinder finder = new BestConversionRateFinder(graph);

        // capture the best conversion rate (USD -> JPY)
        double bestRate = finder.findBestConversionRate("USD", "JPY");

        // allow a tolerance in the assertion
        assertTrue("Expected a better rate than direct conversion", bestRate > 110.0 && bestRate < 130.0);
    }

    // Test Case 7: Real-World Exchange Rates (Direct Conversion)
    @Test
    public void testRealWorldDirectConversion() {
        System.out.println("Running testRealWorldDirectConversion...");

        // setup: real-world exchange rates (direct conversion, nine currencies)
        int numberOfCurrencies = 8;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "CHF", "CAD", "AUD", "HKD"};
        double[][] rates = {
            {1, 1.1122, 0.0072, 1.3180, 1.1847, 0.7365, 0.6731, 0.1283}, // USD to others
            {0.8991, 1, 0.0064, 1.1850, 1.0652, 0.6622, 0.6052, 0.1154}, // EUR to others
            {139.8100, 155.4967, 1, 184.2696, 165.6320, 102.9756, 94.1061, 17.9384}, // JPY to others
            {0.7587, 0.8439, 0.0054, 1, 0.8989, 0.5588, 0.5107, 0.0974}, // GBP to others
            {0.8441, 0.9388, 0.0060, 1.1125, 1, 0.6217, 0.5682, 0.1083}, // CHF to others
            {1.3577, 1.5100, 0.0097, 1.7894, 1.6085, 1, 0.9139, 0.1742}, // CAD to others
            {1.4857, 1.6524, 0.0106, 1.9581, 1.7601, 1.0943, 1, 0.1906}, // AUD to others
            {7.7939, 8.6684, 0.0558, 10.2724, 9.2334, 5.7405, 5.2461, 1} // HKD to others
        };

        // create the currency exchange graph and best conversion rate finder
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        BestConversionRateFinder finder = new BestConversionRateFinder(graph);

        // capture the best conversion rate and assert the result
        double bestRate = finder.findBestConversionRate("EUR", "GBP");

        // Allow a tolerance in the assertion
        assertFalse("Expected a rate close to 1.1850", bestRate > 1.18 && bestRate < 1.19);
    }

    // Test Case 8: Real-World Exchange Rates (Intermediate Conversion)
    @Test
    public void testRealWorldIntermediateConversion() {
        System.out.println("Running testRealWorldIntermediateConversion...");

        // setup: real-world exchange rates (intermediate conversion, nine currencies)
        int numberOfCurrencies = 8;
        String[] currencies = {"USD", "EUR", "JPY", "GBP", "CHF", "CAD", "AUD", "HKD"};
        double[][] rates = {
            {1, 1.1122, 0.0072, 1.3180, 1.1847, 0.7365, 0.6731, 0.1283}, // USD to others
            {0.8991, 1, 0.0064, 1.1850, 1.0652, 0.6622, 0.6052, 0.1154}, // EUR to others
            {139.8100, 155.4967, 1, 184.2696, 165.6320, 102.9756, 94.1061, 17.9384}, // JPY to others
            {0.7587, 0.8439, 0.0054, 1, 0.8989, 0.5588, 0.5107, 0.0974}, // GBP to others
            {0.8441, 0.9388, 0.0060, 1.1125, 1, 0.6217, 0.5682, 0.1083}, // CHF to others
            {1.3577, 1.5100, 0.0097, 1.7894, 1.6085, 1, 0.9139, 0.1742}, // CAD to others
            {1.4857, 1.6524, 0.0106, 1.9581, 1.7601, 1.0943, 1, 0.1906}, // AUD to others
            {7.7939, 8.6684, 0.0558, 10.2724, 9.2334, 5.7405, 5.2461, 1} // HKD to others
        };

        // create the currency exchange graph and best conversion rate finder
        CurrencyExchangeGraph graph = new CurrencyExchangeGraph(numberOfCurrencies, currencies, rates);
        BestConversionRateFinder finder = new BestConversionRateFinder(graph);

        // capture the best conversion rate and assert the result (USD -> AUD via intermediate conversion)
        double bestRate = finder.findBestConversionRate("USD", "AUD");

        // Allow a tolerance in the assertion
        assertTrue("Expected a rate better than 1.4857", bestRate > 1.4857);
    }
}
