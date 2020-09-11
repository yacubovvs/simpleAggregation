package ru.cubos.simpleaggregation;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class SimpleForm extends ScannerJFrame {

    private JPanel mainPanel;
    private JLabel cargoCode;
    private JButton newCargoButton;
    private JButton clearCargo;
    private JButton sendToServer;
    private JLabel totalInCargo;
    private JPanel scanHistoryPanel;

    public SimpleForm() {
        super();
        setSize(400, 300);
        setContentPane(mainPanel);
        setVisible(true);
        setTitle("Simple Aggregation");

        scanHistoryPanel.setVisible(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // write your code here
        new SimpleForm();
    }

    public void onKeyGot(char key) {
        return;
    }

    public void onScan(String scanResult) {
        return;
    }

}
