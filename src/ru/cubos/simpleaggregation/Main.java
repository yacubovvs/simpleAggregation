package ru.cubos.simpleaggregation;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends ScannerJFrame {
    private JLabel scanResult;

    public Main(){
        super();
        setSize(120, 120);
        setVisible(true);
        setTitle("Simple Aggregation");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
	// write your code here
        new Main();
    }

    public void onKeyGot(char key){
        return;
    }

    public void onScan(String scanResult){
        return;
    }
}
