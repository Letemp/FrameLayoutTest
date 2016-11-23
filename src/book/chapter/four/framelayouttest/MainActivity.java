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

	//����һ�����飬���ڴ洢����TextView��ID
	private int[]textIds=new int[]{R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5};
	//����һ�����飬���ڴ洢������ɫ
	private int[]Colors=new int[]{Color.RED,Color.MAGENTA,Color.GREEN,Color.YELLOW,Color.BLUE};
	//����һ�����飬����Ԫ��ΪTexyView������ĳ�����ǰ����������
	private TextView[]views=new TextView[textIds.length];
	private Handler mHandler;
	private int current=0;//��¼���ĸ���ɫ��ʼ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//ѭ������ID���飬����ID��ȡ�ؼ���Ȼ�󽫿ؼ�����TextView�����е�Ԫ��
		for(int i=0;i<textIds.length;i++){
			views[i]=(TextView)this.findViewById(textIds[i]);
		}
		//����Handler�������ڽ�����Ϣ������
		mHandler=new Handler(){
			//������Ϣ�ķ���
			public void handleMessage(Message msg){
				//�ж���Ϣ�Ƿ�Ϊָ������Ϣ
				if(msg.what==0x11){
					//ѭ������TextView�ı�����ɫ
					for(int i=0;i<views.length;i++){
						views[i].setBackgroundColor(Colors[(i+current)%Colors.length]);
					}
					current=(current+1)%Colors.length;//ʹ��ʼ��ɫ����ż�1������Ѿ������һ������ӵ�һ����ʼ
				}
			}
			};
			Timer timer=new Timer();//����ʱ�䶨ʱ��
			timer.schedule(new TimerTask(){
				public void run(){
					mHandler.sendEmptyMessage(0x11);
				}
			},0,3000);//������ʱ����ÿ��3000ms����һ����Ϣ
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
