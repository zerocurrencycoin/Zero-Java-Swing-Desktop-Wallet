package com.vaklinov.zcashui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

/**
 * Preferences UI
 * 
 * @author Ângelo Andrade Cirino <aacirino@gmail.com>
 */

public class PreferencesPanel extends JPanel {

	private static final long serialVersionUID = 5635379194672422151L;
	private JTextField mainnetRPCPortTextField;
	private JTextField testnetRPCPortTextField;
	private JTextField commandLineToolsDirTextField;

	/**
	 * Create the panel.
	 */
	public PreferencesPanel(final WalletPreferences prefs) {
		
		// Build content
		JPanel preferencesPanel = this;
		preferencesPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		preferencesPanel.setLayout(new BorderLayout(0, 0));
	
		// Set panel of fields
		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fieldsPanel.setBounds(6, 115, 615, 129);
		preferencesPanel.add(fieldsPanel, BorderLayout.NORTH);

		JLabel mainnetPortLabel = new JLabel("mainnet RPC port");
		mainnetPortLabel.setBounds(85, 17, 109, 16);
		
		mainnetRPCPortTextField = new JTextField();
		mainnetRPCPortTextField.setBounds(206, 12, 82, 26);
		mainnetRPCPortTextField.setText((String) null);
		mainnetRPCPortTextField.setColumns(6);
		
		JLabel testnetPortLabel = new JLabel("testnet RPC port");
		testnetPortLabel.setBounds(92, 45, 102, 16);
		
		testnetRPCPortTextField = new JTextField();
		testnetRPCPortTextField.setBounds(206, 40, 82, 26);
		testnetRPCPortTextField.setText((String) null);
		testnetRPCPortTextField.setColumns(6);
		
		JLabel commandLineToolsLabel = new JLabel("Command line tools directory");
		commandLineToolsLabel.setBounds(7, 69, 187, 16);
		
		commandLineToolsDirTextField = new JTextField();
		commandLineToolsDirTextField.setBounds(206, 64, 394, 26);
		commandLineToolsDirTextField.setText((String) null);
		commandLineToolsDirTextField.setColumns(32);
		fieldsPanel.setLayout(null);
		
		fieldsPanel.add(mainnetPortLabel);
		fieldsPanel.add(mainnetRPCPortTextField);
		fieldsPanel.add(testnetPortLabel);
		fieldsPanel.add(testnetRPCPortTextField);
		fieldsPanel.add(commandLineToolsLabel);
		fieldsPanel.add(commandLineToolsDirTextField);
		
		mainnetRPCPortTextField.setText(Integer.toString(prefs.mainnetRPCPort()));
		testnetRPCPortTextField.setText(Integer.toString(prefs.testnetRPCPort()));
		commandLineToolsDirTextField.setText(prefs.commandLineToolsDir());
		
		preferencesPanel.add(fieldsPanel);
		
		// Set panel of buttons
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		buttonsPanel.setBounds(6, 115, 615, 129);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
		preferencesPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		JButton btnSavePreferences = new JButton("Save preferences");
		btnSavePreferences.setBounds(6, 133, 152, 29);
		buttonsPanel.add(btnSavePreferences);
		
		JButton btnRevertToDefault = new JButton("Revert to default");
		btnRevertToDefault.setBounds(169, 133, 152, 29);
		buttonsPanel.add(btnRevertToDefault);

		btnSavePreferences.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) 
			{
				savePreferences(prefs);
			}
		});
		
		btnRevertToDefault.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) 
			{
				defaultPreferences(prefs);
			}
		});
	}

	private void savePreferences(WalletPreferences prefs) {
		prefs.mainnetRPCPort(Integer.parseInt(mainnetRPCPortTextField.getText()));
		prefs.testnetRPCPort(Integer.parseInt(testnetRPCPortTextField.getText()));
		prefs.commandLineToolsDir(commandLineToolsDirTextField.getText());
		prefs.savePreferences();
	}

	protected void defaultPreferences(WalletPreferences prefs) {
		prefs.mainnetRPCPort(WalletPreferences.mainnetRPCPortDefault);
		prefs.testnetRPCPort(WalletPreferences.testnetRPCPortDefault);
		prefs.commandLineToolsDir(WalletPreferences.commandLineToolsDirDefault);
		prefs.savePreferences();

		mainnetRPCPortTextField.setText(Integer.toString(WalletPreferences.mainnetRPCPortDefault));
		testnetRPCPortTextField.setText(Integer.toString(WalletPreferences.testnetRPCPortDefault));
		commandLineToolsDirTextField.setText(WalletPreferences.commandLineToolsDirDefault);
	}
}
