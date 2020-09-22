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
    public String lastKey = "";
    public static String specialSymbol = "[specSymbol]";

    Thread checkLastKeyDelay;
    KeyEventDispatcher keyEventDispatcher;
    private boolean stopThreadVal = false;

    public void stopKeyListeners(){
        stopThreadVal = true;
        try {
            KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
        }catch(Exception e){}
    }

    public void startScanListeners(){
        checkLastKeyDelay = new Thread(() -> {
            while(true){
                if(stopThreadVal) break;
                long keyPeriod = System.currentTimeMillis() - lastScanTime;
                if (keyPeriod>keyDelayForScanner && scanResult!="" || lastKey.equals("\n") ){
                    setOnScan();
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

        keyEventDispatcher = e -> {
            switchKeyBoardEn();
            if(e.getID() == KeyEvent.KEY_PRESSED) {
                lastScanTime = System.currentTimeMillis();
                onKeyGot(e.getKeyChar());
                if(e.getKeyChar()==65535) lastKey = specialSymbol;
                else lastKey = "" + e.getKeyChar();
                scanResult += lastKey;

                if(lastKey.equals("\n")){
                    setOnScan();
                }

                return true;
            }
            return true;
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    void setOnScan(){
        onScan(scanResult);
        scanResult = "";
        lastKey = "";
    }

    public void switchKeyBoardEn(){
        Locale loc = new Locale("en","US");
        this.setLocale(loc);
        this.getInputContext().selectInputMethod(loc);
    }

    public void onKeyGot(char key){}

    public void onScan(String scanResult){}
}
