package ru.cubos.simpleaggregation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannForm extends ScannerJFrame {
    private JPanel mainPanel;
    private JLabel cargoCode;
    private JLabel totalInCargo;
    private JTextArea itemCode;
    private JLabel cargoLabel;
    private JLabel itemLabel;
    private JLabel totalInCargoLabel;
    private JLabel statusLabel;
    private JPanel statusPanel;

    Settings settings = new Settings();

    public ScannForm(String settings_files[]) {
        super();

        for(String settings_file: settings_files){
            settings.init(settings_file);
        }

        setSize(320, 240);
        setContentPane(mainPanel);

        setTitle(settings.FORM_TITLE);

        cargoLabel.setText(settings.CARGO_LABEL_TEXT);
        cargoLabel.setForeground(new Color(settings.CARGO_LABEL_TEXT_COLOR[0], settings.CARGO_LABEL_TEXT_COLOR[1], settings.CARGO_LABEL_TEXT_COLOR[2]));
        itemLabel.setText(settings.ITEM_LABEL_TEXT);
        itemLabel.setForeground(new Color(settings.ITEM_LABEL_TEXT_COLOR[0], settings.ITEM_LABEL_TEXT_COLOR[1], settings.ITEM_LABEL_TEXT_COLOR[2]));
        totalInCargoLabel.setText(settings.TOTAL_LABEL_TEXT);
        totalInCargoLabel.setForeground(new Color(settings.TOTAL_LABEL_TEXT_COLOR[0], settings.TOTAL_LABEL_TEXT_COLOR[1], settings.TOTAL_LABEL_TEXT_COLOR[2]));

        totalInCargo.setForeground(new Color(settings.TOTAL_TEXT_COLOR[0], settings.TOTAL_TEXT_COLOR[1], settings.TOTAL_TEXT_COLOR[2]));
        setTextSize(totalInCargo, settings.TOTAL_TEXT_SIZE);

        cargoCode.setForeground(new Color(settings.CARGO_TEXT_COLOR[0], settings.CARGO_TEXT_COLOR[1], settings.CARGO_TEXT_COLOR[2]));
        cargoCode.setForeground(new Color(settings.CARGO_TEXT_COLOR[0], settings.CARGO_TEXT_COLOR[1], settings.CARGO_TEXT_COLOR[2]));
        setTextSize(cargoCode, settings.CARGO_TEXT_SIZE);

        itemCode.setForeground(new Color(settings.ITEM_TEXT_COLOR[0], settings.ITEM_TEXT_COLOR[1], settings.ITEM_TEXT_COLOR[2]));

        setTextSize(itemCode, settings.ITEM_TEXT_SIZE);
        setTextSize(cargoLabel, settings.CARGO_LABEL_TEXT_SIZE);
        setTextSize(itemLabel, settings.ITEM_LABEL_TEXT_SIZE);
        setTextSize(totalInCargoLabel, settings.TOTAL_LABEL_TEXT_SIZE);
        //itemCode.setMaximumSize(new Dimension(getWidth()-10, 20));

        mainPanel.setBackground(new Color(settings.BACKGROUND_COLOR[0],settings.BACKGROUND_COLOR[1],settings.BACKGROUND_COLOR[2]));

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
        statusPanel.setMinimumSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));
        statusPanel.setMaximumSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));
        statusPanel.setSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));

        itemCode.setMinimumSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));
        itemCode.setMaximumSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));
        itemCode.setSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));

        setConnectingStatus();
        setTextSize(statusLabel, settings.STATUS_FONT_SIZE);
        //setExtendedState(MAXIMIZED_BOTH);

        setPadding(cargoLabel, settings.CARGO_LABEL_PADDING);
        setPadding(cargoCode, settings.CARGO_TEXT_PADDING);

        setPadding(itemLabel, settings.ITEM_LABEL_PADDING);
        setPadding(itemCode, settings.ITEM_TEXT_PADDING);

        setPadding(totalInCargoLabel, settings.TOTAL_LABEL_PADDING);
        setPadding(totalInCargo, settings.TOTAL_TEXT_PADDING);

        setVisible(true);

        //onScan("00123456789012345678");
        //onScan("01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=");
        onScan("-");
        onScan("-");
        totalInCargo.setText("00000");
        //onScan("01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=01046070078211312100000000000591ee0592ek0v49b3v4t4r82pamitz5857i=");

    }

    void setPadding(JLabel jLabel, int paddings[]){
        jLabel.setBorder(new EmptyBorder(paddings[0],paddings[1],paddings[2],paddings[3]));
    }

    void setPadding(JTextArea jLabel, int paddings[]){
        jLabel.setBorder(new EmptyBorder(paddings[0],paddings[1],paddings[2],paddings[3]));
    }

    void setConnectingStatus(){
        statusLabel.setText(settings.STATUS_CONNECTING);
        statusPanel.setBackground(new Color(settings.STATUS_CONNECTING_COLOR[0], settings.STATUS_CONNECTING_COLOR[1], settings.STATUS_CONNECTING_COLOR[2]));
    }

    void setReadyStatus(){
        statusLabel.setText(settings.STATUS_READY);
        statusPanel.setBackground(new Color(settings.STATUS_READY_COLOR[0], settings.STATUS_READY_COLOR[1], settings.STATUS_READY_COLOR[2]));
    }

    void setErrorStatus(){
        statusLabel.setText(settings.STATUS_ERROR);
        statusPanel.setBackground(new Color(settings.STATUS_ERROR_COLOR[0], settings.STATUS_ERROR_COLOR[1], settings.STATUS_ERROR_COLOR[2]));
    }

    void setTextSize(JLabel jLabel, int fontSize){
        Font labelFont = jLabel.getFont();
        jLabel.setFont(new Font(labelFont.getName(), Font.BOLD, fontSize));
    }

    void setTextSize(JTextArea jLabel, int fontSize){
        Font labelFont = jLabel.getFont();
        jLabel.setFont(new Font(labelFont.getName(), Font.BOLD, fontSize));
    }

    public static void main(String[] args) {
        String settings_files[] = {
                "settings/scanner_mdlp.txt",
                "settings/common.txt",
                "settings/form_insert_320_240.txt",
        };

        new ScannForm(settings_files);
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

        Pattern patternDataMatrix = Pattern.compile(settings.REGEXP_DATAMATRIX);
        Pattern patternBoxBarcode = Pattern.compile(settings.REGEXP_BARCODE);
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
