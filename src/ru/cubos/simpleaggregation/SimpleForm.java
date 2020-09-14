package ru.cubos.simpleaggregation;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleForm extends ScannerJFrame {
    private JPanel mainPanel;
    private JLabel cargoCode;
    private JLabel totalInCargo;
    private JTextArea itemCode;
    private JLabel cargoLabel;
    private JLabel itemLabel;
    private JLabel totalInCargoLabel;
    private JLabel statusLabel;
    private JPanel statusPanel;

    public static final String REGEXP_BARCODE = "(00[0-9]{18})$";
    public static final String REGEXP_DATAMATRIX = "^.?((01[0-9]{14})(21[0-9a-zA-Z]{13}).?(91[0-9a-zA-Z]{4}).?(92[0-9a-zA-Z+/=]{44}))";

    public SimpleForm() {
        super();

        setSize(320, 240);
        setContentPane(mainPanel);

        setTitle(Settings.FORM_TITLE);

        cargoLabel.setText(Settings.CARGO_LABEL_TEXT);
        itemLabel.setText(Settings.ITEM_LABEL_TEXT);
        totalInCargoLabel.setText(Settings.TOTAL_LABEL_TEXT);

        setTextSize(cargoLabel, Settings.CARGO_LABEL_TEXT_SIZE);
        setTextSize(itemLabel, Settings.ITEM_LABEL_TEXT_SIZE);
        setTextSize(totalInCargoLabel, Settings.TOTAL_LABEL_TEXT_SIZE);
        //itemCode.setMaximumSize(new Dimension(getWidth()-10, 20));

        mainPanel.setBackground(new Color(Settings.BACKGROUND_COLOR[0],Settings.BACKGROUND_COLOR[1],Settings.BACKGROUND_COLOR[2]));

        //textArea.setText(text);
        itemCode.setWrapStyleWord(true);
        itemCode.setLineWrap(true);
        itemCode.setOpaque(false);
        itemCode.setEditable(false);
        itemCode.setFocusable(false);
        itemCode.setBackground(UIManager.getColor("Label.background"));
        itemCode.setFont(UIManager.getFont("Label.font"));
        itemCode.setBorder(UIManager.getBorder("Label.border"));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setConnectingStatus();
        statusPanel.setMinimumSize(new Dimension(-1, Settings.STATUS_LABEL_HEIGHT));
        statusPanel.setMaximumSize(new Dimension(-1, Settings.STATUS_LABEL_HEIGHT));
        statusPanel.setSize(new Dimension(-1, Settings.STATUS_LABEL_HEIGHT));

        itemCode.setMinimumSize(new Dimension(-1, Settings.ITEM_TEXT_LABEL_SIZE));
        itemCode.setMaximumSize(new Dimension(-1, Settings.ITEM_TEXT_LABEL_SIZE));
        itemCode.setSize(new Dimension(-1, Settings.ITEM_TEXT_LABEL_SIZE));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        setConnectingStatus();
                        Thread.sleep(5000);
                        setReadyStatus();
                        Thread.sleep(5000);
                        setErrorStatus();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        setVisible(true);

        onScan("00123456789012345678");
        onScan("01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=");
        //onScan("01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=");

    }

    void setConnectingStatus(){
        statusLabel.setText(Settings.STATUS_CONNECTING);
        statusPanel.setBackground(new Color(Settings.STATUS_CONNECTING_COLOR[0], Settings.STATUS_CONNECTING_COLOR[1], Settings.STATUS_CONNECTING_COLOR[2]));
    }

    void setReadyStatus(){
        statusLabel.setText(Settings.STATUS_READY);
        statusPanel.setBackground(new Color(Settings.STATUS_READY_COLOR[0], Settings.STATUS_READY_COLOR[1], Settings.STATUS_READY_COLOR[2]));
    }

    void setErrorStatus(){
        statusLabel.setText(Settings.STATUS_ERROR);
        statusPanel.setBackground(new Color(Settings.STATUS_ERROR_COLOR[0], Settings.STATUS_ERROR_COLOR[1], Settings.STATUS_ERROR_COLOR[2]));
    }

    void setTextSize(JLabel jLabel, int fontSize){
        Font labelFont = jLabel.getFont();
        jLabel.setFont(new Font(labelFont.getName(), Font.BOLD, fontSize));
    }

    public static void main(String[] args) {
        // write your code here
        Settings.init();
        new SimpleForm();
    }

    @Override
    public void onKeyGot(char key) {
        return;
    }

    @Override
    public void onScan(String scanResult) {
        System.out.println("scanned value: " + scanResult);
        scanResult = scanResult.trim();
        if(scanResult.equals("")) scanResult = "-";

        Pattern patternDataMatrix = Pattern.compile(REGEXP_DATAMATRIX);
        Pattern patternBoxBarcode = Pattern.compile(REGEXP_BARCODE);
        Matcher matcherDataMatrix = patternDataMatrix.matcher(scanResult);
        Matcher matcherBoxBarcode = patternBoxBarcode.matcher(scanResult);

        if(matcherBoxBarcode.matches()){
            cargoCode.setText(scanResult);
        }else if(matcherDataMatrix.matches()){
            itemCode.setText(scanResult);
        }else{
            itemCode.setText(scanResult);
        }
    }
}
