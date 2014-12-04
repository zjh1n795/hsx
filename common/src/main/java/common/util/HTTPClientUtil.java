package common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 对httpClient的一个封装
 * 
 * @author jie
 * 
 */
public class HTTPClientUtil {

	protected static final Log log = LogFactory.getLog(HTTPClientUtil.class);
	
	
	public static String execute(HttpGet httpget){
		return execute(httpget, "utf-8");
	}
	/**
	 * 执行get方式请求
	 * @param httpget
	 * @return
	 */
	public static String execute(HttpGet httpget,String code) {
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		String info = null;
		try {
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				 log.info("executing request :" + httpget.getURI());
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 打印响应内容
					info=EntityUtils.toString(entity);
					log.info("Response content: "+ info);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.error("Response content: " +info);
           log.error(e.toString(),e);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("Response content: " +info);
           log.error(e.toString(),e);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Response content: " +info);
           log.error(e.toString(),e);
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("Response content: " +info);
	           log.error(e.toString(),e);
			}
		}
		return info;
	}
	
	/**
	 * 执行post方式请求
	 * @param httppost
	 * @param formparams
	 * @return
	 */
	public static String execute(HttpPost httppost, List<NameValuePair> formparams){
		return execute( httppost,  formparams,"utf-8");
	}
	
	public static String execute(HttpPost httppost, List<NameValuePair> formparams,String code){
		String info=null;
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams,"UTF-8");  
            httppost.setEntity(uefEntity);  
            log.info("executing request :" + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    info= EntityUtils.toString(entity, "UTF-8");
                    log.info("Response content: " +info);
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();
           log.error("Response content: " +info);
           log.error(e.toString(),e);
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
            log.error("Response content: " +info);
           log.error(e.toString(),e);
        } catch (IOException e) {  
            e.printStackTrace();
            log.error("Response content: " +info);
           log.error(e.toString(),e);
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) { 
            	log.error("Response content: " +info);
               log.error(e.toString(),e);
                e.printStackTrace();  
            }  
        }  
        return info;
	}
	

}
