package conversor_moedas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConversorMoedas extends JFrame {
	private Map<String, Double> exchangeRates;
	private JTextField amountField;
	private JComboBox<String> fromComboBox;
	private JComboBox<String> toComboBox;
	private JTextField resultField;
	
	public ConversorMoedas() {
		exchangeRates = new HashMap<>();
		exchangeRates.put("USD", 1.0); //Dolar americano
		exchangeRates.put("EUR", 0.85); //Euro
		exchangeRates.put("GBP", 0.72); // Libra
		exchangeRates.put("JPY", 110.28); //Iene
		exchangeRates.put("BRL", 5.30); //Real
		exchangeRates.put("CAD", 1.21); // Dolar canadense
		
		setTitle("Conversor de Moedas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		JLabel amountLabel = new JLabel("Valor: ");
		amountField= new JTextField(10);
		JLabel fromLabel = new JLabel("De: ");
		fromComboBox = new JComboBox<>(new String[] {"USD", "EUR", "GBP", "JPY", "BRL", "CAD"});
		JLabel toLabel = new JLabel("Para: ");
		toComboBox = new JComboBox<>(new String[] {"USD", "EUR", "GBP", "JPY", "BRL", "CAD"});
		JButton convertButton = new JButton("Converte!");
		JLabel resultLabel = new JLabel("Resultado: ");
		resultField = new JTextField(10);
		resultField.setEditable(false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(5, 5, 5, 5);	
		
		cons.gridx = 0;
		cons.gridy = 0;
		add(amountLabel, cons);
		
		cons.gridx = 1;
		add(amountField, cons);
		
		cons.gridx = 0;
		cons.gridy = 1;
		add(fromLabel, cons);
		
		cons.gridx = 1;
		add(fromComboBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 2;
		add(toLabel, cons);
		
		cons.gridx = 1;
		add(toComboBox, cons);
		
		cons.gridx = 0;
		cons.gridy = 3;
		cons.gridwidth = 2;
		cons.anchor = GridBagConstraints.CENTER;
		add(convertButton, cons);
		
		cons.gridx = 0;
		cons.gridy = 4;
		cons.gridwidth = 2;
		add(resultLabel, cons);
		
		cons.gridx = 0;
		cons.gridy = 5;
		add(resultField, cons);
		
		convertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(amountField.getText());
				String fromCurrency = (String) fromComboBox.getSelectedItem();
				String toCurrency = (String) toComboBox.getSelectedItem();
				double result = converte(amount, fromCurrency, toCurrency);
				DecimalFormat df = new DecimalFormat("#.##");
				resultField.setText(df.format(result));
			}
		});
		
	}
	
	private double converte(double amount, String fromCurrency, String toCurrency) {
		double fromRate = exchangeRates.get(fromCurrency);
		double toRate = exchangeRates.get(toCurrency);
		return amount * (toRate / fromRate);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ConversorMoedas conversor = new ConversorMoedas();
				conversor.setVisible(true);
			}
			
		});
	}
}
