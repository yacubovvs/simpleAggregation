package ru.cubos.simpleaggregation.Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APIConnector {
    private String ServerURL = "";
    private int TimeOut = 5000;

    public APIConnector(){
        //ServerURL = preferences.getString("serverName", "");
        //String TimeOut_temp = preferences.getString("serverTimeout", "5000");
        //TimeOut = Strings.stringToInt(TimeOut_temp, 5000);
        //if(TimeOut<0) TimeOut = 0;
    }

    public String sendToAServerSync(final String POST_PARAMS, final String ServerURLAPI){
        final String[] mIncomingData = {""};
        Runnable runnable = () -> {
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(ServerURLAPI);

                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setConnectTimeout(TimeOut);
                urlConnection.setReadTimeout(TimeOut);

                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

                if (POST_PARAMS.length()==0){
                    urlConnection.setRequestMethod("GET");
                }else{
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);
                    OutputStream os = urlConnection.getOutputStream();
                    os.write(POST_PARAMS.getBytes());
                    os.flush();
                    os.close();
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    mIncomingData[0] += inputLine;
                }

                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Request sent to address " + ServerURLAPI);
        System.out.println("Got server data " + mIncomingData[0]);

        return mIncomingData[0];
    }

    public String insertItemDisagregation(String rfidcard, String container, String itemCode){

        try {
            rfidcard    = URLEncoder.encode(rfidcard, "UTF-8");
            itemCode    = URLEncoder.encode(itemCode, "UTF-8");
            container   = URLEncoder.encode(container, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int reason = 0;
        String queryString = ServerURL + "/aggregation/extract?code=" + itemCode + "&reason=" + reason + "&container=" + container + "&rfidcard=" + rfidcard;
        String answer = sendToAServerSync("", queryString);
        return answer;
    }

    public String insertItemAggregation(String rfidcard, String container, String itemCode){
        String ServerURLAPI = "";

        container = container.replaceFirst("^0+(?!$)", "");

        try {
            container         = URLEncoder.encode(container, "UTF-8");
            itemCode    = URLEncoder.encode(itemCode, "UTF-8");
            rfidcard    = URLEncoder.encode(rfidcard, "UTF-8");
        }catch (Exception e){}

        ServerURLAPI = ServerURL + "/aggregation/insert?container=" + container + "&code=" + itemCode + "&rfidcard=" + rfidcard;

        String answer = sendToAServerSync("", ServerURLAPI);
        return answer;
    }
}
