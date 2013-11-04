package com.example.test.gesturedetector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
/**
 * MotionEvent ������
 * 
 * ACTION_DOWN == 0;
 * ACTION_UP == 1;
 * ACTION_MOVE == 2;
 * ACTION_CANCEL == 3;
 * ACTION_OUTSIDE == 4;
 * 
 * @author Administrator
 *
 *ִ�н��������
 *1��������ť
 *	onTouch ==== 0 ���ȴ�����֮��Ѵ����¼�����GestureDetector.onDown()
	onDown ===> 0 ���Ѵ����¼����ݽ�����ִ��onDown()
	onTouch ==== 1 ������up��֮���up�¼�����GestureDetector.onSingleTapUp()
	onSingleTapUp ===> 1

 *2��������ť
 *	onTouch ==== 0 ���ȴ�����֮��Ѵ����¼�����GestureDetector.onDown()
	onDown ===> 0  ���Ѵ����¼����ݽ�����ִ��onDown()
	onShowPress ===> 0���Ѿ���������û���ɿ������϶���״̬���ڶ�ʱ����ִ��
	onLongPress ===> 0 ���ڳ�����ʱ����ִ��
	onTouch ==== 1 ������up
 *
 *2��������ť�����ƶ�
 *  onTouch ==== 0 ���ȴ�����֮��Ѵ����¼�����GestureDetector.onDown()
	onDown ===> 0���Ѵ����¼����ݽ�����ִ��onDown()
	onShowPress ===> 0���Ѿ���������û���ɿ������϶���״̬���ڶ�ʱ����ִ��
	onLongPress ===> 0 ���ڳ�����ʱ����ִ��
	onTouch ==== 2  ������֮�����������ƶ�
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 1 ������up

 *3�����������������ƶ�
 *  onTouch ==== 0
	onDown ===> 0
	--------------------���ƻ�������--------------------------------------------
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 23.701355 ,,,,  velocityY ==== 28.814758
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 42.67502 ,,,,  velocityY ==== 46.561615
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 52.41571 ,,,,  velocityY ==== 49.723206
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 61.128998 ,,,,  velocityY ==== 44.299973
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 61.935196 ,,,,  velocityY ==== 36.965042
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0��e2.getAction() ===> 2
	onScroll <<< velocityX === 47.09343 ,,,,  velocityY ==== 32.228973
	
	--------------------����up����--------------------------------------------
	onTouch ==== 1
	onFling ===> e1.getAction() ==> 0��e2.getAction() ===> 1
	onFling <<< velocityX === -3804.2278 ,,,,  velocityY ==== -2074.0125

 */
public class MainActivity extends Activity implements GestureDetector.OnGestureListener,View.OnTouchListener{

	private Button mButton;
	private GestureDetector mDetector;
	private final String TAG = "MainActivity_GestureDetector";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDetector = new GestureDetector(this);
		mButton = (Button) findViewById(R.id.tv);
		mButton.setOnTouchListener(this);
		mButton.setClickable(true);
		mButton.setFocusable(true);
		mButton.setLongClickable(true);
		mDetector.setIsLongpressEnabled(true);
		
	}
	
	/*
     * ��onTouch()�����У����ǵ���GestureDetector��onTouchEvent()����������׽����MotionEvent����GestureDetector
     * �������Ƿ��к��ʵ�callback�����������û�������
     */ 
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onTouch ==== " + event.getAction());
		return mDetector.onTouchEvent(event);
	}
	
	/*
	 * �û��ᴥ����������1��MotionEvent ACTION_DOWN����
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDown ===> " + e.getAction());
		return false;
	}

	/*
	 * �û��ᴥ����������δ�ɿ����϶�����һ��1��MotionEvent ACTION_DOWN����
	 * ע���onDown()������ǿ������û���ɿ������϶���״̬
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onShowPress ===> " + e.getAction());
	}
	
	/*
	 * �û����ᴥ���������ɿ�����һ��1��MotionEvent ACTION_UP����
	 * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onSingleTapUp ===> " + e.getAction());
		return false;
	}
	
	/*
	 * �û����´������������ƶ����ɿ�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE, 1��ACTION_UP����
	 *  �������ͣ�   
     * e1����1��ACTION_DOWN MotionEvent   
     * e2�����һ��ACTION_MOVE MotionEvent   
     * velocityX��X���ϵ��ƶ��ٶȣ�����/��   
     * velocityY��Y���ϵ��ƶ��ٶȣ�����/��   
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onFling ===> e1.getAction() ==> " + e1.getAction() + "e2.getAction() ===> " + e2.getAction());
		Log.d(TAG, "onFling <<< velocityX === " + velocityX + " ,,,,  velocityY ==== " + velocityY);
		return false;
	}
	
	/*
	 *  �û��������������ɶ��MotionEvent ACTION_DOWN����
	 * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onLongPress ===> " + e.getAction());
	}

	/*
	 * �û����´����������϶�����1��MotionEvent ACTION_DOWN, ���ACTION_MOVE����
	 * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onScroll ===> e1.getAction() ==> " + e1.getAction() + "e2.getAction() ===> " + e2.getAction());
		Log.d(TAG, "onScroll <<< velocityX === " + distanceX + " ,,,,  velocityY ==== " + distanceY);
		return false;
	}


	
}
