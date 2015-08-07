package com.example.ccrvlz.utils;

public class NumUtil {
	public static void main(String[] args)  
    {  
          
        int ri = ((Double)(Math.random()*10)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*100)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*1000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*10000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*100000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*1000000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*10000000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*100000000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*1000000000)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
          
        ri = ((Double)(Math.random()*10000000000l)).intValue();  
        System.out.println(ri+" : "+intToZH(ri));  
    }  
      
    /** 
     * ������ת������������ 
     * @author Prosper 
     * 
     */  
    public static String intToZH(int i)  
    {  
        String[] zh = {"��", "һ", "��", "��", "��", "��", "��", "��", "��", "��"};    
        String[] unit = {"", "ʮ", "��", "ǧ", "��", "ʮ", "��", "ǧ", "��", "ʮ"};    
          
        String str = "";  
        StringBuffer sb = new StringBuffer(String.valueOf(i));  
        sb = sb.reverse();  
        int r = 0;  
        int l = 0;  
        for (int j = 0; j < sb.length(); j++)  
        {  
            /** 
             * ��ǰ���� 
             */  
            r = Integer.valueOf(sb.substring(j, j+1));  
              
            if (j != 0)  
                /** 
                 * ��һ������ 
                 */  
                l = Integer.valueOf(sb.substring(j-1, j));  
              
            if (j == 0)  
            {  
                if (r != 0 || sb.length() == 1)  
                    str = zh[r];  
                continue;  
            }  
              
            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9)  
            {  
                if (r != 0)  
                    str = zh[r] + unit[j] + str;  
                else if (l != 0)  
                    str = zh[r] + str;  
                continue;  
            }  
              
            if (j == 4 || j == 8)  
            {  
                str =  unit[j] + str;  
                if ((l != 0 && r == 0) || r != 0)  
                    str = zh[r] + str;  
                continue;  
            }  
        }  
        return str;  
    }  
}
