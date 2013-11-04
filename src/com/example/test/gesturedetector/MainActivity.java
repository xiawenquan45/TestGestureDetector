package com.example.test.gesturedetector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
/**
 * MotionEvent 简单描述
 * 
 * ACTION_DOWN == 0;
 * ACTION_UP == 1;
 * ACTION_MOVE == 2;
 * ACTION_CANCEL == 3;
 * ACTION_OUTSIDE == 4;
 * 
 * @author Administrator
 *
 *执行结果分析：
 *1、单击按钮
 *	onTouch ==== 0 ，先触摸，之后把触摸事件交给GestureDetector.onDown()
	onDown ===> 0 ，把触摸事件传递进来并执行onDown()
	onTouch ==== 1 ，手势up，之后把up事件传给GestureDetector.onSingleTapUp()
	onSingleTapUp ===> 1

 *2、长按按钮
 *	onTouch ==== 0 ，先触摸，之后把触摸事件交给GestureDetector.onDown()
	onDown ===> 0  ，把触摸事件传递进来并执行onDown()
	onShowPress ===> 0，已经触摸但是没有松开或者拖动的状态，在短时间内执行
	onLongPress ===> 0 ，在长按的时间内执行
	onTouch ==== 1 ，手势up
 *
 *2、长按按钮并且移动
 *  onTouch ==== 0 ，先触摸，之后把触摸事件交给GestureDetector.onDown()
	onDown ===> 0，把触摸事件传递进来并执行onDown()
	onShowPress ===> 0，已经触摸但是没有松开或者拖动的状态，在短时间内执行
	onLongPress ===> 0 ，在长按的时间内执行
	onTouch ==== 2  ，长按之后并且有手势移动
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 2
	onTouch ==== 1 ，手势up

 *3、单击并且有手势移动
 *  onTouch ==== 0
	onDown ===> 0
	--------------------手势滑动部分--------------------------------------------
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 23.701355 ,,,,  velocityY ==== 28.814758
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 42.67502 ,,,,  velocityY ==== 46.561615
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 52.41571 ,,,,  velocityY ==== 49.723206
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 61.128998 ,,,,  velocityY ==== 44.299973
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 61.935196 ,,,,  velocityY ==== 36.965042
	onTouch ==== 2
	onScroll ===> e1.getAction() ==> 0，e2.getAction() ===> 2
	onScroll <<< velocityX === 47.09343 ,,,,  velocityY ==== 32.228973
	
	--------------------手势up部分--------------------------------------------
	onTouch ==== 1
	onFling ===> e1.getAction() ==> 0，e2.getAction() ===> 1
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
     * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，将捕捉到的MotionEvent交给GestureDetector
     * 来分析是否有合适的callback函数来处理用户的手势
     */ 
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onTouch ==== " + event.getAction());
		return mDetector.onTouchEvent(event);
	}
	
	/*
	 * 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDown ===> " + e.getAction());
		return false;
	}

	/*
	 * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
	 * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onShowPress ===> " + e.getAction());
	}
	
	/*
	 * 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
	 * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onSingleTapUp ===> " + e.getAction());
		return false;
	}
	
	/*
	 * 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
	 *  参数解释：   
     * e1：第1个ACTION_DOWN MotionEvent   
     * e2：最后一个ACTION_MOVE MotionEvent   
     * velocityX：X轴上的移动速度，像素/秒   
     * velocityY：Y轴上的移动速度，像素/秒   
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
	 *  用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
	 * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onLongPress ===> " + e.getAction());
	}

	/*
	 * 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
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
