package com.yintai.kuaidi100service.common

import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.NameValuePair
import org.apache.commons.httpclient.methods.GetMethod
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.params.HttpMethodParams

/**
 * Created by PangQian on 2016/8/8.
 */
public class HttpRequest {

 public  static String postUrl(String urlstring, String paramstring) {
        String result = ""
        java.net.HttpURLConnection connection = null
        try {
            URL url = new URL(urlstring)
            connection = (HttpURLConnection) url.openConnection()
            connection.setDoOutput(true)
            connection.setRequestMethod("POST")
            connection.setConnectTimeout(30000)
            connection.setRequestProperty("Content-type", "application/json")
            connection.setRequestProperty("Accept","application/json")
            if(paramstring) {
                connection.getOutputStream().write(paramstring.getBytes("UTF-8"))
            }
            connection.getOutputStream().flush()
            connection.getOutputStream().close()
            int code = connection.getResponseCode()
            InputStream inff = connection.getInputStream()
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inff))
            String tempLine = reader.readLine()
            StringBuilder buider = new StringBuilder()
            while (tempLine != null) {
                buider.append(tempLine)
                tempLine = reader.readLine()
            }
            result = buider.toString()
            reader.close()
            inff.close()
        } catch (MalformedURLException e) {
            System.out.println(e)
            e.printStackTrace()

        } catch (IOException e) {
            System.out.println(e)
            e.printStackTrace()
        } finally {
            if (connection != null)
                connection.disconnect()
        }
        return result;
    }

    public static String addUrl(String head, String tail) {
        if (head.endsWith("/")) {
            if (tail.startsWith("/")) {
                return head.substring(0, head.length() - 1) + tail;
            } else {
                return head + tail;
            }
        } else {
            if (tail.startsWith("/")) {
                return head + tail;
            } else {
                return head + "/" + tail;
            }
        }
    }
    public synchronized static String postData(String url, Map<String, String> params, String codePage) throws Exception {

        final HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);

        final PostMethod method = new PostMethod(url);
        if (params != null) {
            method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
            method.setRequestBody(assembleRequestParams(params));
        }
        String result = "";
        try {
            httpClient.executeMethod(method);
            result = new String(method.getResponseBody(), codePage);
        } catch (final Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    public synchronized static String postData(String url, String codePage) throws Exception {
        final HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);

        final GetMethod method = new GetMethod(url);
        String result = "";
        try {
            httpClient.executeMethod(method);
            result = new String(method.getResponseBody(), codePage);
        } catch (final Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return result;
    }

    /**
     * 组装http请求参数
     *
     * @param params
     * @param menthod
     * @return
     */
    private synchronized static NameValuePair[] assembleRequestParams(Map<String, String> data) {
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }

        return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
    }
}
