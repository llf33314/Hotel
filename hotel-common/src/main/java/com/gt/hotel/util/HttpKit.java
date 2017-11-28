package com.gt.hotel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * HTTP工具类
 */
public class HttpKit {

    private static Logger logger = Logger.getLogger(HttpKit.class);

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url,
                                 String param,
                                 String encode) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Charset", "utf-8");
            conn.setUseCaches(false);
            conn.setDoInput(true); //设置输入流采用字节流
            conn.setDoOutput(true); //设置输出流采用字节流
            // 建立实际的连接
            conn.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "gbk");
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += new String(line.getBytes(), "utf-8");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,
                                  String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStreamReader isr = new InputStreamReader(conn.getInputStream(),
                    "gbk");
            System.err.println("new InputStreamReader(is).getEncoding() is "
                    + isr.getEncoding());
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                if ("gbk".equals(isr.getEncoding().toLowerCase())) {
                    result += new String(line.getBytes("gbk"), "utf-8");
                } else {
                    result += new String(line.getBytes(), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 带头部信息的post请求
     *
     * @param
     * @return 服务返回信息
     */
    public static String sendPostByHeaders(String url, Map<String, String> headers, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 自定义header头部请求参数
            if (headers != null && !headers.isEmpty()) {
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += new String(line.getBytes());
            }
        } catch (Exception e) {
            logger.error("短连接接口连接出错:", e);
            throw new RuntimeException("短连接接口连接出错:" + e.getMessage());
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

//	public static void main(String[] args) throws UnsupportedEncodingException,
//			DocumentException {
//		// String xml = HttpKit
//		// .sendGet(
//		// "http://api.map.baidu.com/direction/v1/routematrix?mode=walking&ak=82d7136146878d664def8c56de8255f5&origins=23.132733310787,113.41087450025&destinations=113.38584339689,23.17176433457",
//		// "", "utf-8");
//		// 下面的是通过解析xml字符串的
//		// Document doc = null;
//		// try {
//		// doc = DocumentHelper.parseText(xml);
//		// } catch (DocumentException e) {
//		// e.printStackTrace();
//		// }
//		// Element rootElt = doc.getRootElement();
//		// if ("0".equals(rootElt.element("status").getText())) {
//		// Iterator iter = rootElt.elementIterator("result");
//		// // 遍历head节点
//		// while (iter.hasNext()) {
//		// Element recordEle = (Element) iter.next();
//		// Element lele = recordEle.element("elements");
//		// // System.out.println(lele.element("distance").element("value")
//		// // .getText());
//		// // System.out.println(lele.element("distance").element("text")
//		// // .getText());
//		// // System.out.println(lele.element("duration").element("text")
//		// // .getText());
//		// }
//		// } else {
//		//
//		// }
//
//		String result = sendPost("http://58.67.193.147:4000/sms/http/Sms2.aspx",
//				"action=sendsmsinit&username=test2&userpwd=330821&sendnumber=7&title=微官网&skipurl=http://admin.weiwangbo.com/s/MTM0Ng==&content=%E5%BE%AE%E5%AE%98%E7%BD%91");
//		System.out.println("======result:" + result);
//	}
}
