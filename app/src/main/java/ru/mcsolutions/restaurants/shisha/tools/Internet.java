package ru.mcsolutions.restaurants.shisha.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class Internet {

//    URL url;
//    HttpURLConnection connection;
    //http://stackoverflow.com/questions/9767952/how-to-add-parameters-to-httpurlconnection-using-post

//    DefaultHttpClient http;

    Context context;
    ConnectivityManager connectivity=null;

    HTTPRequestTask httpTask;

    public String result = "";

    List<String> paramNames, paramValues;

    public Internet(Context context) {
        this.context = context;
        paramNames = new ArrayList<String>();
        paramValues = new ArrayList<String>();
    }

    public String getConnectionType(){

        String result = "";
        connectivity = (ConnectivityManager)
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity==null){
            result =  Global.INTERNET_NOT_AVAILABLE;
        }else{

            NetworkInfo network = connectivity.getActiveNetworkInfo();
            if (network!=null){
                int t = network.getType();
                if (t == ConnectivityManager.TYPE_WIFI){
                    WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    if (wm!=null){
                        WifiInfo wifiInfo = wm.getConnectionInfo();
                        result =  "wifi - "+wifiInfo.getSSID();
                    }
                }else {
                    if (t == ConnectivityManager.TYPE_MOBILE) {

                        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        if (telephony != null) {
                            String isRoaming = "";
                            if (telephony.isNetworkRoaming()) {
                                isRoaming = " (Roaming)";
                            }
                            result = "mobile - " + telephony.getNetworkOperatorName() + isRoaming;
                        }
                    } else {
                        result = "какая-то сеть";
                    }
                }

            }

        }
        return result;
    }

    public boolean isExists(){
        boolean result = false;
        connectivity = (ConnectivityManager)
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity!=null){
            NetworkInfo intworkInfo = connectivity.getActiveNetworkInfo();
            if(intworkInfo!=null)
                result = true;
        }
        return result;
    }

    public boolean startURL(String packageprocedure, final Handler handler){

        connectivity = (ConnectivityManager)
                this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity==null){
            Toast.makeText(context, "Интернет отсутствует", Toast.LENGTH_SHORT).show();
            return false;
        }else {

            httpTask = new HTTPRequestTask();
            httpTask.execute(packageprocedure);

            Thread thread = new Thread(new Runnable(){
                public void run(){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        while(getStatus() != AsyncTask.Status.FINISHED){
                            try {
                                TimeUnit.MILLISECONDS.sleep(200);
                                handler.sendEmptyMessage(Global.HTTP_PENDING);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (getStatus() == AsyncTask.Status.FINISHED){
                                result = getURLString();
                                handler.sendEmptyMessage(Global.HTTP_FINISHED);
                            }
                        }
                    }
                    return;
                }
            }
            );
            thread.start();
        }
        return true;
    }

    public void cancel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            httpTask.cancel(false);
        }
    }

    public AsyncTask.Status getStatus(){
        AsyncTask.Status result = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            result =  httpTask.getStatus();
        }
        return result;
    }
    public String getURLString(){

        String result;

        try {
            result = httpTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = "Interrupted";
        } catch (ExecutionException e) {
            e.printStackTrace();
            result = null;
        } catch (CancellationException e) {
            e.printStackTrace();
            result = "Interrupted";
        }
        return result;
    }


    public void addParamNameValue(String name, String value){
        paramNames.add("in_"+name);
        paramValues.add(value);
    }
/*
    public void addLookupParam(Lookup lookup){
        paramNames.add("in_id"+lookup.getTag());
        paramValues.add(lookup.getReqId());
    }
    */
    public void addDoubleParamNameValue(String name, Double value){
        paramNames.add("in_"+name);
        paramValues.add(value.toString().replace(".",","));
    }
