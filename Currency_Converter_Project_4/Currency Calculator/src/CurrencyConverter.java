public class CurrencyConverter {
    private CurrencyAPI api;

    public CurrencyConverter(CurrencyAPI api) {
        this.api = api;
    }

    public double convertCurrency(double amount, String from, String to) throws Exception {
        double conversionRate = api.fetchConversionRate(from, to);
        return amount * conversionRate;
    }
}
