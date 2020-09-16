package ru.cubos.simpleaggregation;

import ru.cubos.simpleaggregation.Helpers.Common;

import java.io.FileReader;
import java.io.IOException;

public class Settings {
    public int          BACKGROUND_COLOR[]              = {0,0,0};
    public String       CARGO_LABEL_TEXT                = "";
    public int          CARGO_LABEL_TEXT_COLOR[]        = {255,255,255};
    public int          CARGO_LABEL_TEXT_SIZE           = 16;
    public int          CARGO_TEXT_COLOR[]              = {255,255,255};
    public int          CARGO_TEXT_SIZE                 = 16;
    public String       ITEM_LABEL_TEXT                 = "";
    public int          ITEM_LABEL_TEXT_COLOR[]         = {255,255,255};
    public int          ITEM_LABEL_TEXT_SIZE            = 16;
    public int          ITEM_TEXT_COLOR[]               = {255,255,255};
    public int          ITEM_TEXT_SIZE                  = 16;
    public String       TOTAL_LABEL_TEXT                = "";
    public int          TOTAL_LABEL_TEXT_COLOR[]        = {255,255,255};
    public int          TOTAL_LABEL_TEXT_SIZE           = 16;
    public String       FORM_TITLE                      = "Simple aggregation";

    public String       ARM_ID                          = "";

    public int          STATUS_LABEL_HEIGHT             = 50;
    public String       STATUS_READY                    = "Ready";
    public String       STATUS_ERROR                    = "Error";
    public String       STATUS_PENDING                  = "Pending";
    public int          STATUS_READY_COLOR[]            = {255,255,255};
    public int          STATUS_ERROR_COLOR[]            = {255,255,255};
    public int          STATUS_PENDING_COLOR[]          = {255,255,255};

    public int          ITEM_TEXT_HEIGHT                = 40;

    public int          TOTAL_TEXT_COLOR[]              = {255,255,255};
    public int          TOTAL_TEXT_SIZE                 = 16;
    public int          STATUS_FONT_SIZE                = 16;

    public int          STATUS_READY_TEXT_COLOR[]       = {255,255,255};
    public int          STATUS_ERROR_TEXT_COLOR[]       = {255,255,255};
    public int          STATUS_PENDING_TEXT_COLOR[]     = {255,255,255};

    public int          CARGO_LABEL_PADDING[]           = {0,0,0,0};
    public int          CARGO_TEXT_PADDING[]            = {0,0,0,0};
    public int          ITEM_LABEL_PADDING[]            = {0,0,0,0};
    public int          ITEM_TEXT_PADDING[]             = {0,0,0,0};
    public int          TOTAL_LABEL_PADDING[]           = {0,0,0,0};
    public int          TOTAL_TEXT_PADDING[]            = {0,0,0,0};

    public boolean      FULLSCREEN                      = false;

    public String       REGEXP_BARCODE                  = "";
    public String       REGEXP_DATAMATRIX               = "";
    public String       WINDOW_TITLE                    = "";

    public int          FORM_TITLE_COLOR[]              = {255,255,255};
    public int          FORM_TITLE_SIZE                 = 20;
    public int          FORM_TITLE_PADDING[]            = {0,0,0,0};