/*
    public void addDatePickParam(DatePick datePick){
        paramNames.add("in_"+datePick.getTag());
        if(datePick.getDate()==null){
            paramValues.add("");
        }else{
            paramValues.add(Global.getDDMMYYYY(datePick.getDate()));
        }
    }
*/

    public class HTTPRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String result = "";


            try {

                String urlString = Global.HTTP + params[0];
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(Global.TIMEOUT);
                connection.setConnectTimeout(Global.TIMEOUT);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestProperty("Authorization", "Basic "+Global.CREDENTIALS);
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                String urlParams = "auth=" +Global.COMPANY+","+ Global.googleAccount;
                for(int i=0;i<paramNames.size();i++){
                    urlParams = urlParams + "&" + paramNames.get(i) + "=" + URLEncoder.encode(paramValues.get(i),"UTF-8");
                }
Utils.log(urlString + "?" + urlParams);
                paramNames.clear();
                paramValues.clear();
                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParams.getBytes().length));
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(urlParams);
                dos.flush();
                dos.close();
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "Cp1251"));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = br.readLine()) != null){
                    response.append(line);
                    response.append("\n");
                }
                br.close();
                result = response.toString();
Utils.log(result);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
        }
        @Override
        protected void onCancelled(){
            Log.d("timur","interrupted");
        }

    }
/*
    public void uploadFile(String absoluteFileName, int size){
        File file = new File(absoluteFileName);
        if(file.exists()){
            new UploadFileAsync().execute(absoluteFileName, "" + size);
        }
    }

    class UploadFileAsync extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Выгрузка файла на сервер...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            String absoluteFileName = params[0];
            Log.d("timur", absoluteFileName);
            String fileName = "photo_1471379746745.jpg";
            int size = Integer.parseInt(params[1]);
            Log.d("timur", "size = "+size);
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "---------------------------303731608612582";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1024 * 1024;
            paramNames.clear();
            paramValues.clear();
            try {
                URL url = new URL(Global.HTTP_UPLOAD);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(Global.TIMEOUT);
                connection.setConnectTimeout(Global.TIMEOUT);
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
//                connection.setRequestProperty("uploaded_file", fileName);
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"in_filename\"; filename=\""+fileName+"\""+lineEnd);
                dos.writeBytes("Content-Type: image/png"+lineEnd);
                dos.writeBytes(lineEnd);

                FileInputStream fileInputStream = new FileInputStream(absoluteFileName);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//                int serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();
                result = serverResponseMessage;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        protected void onProgressUpdate(String... progress) {
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            progressDialog.dismiss();
        }
    }
*/
    public void downloadInstallApk(String fileName, int fileSize) {
        new DownloadFileAsync().execute(fileName, ""+fileSize);

    }

    class DownloadFileAsync extends AsyncTask<String, String, String> {

        String fileName;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Загрузка файла...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(false);
            progressDialog.show();


        }

        @Override
        protected String doInBackground(String... args) {
/*            try {
                fileName = args[0];
                int apkSize = Integer.parseInt(args[1]);
                Log.d("timur", "Size of apk: " + apkSize);

                HttpPost httpPost = new HttpPost(Global.HTTP+"apk");
                try {
                    List<NameValuePair> listNameValuePairs = new ArrayList<NameValuePair>();
                    if (Global.IMEI == null){
                        Global.IMEI = ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    }
                    listNameValuePairs.add(new BasicNameValuePair("in_imei",Global.IMEI));
                    if(!fileName.equals("")){
                        listNameValuePairs.add(new BasicNameValuePair("in_packname",fileName));
                    }
                    httpPost.setEntity(new UrlEncodedFormEntity(listNameValuePairs));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                httpPost.addHeader("Authorization", Global.CREDENTIALS);

                HttpResponse httpResponse = http.execute(httpPost);
                int responseCode = httpResponse.getStatusLine().getStatusCode();
                if (responseCode == 200){
                    HttpEntity entity = httpResponse.getEntity();
                    if (entity!=null){
                        InputStream input = httpResponse.getEntity().getContent();
                        OutputStream output = new FileOutputStream(
                                Environment.getExternalStorageDirectory().getPath()+"/Download/"+
                                        fileName+".apk");
                        long total = 0;
                        int read = 0;
                        byte[] bytes = new byte[1024];
                        while ((read = input.read(bytes)) != -1) {
                            total +=read;
                            publishProgress(""+(int)((total*100)/apkSize));
                            output.write(bytes, 0, read);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                }
            } catch (Exception e) {}
            */
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            progressDialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://"+
                            Environment.getExternalStorageDirectory().getPath()+
                            "/Download/"+fileName+".apk"),
                    "application/msword");
            context.startActivity(intent);

        }
    }

}
