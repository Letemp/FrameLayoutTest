package book.chapter.four.framelayouttest;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	//定义一个数组，用于存储所有TextView的ID
	private int[]textIds=new int[]{R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5};
	//定义一个数组，用于存储五种颜色
	private int[]Colors=new int[]{Color.RED,Color.MAGENTA,Color.GREEN,Color.YELLOW,Color.BLUE};
	//定义一个数组，数组元素为TexyView，数组的长度由前面的数组决定
	private TextView[]views=new TextView[textIds.length];
	private Handler mHandler;
	private int current=0;//记录从哪个颜色开始
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//循环遍历ID数组，根据ID获取控件，然后将控件赋给TextView数组中的元素
		for(int i=0;i<textIds.length;i++){
			views[i]=(TextView)this.findViewById(textIds[i]);
		}
		//创建Handler对象，用于接收消息并处理
		mHandler=new Handler(){
			//处理消息的方法
			public void handleMessage(Message msg){
				//判断消息是否为指定的消息
				if(msg.what==0x11){
					//循环设置TextView的背景颜色
					for(int i=0;i<views.length;i++){
						views[i].setBackgroundColor(Colors[(i+current)%Colors.length]);
					}
					current=(current+1)%Colors.length;//使开始颜色的序号加1，如果已经是最后一个，则从第一个开始
				}
			}
			};
			Timer timer=new Timer();//创建时间定时器
			timer.schedule(new TimerTask(){
				public void run(){
					mHandler.sendEmptyMessage(0x11);
				}
			},0,3000);//开启定时任务，每隔3000ms发送一次消息
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
