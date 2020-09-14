package ru.cubos.simpleaggregation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;

public class ScannerJFrame extends JFrame {
    private String scanResult = "";
    private long lastScanTime;
    private int keyDelayForScanner = 100;
    public ScannerJFrame(){
        startScanListeners();
    }

    public void startScanListeners(){
        Thread checkLastKeyDelay = new Thread(() -> {
            while(true){
                long keyPeriod = System.currentTimeMillis() - lastScanTime;
                if (keyPeriod>keyDelayForScanner && scanResult!=""){
                    //System.out.println("Scan result " + scanResult);
                    onScan(scanResult);
                    scanResult = "";
                }
                try {
                    Thread.sleep(keyDelayForScanner/2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        checkLastKeyDelay.start();
        lastScanTime = System.currentTimeMillis();

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                switchKeyBoardEn();
                if(e.getID() == KeyEvent.KEY_PRESSED && e.getModifiers() == 0) {
                    lastScanTime = System.currentTimeMillis();
                    onKeyGot(e.getKeyChar());
                    scanResult += (char) e.getKeyChar();
                    return true;
                }
                return true;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    public void switchKeyBoardEn(){
        Locale loc = new Locale("en","US");
        this.setLocale(loc);
        this.getInputContext().selectInputMethod(loc);
    }

    public void onKeyGot(char key){

    }

    public void onScan(String scanResult){
        //System.out.println("scanned value");
    }
}
