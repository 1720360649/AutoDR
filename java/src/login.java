import java.io.File;
import java.io.IOException;

public class login {
	private static String Files = new File("").getAbsolutePath();//�ļ����·��
	public boolean tologin(){
		
		
		String path = Files + "//resources//login.exe";
		Runtime rt =Runtime.getRuntime();
		try {
			
			rt.exec(path);
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	
//	//����
//	public static void main(String[] args) {
//		login l = new login();
//		boolean temp = l.tologin();
//		System.out.println(temp);
//	}
	
}
