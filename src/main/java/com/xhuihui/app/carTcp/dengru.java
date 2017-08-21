package com.xhuihui.app.carTcp;

/*
 * ��������
 */
public class dengru {
	
	
	public static String  dengru(String temp) 
	{ 
	
			String  start="2323";  //��ʼ��
			String  command="01";  //�����ʶ ----��������
		    String  answer="FE";  //Ӧ���ʶ
			String  vin=temp;  //ʶ����  stringto16
			String  encrypttype="01";  //���ܷ�ʽ  1byte
			
			
			int len;  //���ݵ�Ԫ���� 2byte
			
			//-----------------------------���ݵ�Ԫ----------------------------
			String time="170619110000";  //���ݲɼ�ʱ��    6byte   ��ת��timeto16
			String serialnum="0006";//��ˮ��    2byte  
			String ICCID="8986061509000631552N";//SIM��ICCID��     20byte  ��ת��stringto16
			String n="01";  //�ɳ�索����ϵͳ��   1byte    0-250
			String m="01";//�ɳ�索��ϵͳ���볤��  1byte    0-50
			String systemcode="01";  //�ɳ�索��ϵͳ����   ������n*m��byte    
			//-------------------------------------------------------------------------
			String data=timeto16.timeto16(time)+serialnum+stringto16.stringto16(ICCID)+n+m+systemcode;
			
			len=data.length()/2;
		    
			String  lenstr=Integer.toHexString(len); 
		    
			//System.out.println(lenstr);
			
			String head=start+command+answer+stringto16.stringto16(vin)+encrypttype;
						
			String  wholedata=head+"00"+lenstr+data;
				
			byte b=yiHuo.bcc(wholedata);

			
		    return  wholedata+Tools.ToHexString(b);
		
	
	}
	
	public static void main(String[] args){
		
	
		
		System.out.println(dengru("LA678M1E0GKLW9938"));
		
	}
	
	
	

}
