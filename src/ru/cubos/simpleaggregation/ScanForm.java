package ru.cubos.simpleaggregation;

import ru.cubos.simpleaggregation.Helpers.APIConnector;
import ru.cubos.simpleaggregation.Helpers.Common;
import ru.cubos.simpleaggregation.Helpers.JSONParser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanForm extends ScannerJFrame {
    private JPanel mainPanel;
    private JLabel cargoCode;
    private JLabel totalInCargo;
    private JTextArea itemCode;
    private JLabel cargoLabel;
    private JLabel itemLabel;
    private JLabel totalInCargoLabel;
    private JLabel statusLabel;
    private JPanel statusPanel;
    private JLabel formTitleLabel;
    private boolean blockReading = false;
    Settings settings = new Settings();

    String current_box      = "";
    String current_item     = "";
    String current_count    = "";

    private FormType currentType;

    enum FormType {
        Insert,
        Extract
    }

    public ScanForm(String settings_files[], FormType type) {
        super();

        this.currentType = type;
        switchKeyBoardEn();

        for(String settings_file: settings_files){
            settings.init(settings_file);
        }

        //setSize(480, 320);
        setContentPane(mainPanel);

        if(settings.FULLSCREEN) setExtendedState(MAXIMIZED_BOTH);
        else{
            setSize(settings.WINDOW_SIZE[0], settings.WINDOW_SIZE[1]);
        }

        setTitle(settings.WINDOW_TITLE);

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

        //textArea.setText(text);
        itemCode.setWrapStyleWord(true);
        itemCode.setLineWrap(true);
        itemCode.setOpaque(false);
        itemCode.setEditable(false);
        itemCode.setFocusable(false);
        itemCode.setBackground(UIManager.getColor("Label.background"));
        //itemCode.setFont(UIManager.getFont("Label.font"));
        itemCode.setBorder(UIManager.getBorder("Label.border"));

        setTextSize(itemCode, settings.ITEM_TEXT_SIZE);
        setTextSize(cargoLabel, settings.CARGO_LABEL_TEXT_SIZE);
        setTextSize(itemLabel, settings.ITEM_LABEL_TEXT_SIZE);
        setTextSize(totalInCargoLabel, settings.TOTAL_LABEL_TEXT_SIZE);
        //itemCode.setMaximumSize(new Dimension(getWidth()-10, 20));

        mainPanel.setBackground(new Color(settings.BACKGROUND_COLOR[0],settings.BACKGROUND_COLOR[1],settings.BACKGROUND_COLOR[2]));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statusPanel.setMinimumSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));
        statusPanel.setMaximumSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));
        statusPanel.setSize(new Dimension(-1, settings.STATUS_LABEL_HEIGHT));

        itemCode.setMinimumSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));
        itemCode.setMaximumSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));
        itemCode.setSize(new Dimension(-1, settings.ITEM_TEXT_HEIGHT));

        setTextSize(statusLabel, settings.STATUS_FONT_SIZE);

        setPadding(cargoLabel, settings.CARGO_LABEL_PADDING);
        setPadding(cargoCode, settings.CARGO_TEXT_PADDING);

        setPadding(itemLabel, settings.ITEM_LABEL_PADDING);
        setPadding(itemCode, settings.ITEM_TEXT_PADDING);

        setPadding(totalInCargoLabel, settings.TOTAL_LABEL_PADDING);
        setPadding(totalInCargo, settings.TOTAL_TEXT_PADDING);

        setPadding(formTitleLabel, settings.FORM_TITLE_PADDING);
        setTextSize(formTitleLabel, settings.FORM_TITLE_SIZE);
        formTitleLabel.setText(settings.FORM_TITLE);
        formTitleLabel.setForeground(new Color(settings.FORM_TITLE_COLOR[0], settings.FORM_TITLE_COLOR[1], settings.FORM_TITLE_COLOR[2]));

        setPendingStatus("Отсканируйте короб");
        setVisible(true);

        //onScan("00123456789012345678");
        //onScan("010460700782113121000000000005E91ee0592s0FXM0lXOYDtnU7QNWH93jam61KMVShZqbdnpDqHTFs=");

        itemCode.setText("-");
        cargoCode.setText("-");
        setValueInCargo("-");

        /*
        // При изменении размера формы
        this.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                ScanForm.this.repaint();
                ScanForm.this.validate();
            }
        });
        */
    }

    void setPadding(JLabel jLabel, int paddings[]){
        jLabel.setBorder(new EmptyBorder(paddings[0],paddings[1],paddings[2],paddings[3]));
    }

    void setPadding(JTextArea jLabel, int paddings[]){
        jLabel.setBorder(new EmptyBorder(paddings[0],paddings[1],paddings[2],paddings[3]));
    }

    void setPendingStatus(){
        statusLabel.setText(settings.STATUS_PENDING);
        statusLabel.setForeground(new Color(settings.STATUS_PENDING_TEXT_COLOR[0], settings.STATUS_PENDING_TEXT_COLOR[1], settings.STATUS_PENDING_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_PENDING_COLOR[0], settings.STATUS_PENDING_COLOR[1], settings.STATUS_PENDING_COLOR[2]));
    }

    void setPendingStatus(String pendingText){
        statusLabel.setText(pendingText);
        statusLabel.setForeground(new Color(settings.STATUS_PENDING_TEXT_COLOR[0], settings.STATUS_PENDING_TEXT_COLOR[1], settings.STATUS_PENDING_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_PENDING_COLOR[0], settings.STATUS_PENDING_COLOR[1], settings.STATUS_PENDING_COLOR[2]));
    }



    void setReadyStatus(){
        statusLabel.setText(settings.STATUS_READY);
        statusLabel.setForeground(new Color(settings.STATUS_READY_TEXT_COLOR[0], settings.STATUS_READY_TEXT_COLOR[1], settings.STATUS_READY_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_READY_COLOR[0], settings.STATUS_READY_COLOR[1], settings.STATUS_READY_COLOR[2]));
    }

    void setReadyStatus(String readyText){
        statusLabel.setText(readyText);
        statusLabel.setForeground(new Color(settings.STATUS_READY_TEXT_COLOR[0], settings.STATUS_READY_TEXT_COLOR[1], settings.STATUS_READY_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_READY_COLOR[0], settings.STATUS_READY_COLOR[1], settings.STATUS_READY_COLOR[2]));
    }

    void setErrorStatus(){
        statusLabel.setText(settings.STATUS_ERROR);
        statusLabel.setForeground(new Color(settings.STATUS_ERROR_TEXT_COLOR[0], settings.STATUS_ERROR_TEXT_COLOR[1], settings.STATUS_ERROR_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_ERROR_COLOR[0], settings.STATUS_ERROR_COLOR[1], settings.STATUS_ERROR_COLOR[2]));
    }

    void setErrorStatus(String errorText){
        statusLabel.setText(errorText);
        statusLabel.setForeground(new Color(settings.STATUS_ERROR_TEXT_COLOR[0], settings.STATUS_ERROR_TEXT_COLOR[1], settings.STATUS_ERROR_TEXT_COLOR[2]));
        statusPanel.setBackground(new Color(settings.STATUS_ERROR_COLOR[0], settings.STATUS_ERROR_COLOR[1], settings.STATUS_ERROR_COLOR[2]));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mainPanel.setBackground(new Color(settings.STATUS_ERROR_COLOR[0], settings.STATUS_ERROR_COLOR[1], settings.STATUS_ERROR_COLOR[2]));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainPanel.setBackground(new Color(settings.BACKGROUND_COLOR[0],settings.BACKGROUND_COLOR[1],settings.BACKGROUND_COLOR[2]));
            }
        });
        thread.start();
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
        startInsert();
    }

    public static void startInsert() {
        String settings_files[] = {
                "settings/scanner_mdlp.txt",
                "settings/common.txt",
                "settings/form_insert_fullScreen.txt",
        };

        new ScanForm(settings_files, FormType.Insert);
    }

    public static void startExtract() {
        String settings_files[] = {
                "settings/scanner_mdlp.txt",
                "settings/common.txt",
                "settings/form_extract_fullScreen.txt",
        };

        new ScanForm(settings_files, FormType.Extract);
    }

    //int i= 0;
    /*
    @Override
    public void onKeyGot(char key) {
        //i++;
        //System.out.print("" + i + "   *" + key + "*");
        //System.out.println("*" + (int)key + "*");
        return;
    }*/

    @Override
    public void onScan(String scanResult) {
        //System.out.println("scanned value raw: " + scanResult);
        scanResult = scanResult.replace(specialSymbol + "0029", ""); // Need for zebra barcode scanner for datamatrix MDLP
        //scanResult = scanResult.replace(specialSymbol + "029", ""); // Need for zebra barcode scanner for datamatrix MDLP
        scanResult = scanResult.replace(specialSymbol, "");
        //scanResult = scanResult.replace(" ", "");
        //scanResult = scanResult.replaceAll("[\\x00-\\x1F]", ""); // removing unread symbols
        System.out.println("scanned value: " + scanResult);
        scanResult = scanResult.trim();
        if(scanResult.equals("")) scanResult = "-";

        Pattern patternDataMatrix = Pattern.compile(settings.REGEXP_DATAMATRIX);
        Pattern patternBoxBarcode = Pattern.compile(settings.REGEXP_BARCODE);
        Matcher matcherDataMatrix = patternDataMatrix.matcher(scanResult);
        Matcher matcherBoxBarcode = patternBoxBarcode.matcher(scanResult);

        setReadyStatus();

        if(matcherBoxBarcode.matches()){
            onInputNewBox(scanResult);
        }else if(matcherDataMatrix.matches()){
            onInputNewItem(scanResult);
        }else{
            //Commands
            if(scanResult.equals("command_exit")){
                System.exit(0);
            }if(scanResult.equals("command_insert")){
                stopKeyListeners();
                this.dispose();
                startInsert();
            }if(scanResult.equals("command_extract")){
                stopKeyListeners();
                this.dispose();
                startExtract();
            }else{
                setErrorStatus("Не верный штрих код");
            }
        }

    }

    /*
    String current_box      = "";
    String current_item     = "";
    String current_count    = "";
    */

    void onInputNewBox(String boxScannedCode){
        cargoCode.setText(boxScannedCode);
        current_box = boxScannedCode;
        current_item = "";
        itemCode.setText("-");
        totalInCargo.setText("-");
        setPendingStatus("Отсканируйте товар");
    }

    void setValueInCargo(int value){
        int minLength = 5;
        String sValue = "0000000000000" + value;
        if(("" + value).length()>minLength){
            totalInCargo.setText("" + value);
        }else{
            totalInCargo.setText(sValue.substring(sValue.length()-5));
        }
    }

    void setValueInCargo(String value){
        totalInCargo.setText(value);
    }

    void onInputNewItem(String itemScannedCode){
        if(!settings.ASYNC_SEND && blockReading){
            setErrorStatus("Подождите отправки данных на сервер");
            return;
        }
        blockReading = true;
        itemCode.setText(itemScannedCode);
        current_item = itemScannedCode;
        if(current_box.equals("")){
            setErrorStatus("Не введен короб");
            blockReading = false;
        }else{
            setPendingStatus("Отправка");

            Thread thread = new Thread(() -> {
                APIConnector apiConnector = new APIConnector(settings.SERVER_ADDRESS, settings.ARM_ID);
                if(currentType==FormType.Extract){
                    String resultText = apiConnector.insertItemDisagregation(current_box, current_item);
                    String result = JSONParser.getElementOfJSON(resultText, "result");
                    if(result.substring(0,5).equals("error")){
                        String message = JSONParser.getElementOfJSON(resultText, "message");
                        setErrorStatus(message);
                    }else if(result.equals("success")){
                        setReadyStatus();
                        String count = JSONParser.getElementOfJSON(resultText, "count");
                        setValueInCargo(Common.hardParseInt(count, 0));
                    }else{
                        setErrorStatus("Ошибка связи с сервером");
                    }
                }else if(currentType==FormType.Insert){
                    String resultText = apiConnector.insertItemAggregation(current_box, current_item);
                    String result = JSONParser.getElementOfJSON(resultText, "result");
                    if(result.substring(0,5).equals("error")){
                        String message = JSONParser.getElementOfJSON(resultText, "message");
                        setErrorStatus(message);
                    }else if(result.equals("success")){
                        setReadyStatus();
                        String count = JSONParser.getElementOfJSON(resultText, "count");
                        setValueInCargo(Common.hardParseInt(count, 0));
                    }else{
                        setErrorStatus("Ошибка связи с сервером");
                    }
                }

                blockReading = false;
            });
            thread.start();
            if(!settings.ASYNC_SEND){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            /*
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/


        }
    }
}
