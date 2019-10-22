import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Start extends JFrame {
	private static final long serialVersionUID = 1L;
	//常量定义
	private final static JFrame jframe =  new JFrame("欧兔DR");//定义主容器
	Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
	Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
	final int screenWidth = screenSize.width; // 获取屏幕的宽
	final int screenHeight = screenSize.height;//获取屏幕的高
	private boolean run = false;//运行状态
	private static SystemTray tray = SystemTray.getSystemTray();//图标实例
	private static TrayIcon trayIcon = null;//托盘图标
	private static String Files = new File("").getAbsolutePath();//文件相对路径
	private static  String trayImgpath = "//img//error.png";//托盘图标路径
	//主程序定时器
	private static Timer maintimer = null;
	//图标文字
	private static String icontxt = "欧兔DR";
	//开始按钮
	private final JButton start = new JButton("");
	//状态jlable
	private final JLabel state = new JLabel("状态:");
	//状态文字
	private final JLabel statetxt = new JLabel("");
	//版权标识
	private final JLabel txt = new JLabel("@[十九]-统一老坛   群: 115767597");
	
	//容器
	public void mainwindow(){
		
		jframe.setResizable(false); // 窗体不可自由改变大小(不可最大化)
		jframe.setLayout(null); // 将布局设为无
		//设置容器图标
		File src = new File(Files + "//img//icon.png");		
		Image image;
		try {
			image = ImageIO.read(src);
			jframe.setIconImage(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//开始按钮设置
		start.setText("开启欧兔");
		start.setFont(new Font("黑体", Font.PLAIN, 20));
		start.setBounds(75, 80, 130, 50);
		
		//状态jlable 设置
		state.setFont(new Font("黑体", Font.PLAIN, 20));
		state.setBounds(50, 20, 50, 50);
		
		//状态文字设置
		statetxt.setText("未开启");
		statetxt.setFont(new Font("黑体", Font.PLAIN, 20));
		statetxt.setForeground(Color.red);
		statetxt.setBounds(100, 20, 200, 50);
		
		//版权
		txt.setFont(new Font("黑体", Font.PLAIN, 13));
		txt.setForeground(Color.BLACK);
		txt.setBounds(40, 140, 250, 30);
		
		//向主容器注入组件
		jframe.add(txt);
		jframe.add(start);
		jframe.add(state);
		jframe.add(statetxt);
		
		
		
		//注入开始按钮监听事件
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startListen();
			}
		});
		
		//注入主窗体监听
		jframe.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				jframe.setVisible(false);
				Start.miniTray();
			}
			
		});
		
		
		
		jframe.setLocation((screenWidth - 300) / 2, (screenHeight-200)/2); // 设置容器启动位置
		jframe.setSize(300, 200); // 设置容器大小
		jframe.setVisible(true); // 将容器状态设置为可见
	}
	
	//开始按钮函数
	public void startListen(){
		if(run){
			start.setText("开启欧兔");
			statetxt.setText("未开启");
			statetxt.setForeground(Color.red);
			statetxt.setBounds(130, 20, 200, 50);
			maintimer.cancel();
			trayImgpath = "//img//error.png";
			run = false;
		}else{
			startRun();
			start.setText("关闭欧兔");
			statetxt.setText("正在实时监测");
			statetxt.setForeground(Color.black);
			statetxt.setBounds(100, 20, 200, 50);
			trayImgpath = "//img//icon.gif";
			run = true;
		}
	}
	
	//最小化到托盘
	private static void miniTray() {//窗口最小化到任务栏托盘

		ImageIcon trayImg = new ImageIcon(Files+trayImgpath);//托盘图标
		PopupMenu pop = new PopupMenu();//增加托盘右击菜单
		MenuItem show = new MenuItem("Back");
		MenuItem exit = new MenuItem("Quit");
		
		
		
		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tray.remove(trayIcon);
				trayIcon = null;
				jframe.setVisible(true);
				jframe.setExtendedState(JFrame.NORMAL);
				jframe.toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // 按下退出键

		public void actionPerformed(ActionEvent e) {
			tray.remove(trayIcon);
			System.exit(0);
		}
		
		});

		pop.add(show);
		pop.add(exit);
		
		trayIcon = new TrayIcon(trayImg.getImage(), icontxt, pop);

		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				tray.remove(trayIcon); // 移去托盘图标\
				trayIcon = null;
				jframe.setVisible(true);
				jframe.setExtendedState(JFrame.NORMAL); // 还原窗口
				jframe.toFront();
			}
		}
		
		});
		
		try {
			
			tray.add(trayIcon);

		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		
	}
	
	//运行主函数
	public void startRun(){
		maintimer = new Timer(true);
		maintimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(Textnetwork.isOpen()){
					
				}else{
					try {
//						//托盘提示
//						if(trayIcon != null){
//							trayIcon.displayMessage(null,"欧兔正在重新连接",TrayIcon.MessageType.ERROR);
//						}
						
						statetxt.setText("欧兔正在重新连接");
						statetxt.setForeground(Color.red);
						statetxt.setBounds(100, 20, 200, 50);
						new login().tologin();
						Thread.sleep(800);
						statetxt.setText("正在实时监测");
						statetxt.setForeground(Color.black);
						statetxt.setBounds(100, 20, 200, 50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},0, 100);
	}
	
	public static void main(String[] args) {
		Start s =new Start();
		s.mainwindow();
	}
}
