package ru.cubos.simpleaggregation;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;

public class ScannerJFrame extends JFrame {
    private String scanResult = "";
    private long lastScanTime;
    private int keyDelayForScanner = 100;
    public ScannerJFrame(){

        Thread checkLastKeyDelay = new Thread(() -> {
            while(true){
                long keyPeriod = System.currentTimeMillis() - lastScanTime;
                if (keyPeriod>keyDelayForScanner && scanResult!=""){
                    System.out.println("Scan result " + scanResult);
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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                switchKeyBoardEn();
                lastScanTime = System.currentTimeMillis();
                onKeyGot(keyEvent.getKeyChar());
                scanResult += (char)keyEvent.getKeyChar();
            }
            @Override
            public void keyPressed(KeyEvent keyEvent) { }
            @Override
            public void keyReleased(KeyEvent keyEvent) { }
        });
    }

    public void switchKeyBoardEn(){
        Locale loc = new Locale("en","US");
        this.setLocale(loc);
        this.getInputContext().selectInputMethod(loc);
    }

    public void onKeyGot(char key){

    }

    public void onScan(String scanResult){

    }
}
