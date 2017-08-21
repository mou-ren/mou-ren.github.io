package com.xhuihui.app.carTcp;

import java.awt.List;

public class timeto16 {
	
	
	public static String timeto16(String s) 
	{ 
			
		    String str="";
		    
		    for(int i=0;i<s.length();i=i+2){
		    	
		    	int temp=Integer.parseInt(s.substring(i, i+2));
		    	
		    	//System.out.println(Integer.toHexString(temp));
		    	
		    	String s4 = Integer.toHexString(temp);
		    	
		    	if(s4.length()==1){
		    		
		    		s4="0"+s4;
		    		
		    	}		    	
		    	
		    	
				str = str + s4;		    	
		    	
		     }
		    	
			return str; 
	} 
	
	
	public static String HextoTime(String s) 
	{ 
			
		 if (s == null || s.equals("")) {  
	            return null;  
	        }  
	        s = s.replace(" ", "");  
	       String str=""; 
	        for (int i = 0; i <( s.length()/2); i++) {  
	            try {  
	                Integer x= Integer.parseInt(  
	                        s.substring(i * 2, i * 2 + 2), 16);  
	                String temp=x.toString();
	                if(temp.length()==1){
	                	temp="0"+temp;
	                }
	                str=str+temp;
	                
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	      
	        return str;  
	} 
	
	
	
	
	
	
	
	
	
	
     public static void main(String[] args){
		
		String  str="170628210831";
		
		System.out.println(timeto16(str));
		
		
		String str1="11061c201043";
		
		System.out.println(HextoTime(str1));
	 }

}
