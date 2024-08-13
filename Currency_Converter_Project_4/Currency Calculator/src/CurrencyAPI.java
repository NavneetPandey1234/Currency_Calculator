import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import org.json.JSONObject;

public class CurrencyAPI {
    private static final String API_KEY = "8b3c49ccc45a6ca8a9a7c526"; // Replace with your actual API key

    public double fetchConversionRate(String from, String to) throws IOException {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;
        URL url;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL", e);
        }

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(content.toString());
        return json.getJSONObject("conversion_rates").getDouble(to);
    }
}
