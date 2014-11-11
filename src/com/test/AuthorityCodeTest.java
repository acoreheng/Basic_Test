package com.test;
public class AuthorityCodeTest {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
          
        //用户具有的总权限  
        int userPurview ;  
        //用户拥有角色1和角色4的权限  
        int power =(int)Math.pow(2, 0)+(int)Math.pow(2, 1)+(int)Math.pow(2, 2)+(int)Math.pow(2, 3);//15  
        int power4 =(int)Math.pow(2, 3)+(int)Math.pow(2,7);//136  
        userPurview =totolPower(power,power4);//143 ----10001111  
  
        int[]optPurview = {2,1};  
        //这里假设用户要执行2号添加A和7号操作查看B  
        boolean rs = checkPower(userPurview, optPurview);  
        System.out.println(rs); 
        
        System.out.println(checkPower(10, 4));
    }  
      
    private static int totolPower(int i, int j) {  
        return i|j;
          
    }  
  
    //optPurview是操作要求的权限码整形数组，还没有经过权  
    private static boolean checkPower(int userPurview, int[] optPurview) {  
          
        int purviewValue =0;  
        for (int i = 0; i < optPurview.length; i++) {  
            int j = optPurview[i];  
            purviewValue+= (int)Math.pow(2, j);  
        }  
         System.out.println("操作要求码："+purviewValue);//132 ---10000100  
         return (userPurview&purviewValue) == purviewValue;  
  
    }  
    
    /**
	 * @param userPurview	用户具有的总权限
	 * @param optPurview	一个操作要求的权限为一个整数
	 * @return
	 */
	public static boolean checkPower(int userPurview, int optPurview){
		int purviewValue= (int)Math.pow(2, optPurview);
		return (userPurview&purviewValue)==purviewValue;
	}
}  