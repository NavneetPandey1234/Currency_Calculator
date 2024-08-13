import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JLabel resultLabel;
    private CurrencyConverter converter;

    public CurrencyConverterGUI(CurrencyConverter converter) {
        this.converter = converter;

        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Add components to the frame
        add(new JLabel("From:"));
        fromCurrency = new JComboBox<>(new String[] { "USD", "EUR", "GBP", "INR" });
        add(fromCurrency);

        add(new JLabel("To:"));
        toCurrency = new JComboBox<>(new String[] { "USD", "EUR", "GBP", "INR" });
        add(toCurrency);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());
        add(convertButton);

        resultLabel = new JLabel("Result: ");
        add(resultLabel);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();

                double result = converter.convertCurrency(amount, from, to);
                resultLabel.setText("Result: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid amount entered.");
            } catch (Exception ex) {
                resultLabel.setText("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyAPI api = new CurrencyAPI();
            CurrencyConverter converter = new CurrencyConverter(api);
            CurrencyConverterGUI gui = new CurrencyConverterGUI(converter);
            gui.setVisible(true);
        });
    }
}
