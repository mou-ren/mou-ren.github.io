package com.xhuihui.app.carTcp;/*
 * 发送实时数据 
 */

public class wholedata {
	
	public static String  wholedata() 
	{ 
	
	String  start="2323";  //起始符
	String  command="02";  //命令标识---实时数据
    String  answer="fe";  //应答标识
	String  vin="LA888M1E0GKLW0460";  //识别码
//	String  vin="LGBH12E2XEY207941";  //识别码
	String  encrypttype="01";  //加密方式  1byte
	
	String  head=start+command+answer+stringto16.stringto16(vin)+encrypttype;
	//数据长度lenstr  2byte
	//-----------------------------------数据单元-----------------------------------
	String  time="170911143700";  //数据采集时间
    System.out.print("时间："+time);

	//----------------------------------整车数据------------------------------------
	String  type1="01";         //数据类别  
	String  carstatus="03";     //车辆状态  1byte   01启动 02熄火 03其他 fe异常  ff无效 
	String  chargeStatus="03";  //充电状态    1 byte   01：停车充电； 02：行驶充电； 03：未充电状态； 04：充电完成 ；FE：异常；FF：无效
	String  runmode="01";       //运行模式  1byte   01纯电  02混动  03燃油  fe异常 ff无效
	
	String  speed="0001";       //车速  2byte       有效值范围： 0～2200（表示 0 km/h～220 km/h）
	String  mileage="00000001"; //累计里程  4byte    有效值范围： 0～9999999（表示 0km～999999.9km）
	
	String totalVoltage="0001"; //总电压   2byte  有效值范围：0～10000（表示 0V～1000V）
    String totalAmp="0001";     //总电流   2byte  有效值范围： 0～20000 （偏移量 1000A，表示-1000A～+1000A）
    String soc="01";            //SOC  1byte   有效值范围： 0～100（表示 0%～100%）
	
	String DCDC_status="01";   //DCDC状态  1byte   01工作  02断开  fe异常  ff无效
	
	String  gear="01";    //档位    1byte    
	String insulatingResistance="0001"; //绝缘电阻  2byte  有效范围： 0～60000（表示 0 ～60000 ）
	String  accTrip="01";   //加速踏板行程值   1byte  有效值范围： 0～100（表示 0%～100%）
	String  brakeTripstatus="01";  //制动踏板状态  1byte   0-100
	
    String wholecardata=type1+carstatus+chargeStatus+runmode+speed+mileage+totalVoltage+totalAmp+
    		soc+DCDC_status+gear+insulatingResistance+accTrip+brakeTripstatus;
	
    //----------------------------------驱动电机数据----------------------------
	String  type2="02";         //数据类别
    String  drivemotornum="02";     //驱动电机个数   1byte  1-253   
    //---------------------第一个驱动电机-------------------------------
    String  drivemotorserial="01";   //驱动电机序号  1byte  1-253   
    String  drivemotorstatus="01";  //驱动电机状态 1byte   01耗电 02发电 03关闭 04准备 fe异常 ff无效
    String  controllertemp="05";    //驱动电机控制器温度  1byte   0-250  （表示-40~210）  
    String  rotateSpeed="0005";       //驱动电机转速   2byte   有效值范围： 0～65531（偏移量20000）    
    String  Drivemotortorque="0005";  //驱动电机转矩   2byte   0-65531  偏移量20000 再除以10
    String  drivemotortemp="05";      //驱动电机温度   1byte   0-250   偏移量40
    String  controllervoltage="0005";  //电机控制器输入电压  2byte    0-60000  表示0-6000
    String  buscurrent="0005";     //电机控制器直流母线电流 2byte   0-20000（-1000~1000）先除以10 再偏移量1000
  
    
    
    String  drivemotorserial2="01";   //驱动电机序号  1byte  1-253   
    String  drivemotorstatus2="04";  //驱动电机状态 1byte   01耗电 02发电 03关闭 04准备 fe异常 ff无效
    String  controllertemp2="01";    //驱动电机控制器温度  1byte   0-250  （表示-40~210）  
    String  rotateSpeed2="0001";       //驱动电机转速   2byte   有效值范围： 0～65531（偏移量20000）    
    String  Drivemotortorque2="0001";  //驱动电机转矩   2byte   0-65531  先偏移20000 再小数点往前1位
    String  drivemotortemp2="01";      //驱动电机温度   1byte   0-250   偏移量40
    String  controllervoltage2="0001";  //电机控制器输入电压  2byte    0-60000  表示0-6000
    String  buscurrent2="0001";     //电机控制器直流母线电流 2byte   0-20000（-1000~1000）偏移量1000
    
    /*
    //---------------------第三个驱动电机---------------------------
    String  drivemotorserial3="03";   //驱动电机序号  1byte  1-253   
    String  drivemotorstatus3="fe";  //驱动电机状态 1byte   01耗电 02发电 03关闭 04准备 fe异常 ff无效
    String  controllertemp3="64";    //驱动电机控制器温度  1byte   0-250  （表示-40~210）  
    String  rotateSpeed3="0064";       //驱动电机转速   2byte   有效值范围： 0～65531（偏移量20000）    
    String  Drivemotortorque3="0064";  //驱动电机转矩   2byte   0-65531  先偏移20000 再小数点往前1位
    String  drivemotortemp3="64";      //驱动电机温度   1byte   0-250   偏移量40
    String  controllervoltage3="0064";  //电机控制器输入电压  2byte    0-60000  表示0-6000
    String  buscurrent3="0064";     //电机控制器直流母线电流 2byte   0-20000（-1000~1000）偏移量1000
    //---------------------第四个驱动电机---------------------------
    String  drivemotorserial4="04";   //驱动电机序号  1byte  1-253   
    String  drivemotorstatus4="ff";  //驱动电机状态 1byte   01耗电 02发电 03关闭 04准备 fe异常 ff无效
    String  controllertemp4="64";    //驱动电机控制器温度  1byte   0-250  （表示-40~210）  
    String  rotateSpeed4="0064";       //驱动电机转速   2byte   有效值范围： 0～65531（偏移量20000）    
    String  Drivemotortorque4="0064";  //驱动电机转矩   2byte   0-65531  先偏移20000 再小数点往前1位
    String  drivemotortemp4="64";      //驱动电机温度   1byte   0-250   偏移量40
    String  controllervoltage4="0064";  //电机控制器输入电压  2byte    0-60000  表示0-6000
    String  buscurrent4="0064";     //电机控制器直流母线电流 2byte   0-20000（-1000~1000）偏移量1000
    
    */
        
    
    String drivemotordata=type2+drivemotornum+drivemotorserial+drivemotorstatus+controllertemp+rotateSpeed
    		+Drivemotortorque+drivemotortemp+controllervoltage+buscurrent+drivemotorserial2+drivemotorstatus2+
    		controllertemp2+rotateSpeed2+Drivemotortorque2+drivemotortemp2+controllervoltage2+buscurrent2;
    
    		/*
    		+drivemotorserial3+drivemotorstatus3+controllertemp3+rotateSpeed3+Drivemotortorque3+drivemotortemp3
    		+controllervoltage3+buscurrent3+drivemotorserial4+drivemotorstatus4+controllertemp4+
    		rotateSpeed4+Drivemotortorque4+drivemotortemp4+controllervoltage4+buscurrent4;
    */
   //------------------------------燃料电池数据--------------------------------------
    
    String type3="03";   
    String Fuelcellvoltage="0006";   //燃料电池电压    0-20000  （0-2000） 2byte
    String Fuelcellcurrent="0006";   //燃料电池电流    0-20000  （0-2000）  2byte
    String fuelconsumptionrate="0006";  //燃料消耗率   0-60000（表示0~600 ）2byte
    String tempprobenum="0009";  //温度探针总数  0-65531   2byte    N个
    String probetemp="090807060504030201";                //探针温度值 byte[n]  0-240  偏移量40  1个探针占1byte  n个探针占n个byte
    
    String  Hydrogenmaxtemp="0006";  //氢系统中最高温度  2byte   0-2400（偏移量40，表示-40~200）
    String  maxtempProbecode="01";  //最高温度探针代号  1byte  1-252
    String  hydromaxconsi="0006";  //氢气最高浓度   2byte   0-60000  （表示0-60000）
    String  consisensorcode="02";  //氢气最高浓度传感器代号  1byte   1-252 
    String  hydromaxpressure="0006";   //氢气最高压力 2byte  0-1000（表示0-100）
    String  pressuresensorcode="03";  //氢气最高压力传感器代号  1byte  1-252
    String  highpressureDC="01";   //高压DC/DC状态   1byte   01工作 02断开 fe异常  ff无效
   
    
    String fuelcelldata=type3+Fuelcellvoltage+Fuelcellcurrent+fuelconsumptionrate+tempprobenum+probetemp
    		+Hydrogenmaxtemp+maxtempProbecode+hydromaxconsi+consisensorcode+hydromaxpressure+
    		pressuresensorcode+highpressureDC;
    
   //-----------------------------------发动机数据-------------------------------
    
    String type4="04";  
    String motorstatus="01";            //发动机状态   1个byte  01启动 02关闭 fe异常  ff无效
    String crankshaftspeed="0006";      //曲轴转速   2byte    0-60000  
    String fuelconsumptionrate1="0006";  //燃料消耗率2byte    0-60000   (表示0-600)
    
    String  motordata=type4+motorstatus+crankshaftspeed+fuelconsumptionrate1;
    
    //-------------------------------------位置数据-------------------------------
    String  type5="05";    //数据类别
    String status="00";       //定位状态  1byte    Bit0——0： 有效定位； 1： 无效定位（当数据通信正常，而不能获取定位信息时，发送最后一次有效定位信息，并将定位状态置为无效）
											    //Bit1——0： 北纬； 1： 南纬
											    //Bit2——0： 东经； 1： 西经
											    //Bit3~7——保留

    String longitude="06f08211";    //经度    4byte
    String latitude="0260e764";     //纬度    4byte

/*    String longitude="06eef094";    //经度    4byte
    String latitude="0261b590";     //纬度    4byte*/

    String locationdata=type5+status+longitude+latitude;
    
    //-------------------------------------极值数据---------------------
    String  type6="06";    //数据类别
    
    String maxVoltageBatterysubsystemid="01";  //最高电压电池子系统号  1byte
    String maxVoltageBatterycellcode="01";  //最高电压电池单体代号  1byte

    String maxVoltage="0001";                 //电池单体电压最高值 2byte  有效值范围： 0～15000（表示 0V～15V），最小计量单元： 0.001V
    String minVoltageBatterysubsystemid="01";  //最低电压电池子系统号  1byte
    String minVoltageBatterycellcode="01";  //最低电压电池单体代号  1byte
    String minVoltage="0001";                  //电池单体电压最低值2byte   有效值范围： 0～15000（表示 0V～15V），最小计量单元： 0.001V；无效值范围：15001~65535

    String maxTempsubsystemOrder="01";      //最高温度子系统号  1byte   1-250
    String maxTempProbeOrder="01";            //最高温度探针序号1byte    1-250
    String maxTemp="01";                      //最高温度值1byte   有效值范围： 0～250（数值偏移量 40℃，表示-40℃～+210℃）
    String minTempsubsystemOrder="01";    //最低温度子系统号  1byte   1-250
    String minTempProbeOrder="01";             //最低温度探针序号1byte    1-250
    String minTemp="01";                       //最低温度值1byte   有效值范围： 0～250（数值偏移量 40℃，表示-40℃～+210℃）
   
    String extrimdata=type6+maxVoltageBatterysubsystemid+maxVoltageBatterycellcode+maxVoltage+
    		minVoltageBatterysubsystemid+minVoltageBatterycellcode+minVoltage+maxTempsubsystemOrder
    		+maxTempProbeOrder+maxTemp+minTempsubsystemOrder+minTempProbeOrder+minTemp;
    
    
    //----------------------------------报警数据--------------------------------
    
    String  type7="07";    //数据类别
    String  maxalarmlevel="01";   //最高报警等级   1byte  范围：0-3  0是无故障  3最高等级
    String  commonalarmflag="00000000"; //通用报警标志   4byte  包括18个报警标志和预留位  全为1：0007ffff
    String  energyerrornum="02";   //可充电储能装置故障总数N1   1byte   0-252
    String  energyerrorcodelist="0000000200000001";  //可充电储能装置故障代码列表   4*N1个byte   每个故障占4个byte
    String  drivemotorerrornum="02";  //驱动电机故障总数N2  1byte  0-252
    String  drivemotorerrorcodelist="0000000200000001";  //驱动电机故障代码列表   4*N2个byte   每个故障占4个byte
    String  engineerrornum="02";  //发动机故障总数N3  1byte  0-252
    String  engineerrorcodelist="0000000200000001";     //发动机故障列表       4*N3个byte   每个故障占4个byte
    String  othererrornum="02";   //其他故障总数N4  1byte   0-252
    String  othererrorcodelist="0000000200000001";    //其他故障代码列表     4*N4个byte   每个故障占4个byte
    
    
    String alarmdata=type7+maxalarmlevel+commonalarmflag+energyerrornum+energyerrorcodelist
    		+drivemotorerrornum+drivemotorerrorcodelist+engineerrornum+engineerrorcodelist
    		+othererrornum+othererrorcodelist;
    
    //-----------------------------可充电储能装置电压数据---------------------------------
    String  type8="08";    //数据类别
    
    String energystoragesubsysnum1="02";  //可充电储能子系统个数  1byte 1-250
    
    String energystoragesubsysid="01";   //可充电储能子系统号  1byte    1-250
    String energystoragevoltage="2710";  //可充电储能装置电压  2byte  0-10000（0-1000）
    String energystoragecurrent="2710";  //可充电储能装置电流  2byte 0-20000 （-1000~1000）
    String singlecellnum="0001";  //单体电池总数   2byte  1-65531  N个电池单体
    String pagestartcellnum="0001";  //本桢起始电池序号  2byte  1-65531
    String pagesinglecellnum="01";   //本桢单体电池总数m  1byte  1-200
    String singlecellvoltage="0001";  //单体电池电压   2*m个byte    0-60000（表示0-60）
    //------------------
    String energystoragesubsysid1="02";   //可充电储能子系统号  1byte    1-250
    String energystoragevoltage1="0001";  //可充电储能装置电压  2byte  0-10000（0-1000）
    String energystoragecurrent1="0001";  //可充电储能装置电流  2byte 0-20000 （-1000~1000）
    String singlecellnum1="0001";  //单体电池总数   2byte  1-65531  N个电池单体
    String pagestartcellnum1="0001";  //本桢起始电池序号  2byte  1-65531
    String pagesinglecellnum1="03";   //本桢单体电池总数m  1byte  1-200
    String singlecellvoltage1="000300020001";  //单体电池电压   2*m个byte    0-60000（表示0-60）
    
    
    
    
    String storagevoltage=type8+energystoragesubsysnum1+energystoragesubsysid+energystoragevoltage
    		+energystoragecurrent+singlecellnum+pagestartcellnum+pagesinglecellnum+singlecellvoltage
    		+energystoragesubsysid1+energystoragevoltage1+energystoragecurrent1+singlecellnum1
    		+pagestartcellnum1+pagesinglecellnum1+singlecellvoltage1;
    

    
    //---------------------------------可充电储能装置温度数据-------------------------------
        
    String type9="09";  //数据类别
    String energystoragesubsysnum2="02";  //可充电储能子系统个数  1byte  1-250
    
    String storagesubsysid="01";          //可充电储能子系统号   1byte  1-250
    String storagetempprobenum="0003";   //可充电储能温度探针个数N   2byte  1-65531
    String eachtempdata="030201";      //各温度探针检测到的温度值    N个字节  0-250（偏移40 -40~210） 1个探针温度占1个byte
    //-------------------------------------------------------
    String storagesubsysid1="02";          //可充电储能子系统号   1byte  1-250
    String storagetempprobenum1="0006";   //可充电储能温度探针个数N   2byte  1-65531
    String eachtempdata1="060504030201";      //各温度探针检测到的温度值    N个字节  0-250（偏移40 -40~210） 1个探针温度占1个byte
    
    String storagetemp=type9+energystoragesubsysnum2+storagesubsysid+storagetempprobenum+eachtempdata+storagesubsysid1
    		+storagetempprobenum1+eachtempdata1;
   
    
    /*------------------------------发送全部实时信息-----------------------------*/
    //
     
    String data=timeto16.timeto16(time)+wholecardata+drivemotordata+fuelcelldata+motordata+locationdata+extrimdata+alarmdata+storagevoltage+storagetemp;
    
    
    int len=data.length()/2;
    
	String  lenstr=Integer.toHexString(len); 
    
	System.out.println(lenstr);
    
	String  wholedata=head+"00"+lenstr+data;
		
	byte b=yiHuo.bcc(wholedata);

	
    return  wholedata+Tools.ToHexString(b);
    
    
  }

    public static void main(String[] args){

	System.out.println(wholedata());
	
}
		
}
