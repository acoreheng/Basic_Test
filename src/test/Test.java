package test;


/**
 * @author AcoreHeng
 * @version 创建时间：2012-9-27 上午10:35:46
 */
public class Test {
	public static void main(String[] args) {
		System.out.println(getLevel(90));;
		
	}
	public static String getLevel(int mark) {
		  for(GradeLevel gl: GradeLevel.values()) {
		    if (mark >= gl.min) {
		      return gl.desc;
		    }
		  }
		  return "出错了";
		}
	enum GradeLevel {
		A(90, "优秀"), B(80, "良好"), C(70, "中等"), D(60, "及格"), E(0, "不及格");
		private int min;
		private String desc;
		GradeLevel(int min, String desc) {
			this.min = min;
			this.desc = desc;
		}
	}
}
