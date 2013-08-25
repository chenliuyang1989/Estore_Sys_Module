package com.gmcc.util;

import org.apache.commons.beanutils.BeanUtils;

import com.gmcc.model.Authority;
import com.gmcc.model.LabelValue;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Utility class to convert one object to another.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class ConvertUtils {
//    private static final Log log = LogFactory.getLog(ConvertUtils.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private ConvertUtils() {
    }

    /**
     * Method to convert a ResourceBundle to a Map object.
     * @param rb a given resource bundle
     * @return Map a populated map
     */
    public static Map<String, String> convertBundleToMap(ResourceBundle rb) {
        Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getString(key));
        }

        return map;
    }

    /**
     * Convert a java.util.List of LabelValue objects to a LinkedHashMap.
     * @param list the list to convert
     * @return the populated map with the label as the key
     */
    public static Map<String, String> convertListToMap(List<LabelValue> list) {
        Map<String, String> map = new LinkedHashMap<String, String>();

        for (LabelValue option : list) {
            map.put(option.getLabel(), option.getValue());
        }

        return map;
    }

    /**
     * Method to convert a ResourceBundle to a Properties object.
     * @param rb a given resource bundle
     * @return Properties a populated properties object
     */
    public static Properties convertBundleToProperties(ResourceBundle rb) {
        Properties props = new Properties();

        for (Enumeration<String> keys = rb.getKeys(); keys.hasMoreElements();) {
            String key = keys.nextElement();
            props.put(key, rb.getString(key));
        }

        return props;
    }

    /**
     * Convenience method used by tests to populate an object from a
     * ResourceBundle
     * @param obj an initialized object
     * @param rb a resource bundle
     * @return a populated object
     */
    public static Object populateObject(Object obj, ResourceBundle rb) {
        try {
            Map<String, String> map = convertBundleToMap(rb);
            BeanUtils.copyProperties(obj, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
    
    public static String getSubMenuStr(List<Authority> menusOfRole,List<Authority> menus){
    	StringBuilder subMenus=new StringBuilder("");
    	int i=0;
    	for(Authority menu:menus){
    		i++;
    		subMenus.append("<input type='checkbox' name='menusOfRole");    		
    		subMenus.append("' id='");
    		subMenus.append(menu.getParentId());
    		subMenus.append("-");
    		subMenus.append(menu.getId());
    		subMenus.append("' ");
    		if(menusOfRole!=null && menusOfRole.contains(menu)){
    			subMenus.append("checked='checked' ");
    		}
    		subMenus.append(" value='");
    		subMenus.append(menu.getId());
    		subMenus.append("' onclick=\"checkParent('");
    		subMenus.append(menu.getParentId());
    		subMenus.append("'); \">");
    		subMenus.append(menu.getMenuName());
    		subMenus.append("</input> ");
    		if(i%6==0){
    			subMenus.append("<br> ");
    		}
    	}
    	
    	return subMenus.toString();
    }
    
    public static String getString(List<String> str){
    	StringBuffer strs=new StringBuffer("");
    	if(str!=null){
    		for(String s:str){
    			if(s!=null&&!"".equals(s.trim())){
    		    strs.append(s);
    			}
    		}
    	}
    	return strs.toString();
    }
    
    /**
	    * 把数字转换为大写人民币的静态方法
	    * @param number 需要转换为大写人民币的金额，需要小于 九千亿（支持整数部分有12位）
	    * @return 大写的人民币
	    */
	   public static String convertToRMB(String num)
	   {
	      String number = num.indexOf(".")==-1?num:num.substring(0,num.indexOf("."));
	      String rear = number.length()==num.length()?"":num.substring(num.indexOf(".")==num.length()?num.length():num.indexOf(".")+1);
	      //去除前面无关的0
	      while(number.indexOf("0") == 0)
	      {
	         number = number.substring(1);
	      }
	      if(number.length() >12 )
	      {
	         System.out.println("数字太大了！");
	         return null;
	      }

	      //如果小数部分只有一位
	      if(rear.length()==1)
	      {
	         rear += "0";
	      }
	      //如果全为零
	      if(number.length()==0 &&(rear.length()==0 || rear.startsWith("00")))
	      {
	         return "零元整";
	      }

	      String temp[] = new String[]{"","",""};
	      int length = number.length();
	      //把数字转化为三部分分别处理
	      if(length-4 >= 0) // >=4位
	      {
	         temp[0] = number.substring(length-4,length);
	         if(length-8 >= 0) // >=8位
	         {
	            temp[1] = number.substring(length-8,length-4);
	            temp[2] = number.substring(0,length-8);
	         }
	         else //不足8位
	         {
	            temp[1] = number.substring(0,length-4);
	         }
	      }
	      else //不足4位
	      {
	         temp[0] = number.substring(0,length);
	      }
	      StringBuffer result = new StringBuffer();
	      boolean addZero = false;
	      boolean haveInt = false;
	      //处理 亿 部分
	      if(temp[2].length()>0)
	      {
	         result.append(subConvertToRMB(temp[2],true));
	         result.append("亿");
	         addZero = true;
	         haveInt = true;
	      }
	      else
	      {
	         addZero = false;
	      }
	      //如果：亿位部分有值 && 亿位部分以0结束或者万位部分以0开始  && 万位部分不为0   && 千位部分不为0
	      if(addZero && (temp[2].endsWith("0") || temp[1].startsWith("0")) && !temp[1].equals("0000") && !temp[0].equals("0000"))
	      {
	         result.append("零");
	      }
	      //处理 万 部分
	      if(temp[1].length()>0 && !temp[1].equals("0000"))
	      {
	         result.append(subConvertToRMB(temp[1],true));
	         result.append("万");
	         addZero = true;
	         haveInt = true;
	      }
	      else
	      {
	         addZero = false;
	      }
	      //如果：万位部分有值 && 万位部分以0结束或者千位部分以0开始   && 千位部分不为0
	      if(addZero && (temp[1].endsWith("0") || temp[0].startsWith("0")) && !temp[0].equals("0000"))
	      {
	         result.append("零");
	      }
	      //处理千位部分
	      if(temp[0].length()>0 && !temp[0].equals("0000"))
	      {
	         result.append(subConvertToRMB(temp[0],true));
	         addZero = true;
	         haveInt = true;
	      }

	      if(haveInt)
	      {
	         result.append("元");
	      }

	      //处理小数部分
	      if(rear.length()>0 && !rear.startsWith("00"))
	      {
	         //如果有整数部分 && 角部分为0
	         if(haveInt && rear.startsWith("0"))
	         {
	            result.append("零");
	         }
	         result.append(subConvertToRMB(rear,false));
	      }
	      result.append("整");
	     return result.toString();
	   }


	   /**
	    * 部分转化方法（千位以内的）
	    * @param number 小于四位的数字
	    * @param isInteger 此次转换是小数还是整数
	    * @return 千位以内的大写人民币
	    */
	   private static String subConvertToRMB(String number,boolean isInteger)
	   {
	      String source[] = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	      StringBuffer result = new StringBuffer();

	      //如果请求处理的是小数部分
	      if(!isInteger)
	      {
	         int len = number.length();
	         if(len==0)
	         {
	            return "";
	         }
	         //分别取得前两位
	         int t1 = --len >= 0 ? Integer.parseInt(String.valueOf(number.charAt(0))):0;
	         int t2 = --len >= 0 ? Integer.parseInt(String.valueOf(number.charAt(1))):0;
	         //按照一定的规则加上大写人民币
	         if( t1!=0)
	         {
	           result.append(source[t1]);
	            result.append("角");
	            if(t2 != 0)
	            {
	               result.append(source[t2]);
	               result.append("分");
	            }
	         }
	         else
	         {
	            if(t2!=0)
	            {
	               result.append(source[t2]);
	               result.append("分");
	            }
	            else
	            {
	               return "";
	            }
	         }
	         return result.toString();
	      }
	      //下面处理的是整数部分
	      if(number.length() >4 )
	      {
	         System.out.println("分割错误！");
	         return null;
	      }

	      int len = number.length();
	      int temp[] ={0,0,0,0};
	      int j = 3;
	      for (int i = len; i >0; i--)
	      {
	        temp[j--] = Integer.parseInt(String.valueOf(number.charAt(i-1)));
	      }
	      if(temp[0] != 0)
	      {
	         result.append(source[temp[0]]);
	         result.append("仟");
	      }
	      if(temp[1] != 0) //百位不为零
	      {
	         result.append(source[temp[1]]);
	         result.append("佰");
	      }
	      else //百位为零，则应该判断下面两位
	      {
	         //十位和个位不全为0，并且千位不为0
	         if((temp[2]!=0 || temp[3]!=0) && temp[0]!=0)
	         {
	            result.append("零");
	         }
	      }

	      if(temp[2] != 0) //十位不为零
	      {
	         result.append(source[temp[2]]);
	         result.append("拾");
	      }
	      else //十位为零，则应该判断百位和个位的情况
	      {
	         if(temp[1]!=0 && temp[3]!=0)
	         {
	            result.append("零");
	         }
	      }
	      if(temp[3] != 0)
	      {
	         result.append(source[temp[3]]);
	      }

	     return result.toString();
	   }
}
