package com.xhuihui.app.carTcp;

public class stringto16 {
	
	
	public static String stringto16(String s) 
	{ 
			String str=""; 
			for (int i=0;i<s.length();i++) 
			{ 
			int ch = (int)s.charAt(i); 
			
			String s4 = Integer.toHexString(ch); 
			str = str + s4; 
			} 
			return str; 
	} 
	
	public static String hexStringToString(String s) {  
        if (s == null || s.equals("")) {  
            return null;  
        }  
        s = s.replace(" ", "");  
        byte[] baKeyword = new byte[s.length() / 2];  
        for (int i = 0; i < baKeyword.length; i++) {  
            try {  
                baKeyword[i] = (byte) (0xff & Integer.parseInt(  
                        s.substring(i * 2, i * 2 + 2), 16));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        try {  
            s = new String(baKeyword, "gbk");  
            new String();  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
        return s;  
    }  
	
	
	public static void main(String[] args){
		
		String  str="LSVN941Z0D2122820";
		
		System.out.println(stringto16(str));
		
		String str1="4c53564e3934315a304432313232383230";
	
		System.out.println(hexStringToString(str1));
	}
	
 



}