    public void init(String path){
        try(FileReader reader = new FileReader(path))
        {
            String string;
            while (true){
                string = readString(reader);
                switch (string){
                    case "#END":
                        return;
                    case "BACKGROUND_COLOR:":
                        BACKGROUND_COLOR = parseColor(readString(reader));
                        break;
                    case "CARGO_LABEL_TEXT:":
                        CARGO_LABEL_TEXT = readString(reader);
                        break;
                    case "CARGO_LABEL_TEXT_COLOR:":
                        CARGO_LABEL_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "CARGO_LABEL_TEXT_SIZE:":
                        CARGO_LABEL_TEXT_SIZE = Common.hardParseInt(readString(reader), CARGO_LABEL_TEXT_SIZE);
                        break;
                    case "CARGO_TEXT_COLOR:":
                        CARGO_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "CARGO_TEXT_SIZE:":
                        CARGO_TEXT_SIZE = Common.hardParseInt(readString(reader), CARGO_TEXT_SIZE);
                        break;
                    case "ITEM_LABEL_TEXT:":
                        ITEM_LABEL_TEXT = readString(reader);
                        break;
                    case "ITEM_LABEL_TEXT_COLOR:":
                        ITEM_LABEL_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "ITEM_LABEL_TEXT_SIZE:":
                        ITEM_LABEL_TEXT_SIZE = Common.hardParseInt(readString(reader), ITEM_LABEL_TEXT_SIZE);
                        break;
                    case "ITEM_TEXT_COLOR:":
                        ITEM_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "ITEM_TEXT_SIZE:":
                        ITEM_TEXT_SIZE = Common.hardParseInt(readString(reader), ITEM_TEXT_SIZE);
                        break;
                    case "TOTAL_LABEL_TEXT:":
                        TOTAL_LABEL_TEXT = readString(reader);
                        break;
                    case "TOTAL_LABEL_TEXT_COLOR:":
                        TOTAL_LABEL_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "TOTAL_LABEL_TEXT_SIZE:":
                        TOTAL_LABEL_TEXT_SIZE = Common.hardParseInt(readString(reader), TOTAL_LABEL_TEXT_SIZE);
                        break;
                    case "FORM_TITLE:":
                        FORM_TITLE = readString(reader);
                        break;
                    case "ARM_ID:":
                        ARM_ID = readString(reader);
                        break;
                    case "STATUS_LABEL_HEIGHT:":
                        STATUS_LABEL_HEIGHT = Common.hardParseInt(readString(reader), STATUS_LABEL_HEIGHT);
                        break;
                    case "STATUS_READY:":
                        STATUS_READY = readString(reader);
                        break;
                    case "STATUS_ERROR:":
                        STATUS_ERROR = readString(reader);
                        break;
                    case "STATUS_PENDING:":
                        STATUS_PENDING = readString(reader);
                        break;
                    case "STATUS_READY_COLOR:":
                        STATUS_READY_COLOR = parseColor(readString(reader));
                        break;
                    case "STATUS_ERROR_COLOR:":
                        STATUS_ERROR_COLOR = parseColor(readString(reader));
                        break;
                    case "STATUS_PENDING_COLOR:":
                        STATUS_PENDING_COLOR = parseColor(readString(reader));
                        break;
                    case "ITEM_TEXT_HEIGHT:":
                        ITEM_TEXT_HEIGHT = Common.hardParseInt(readString(reader), ITEM_TEXT_HEIGHT);
                        break;
                    case "TOTAL_TEXT_COLOR:":
                        TOTAL_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "TOTAL_TEXT_SIZE:":
                        TOTAL_TEXT_SIZE = Common.hardParseInt(readString(reader), TOTAL_TEXT_SIZE);
                        break;
                    case "STATUS_FONT_SIZE:":
                        STATUS_FONT_SIZE = Common.hardParseInt(readString(reader), STATUS_FONT_SIZE);
                        break;
                    case "STATUS_READY_TEXT_COLOR:":
                        STATUS_READY_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "STATUS_ERROR_TEXT_COLOR:":
                        STATUS_ERROR_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "STATUS_PENDING_TEXT_COLOR:":
                        STATUS_PENDING_TEXT_COLOR = parseColor(readString(reader));
                        break;
                    case "CARGO_LABEL_PADDING:":
                        CARGO_LABEL_PADDING = parsePaddings(readString(reader));
                        break;
                    case "CARGO_TEXT_PADDING:":
                        CARGO_TEXT_PADDING = parsePaddings(readString(reader));
                        break;
                    case "ITEM_LABEL_PADDING:":
                        ITEM_LABEL_PADDING = parsePaddings(readString(reader));
                        break;
                    case "ITEM_TEXT_PADDING:":
                        ITEM_TEXT_PADDING = parsePaddings(readString(reader));
                        break;
                    case "TOTAL_LABEL_PADDING:":
                        TOTAL_LABEL_PADDING = parsePaddings(readString(reader));
                        break;
                    case "TOTAL_TEXT_PADDING:":
                        TOTAL_TEXT_PADDING = parsePaddings(readString(reader));
                        break;
                    case "REGEXP_BARCODE:":
                        REGEXP_BARCODE = readString(reader);
                        break;
                    case "REGEXP_DATAMATRIX:":
                        REGEXP_DATAMATRIX = readString(reader);
                        break;
                    case "WINDOW_SIZE:":
                        String screenSize = readString(reader);
                        if(screenSize.equals("fullscreen")) FULLSCREEN = true;
                        break;
                    case "WINDOW_TITLE:":
                        WINDOW_TITLE = readString(reader);
                        break;
                    case "FORM_TITLE_COLOR:":
                        FORM_TITLE_COLOR = parseColor(readString(reader));
                        break;
                    case "FORM_TITLE_SIZE:":
                        FORM_TITLE_SIZE = Common.hardParseInt(readString(reader), FORM_TITLE_SIZE);
                        break;
                    case "FORM_TITLE_PADDING:":
                        FORM_TITLE_PADDING = parsePaddings(readString(reader));
                        break;
                    default:
                        continue;
                }
            }

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static int[] parsePaddings(String string){
        String strings[] = string.split(",");
        int color[] = {
                Common.hardParseInt(strings[0].trim(), 0),
                Common.hardParseInt(strings[1].trim(), 0),
                Common.hardParseInt(strings[2].trim(), 0),
                Common.hardParseInt(strings[3].trim(), 0),
        };
        return color;
    }

    static int[] parseColor(String string){
        String strings[] = string.split(",");
        int color[] = {
                Common.hardParseInt(strings[0].trim(), 128),
                Common.hardParseInt(strings[1].trim(), 128),
                Common.hardParseInt(strings[2].trim(), 128),
        };
        return color;
    }

    static String readString(FileReader reader){
        String out = "";
        int c;
        while(true){
            try {
                if (!((c=reader.read())!=-1)) break;
                if (c=='\n') break;
                out += (char)c;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.print((char)c);
        }
        return out.trim();
    }
}
