package cn.jufuns.core.util;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class EncodePropertyPlaceholder extends PropertyPlaceholderConfigurer {

	private static final Pattern REGEXP = Pattern.compile("ECRPT\\{([a-z0-9A-Z]+)\\}");
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
    	Matcher matcher = REGEXP.matcher(propertyValue);
    	if(matcher.find()){
    		propertyValue = SHA.decrypt(matcher.group(1));
    	}
        return super.convertProperty(propertyName, propertyValue);
        
    }
    public static void main(String[] args) {
		System.out.println(SHA.encrypt("abc"));
	}
    
   static class SHA{
    	
    	
    	private final static String E_T  = "\u200B\u200D\u200D\u200C\u200C\u200D\u200B\u200C\u200B\u200D\u200D\u200D\u200B\uFEFF\u200B\u200C\u200C\u200C\uFEFF\u200D\u200C\u200D\u200D\uFEFF\u200B\u200D\u200C\u200B\u200B\uFEFF\u200D\u200C";//"\u200B\u200D\u200D\u200C\u200C\u200D\u200B\u200C\u200B\u200D\u200D\u200D\u200B\uFEFF\u200B\u200C\u200C\u200C\uFEFF\u200D\u200C\u200D\u200D\uFEFF\u200B\u200D\u200C\u200B\u200B\uFEFF\u200D\u200C";
    	private static final Map<String,String> A_M = new HashMap<String,String>();
    	static{
    		A_M.put("\u200b", "00");
    		A_M.put("\u200c", "01");
    		A_M.put("\u200d", "10");
    		A_M.put("\ufeff", "11");
    	}
    	private static final String g_e_fa(){
    		String str = E_T;
    		for(String key:A_M.keySet()){
    			str = str.replaceAll(key, A_M.get(key));
    		}
    		byte[] byteArr = new byte[str.length()/8];
    		int idx = 0;
    		for(int i=0;i<str.length();i+=8){
    			Integer it = Integer.parseInt(str.substring(i,i+8), 2);
    			byteArr[idx++] = it.byteValue();
    		}
    		return new String(byteArr,Charset.forName("utf-8"));
    	}
    	
    	/** 字符串默认键值 */
    	private static String strDefaultKey =  g_e_fa();
    	/** 加密工具 */
    	private static Cipher encryptCipher = null;
    	/** 解密工具 */
    	private static Cipher decryptCipher = null;
    	static{
    		Key key;
    		try{
    			key = getKey(strDefaultKey.getBytes());
    			encryptCipher = Cipher.getInstance("DES");
    			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
    			decryptCipher = Cipher.getInstance("DES");
    			decryptCipher.init(Cipher.DECRYPT_MODE, key);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	
    	
    	/**
    	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
    	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
    	 * @param arrB  需要转换的byte数组
    	 * @return 转换后的字符串
    	 * @throws Exception 本方法不处理任何异常，所有异常全部抛出
    	 */
    	private final static String byteArr2HexStr(byte[] arrB) throws Exception{
    		 int iLen = arrB.length;
    		 // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
    		 StringBuffer sb = new StringBuffer(iLen * 2);
    		 for (int i = 0; i < iLen; i++) {
    			 int intTmp = arrB[i];
    			 // 把负数转换为正数
    			 while(intTmp < 0){
    				 intTmp = intTmp + 256;
    			 }
    			 // 小于0F的数需要在前面补0
    			 if (intTmp < 16) {
    				 sb.append("0");
    			 }
    			 sb.append(Integer.toString(intTmp, 16));
    		}
    		return sb.toString();
    	}  
        /** 
         * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) 
         * 互为可逆的转换过程 
         * @param strIn 需要转换的字符串 
         * @return 转换后的byte数组 
         */  
    	private final static byte[] hexStr2ByteArr(String strIn) throws Exception {  
            byte[] arrB = strIn.getBytes();  
            int iLen = arrB.length;  
            // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2  
            byte[] arrOut = new byte[iLen / 2];  
            for (int i = 0; i < iLen; i = i + 2) {  
                String strTmp = new String(arrB, i, 2);  
                arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);  
            }  
            return arrOut;  
        }  
        /** 
         * 加密字节数组 
         * @param arrB  需加密的字节数组 
         * @return 加密后的字节数组 
         */  
        public static byte[] encrypt(byte[] arrB) throws Exception {  
            return encryptCipher.doFinal(arrB);  
        }  
      
        /** 
         * 解密字节数组 
         * @param arrB  需解密的字节数组 
         * @return 解密后的字节数组 
         */  
        private final static byte[] decrypt(byte[] arrB) throws Exception {  
            return decryptCipher.doFinal(arrB);  
        }  
        /** 
         * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位 
         * @param arrBTmp  构成该字符串的字节数组 
         * @return 生成的密钥 
         */  
        private final static Key getKey(byte[] arrBTmp) throws Exception {  
            // 创建一个空的8位字节数组（默认值为0）  
            byte[] arrB = new byte[8];  
            // 将原始字节数组转换为8位  
            for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {  
                arrB[i] = arrBTmp[i];  
            }  
            // 生成密钥  
            Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");  
            return key;  
        }  
        
        
        
        /** 
         * 加密字符串 
         * @param strIn  需加密的字符串 
         * @return 加密后的字符串 
         */  
        public static final String encrypt(String strIn)  {  
            try {
    			return byteArr2HexStr(encrypt(strIn.getBytes()));
    		} catch (Exception e) {
    			//e.printStackTrace();
    			System.out.println("加密失败");
    		}  
            return "";
        }  
        /** 
         * 解密字符串 
         * @param strIn  需解密的字符串 
         * @return 解密后的字符串 
         */  
        public final static String decrypt(String strIn)  {  
            try {
    			return new String(decrypt(hexStr2ByteArr(strIn)));
    		} catch (Exception e) {
    			//e.printStackTrace();
    			System.out.println("解密失败");
    		}  
            return "";
        }  
        
       /* public static void main(String[] args) {  
            try {  

                String test2 = "14465可37600";  
                Des des2 = new Des();// 自定义密钥  
                System.out.println("加密前的字符：" + test2);  
                System.out.println("加密后的字符：" + des2.encrypt(test2));
                test2 = des2.encrypt(test2);
                System.out.println("解密后的字符：" + des2.decrypt(test2));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  */
        private static final String N = "\n";
        private static final String HELP  =  "用法：".concat(N).concat("java -jar EncodecJar [e|d] string");
    	public static void main(String[] args) {
    		
    		if(args.length!=2 || !("e".equalsIgnoreCase(args[0])|| "d".equalsIgnoreCase(args[0]))){
    			System.out.println(HELP);
    		}
    		String tmp = "";
    		if("e".equalsIgnoreCase(args[0])){
    			tmp = encrypt(args[1]);
    			if(!tmp.equals("")){
    				System.out.println("加密后：".concat(N).concat(tmp));				
    			}
    		}
    		if("d".equalsIgnoreCase(args[0])){
    			tmp = decrypt(args[1]);
    			if(!tmp.equals("")){
    				System.out.println("解密后：".concat(N) .concat(tmp));  	
    			}
    		}
    	}
    }  
}