import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Textnetwork {

	//ÍøÂç¼ì²â(ping)
	public static boolean isConnect() {
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			process = runtime.exec("ping " + "www.baidu.com -n 1 -l 8 -w 200");
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isr.close();
			br.close();

			if (null != sb && !sb.toString().equals("")) {
				String logString = "";
				if (sb.toString().indexOf("TTL") > 0) {
					// ÍøÂç³©Í¨
					connect = true;
				} else {
					// ÍøÂç²»³©Í¨
					connect = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connect;
	}
	
	//ÍøÂç×´Ì¬ÅĞ¶Ï
	public static boolean isOpen(){
		boolean open = true;
		if(!isConnect()){
			if(!isConnect()){
				open = false;
			}
		}
		return open;
	}
	
//	//²âÊÔ
//	public static void main(String[] args) {
//		if(isOpen()){
//			System.out.println("ÍøÂç³©Í¨!");
//		}else{
//			System.out.println("ÍøÂçÒì³£!");
//		}
//	}

}