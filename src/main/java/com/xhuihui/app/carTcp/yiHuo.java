package com.xhuihui.app.carTcp;

import javax.script.*;
  


public class yiHuo {
	
	//�����ǵ���python�ļ������У��
	


	 
	 
	 //������javaʵ�ֵ����У��
	 public static byte bcc(String data) {
		        byte[] bytes = HexString2Bytes(data);
		        byte bcc=0;
		        for (int i = 0; i < bytes.length; i++) {
		            bcc^=bytes[i];
		        }
		        return bcc;
		    }
		
	  public static byte[] HexString2Bytes(String hexstr) {
		        byte[] b = new byte[hexstr.length() / 2];
		        int j = 0;

		        for (int i = 0; i < b.length; i++) {
		            char c0 = hexstr.charAt(j++);
		            char c1 = hexstr.charAt(j++);
		         
		            b[i] = ((byte) (parse(c0) << 4 | parse(c1)));
		            // int start = i * 2;
		            // int c = Integer.parseInt(hexstr.substring(start, start + 2));
		            // b[i] = (byte)c;
		        }

		        return b;
		    }
		 
	   private static int parse(char c) {
		        if (c >= 'a') {
		            return c - 'a' + 10 & 0xF;
		        }

		        if (c >= 'A') {
		            return c - 'A' + 10 & 0xF;
		        }

		        return c - '0' & 0xF;
		    }


}
