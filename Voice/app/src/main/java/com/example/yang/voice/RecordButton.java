package com.example.yang.voice;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


/**
 * Created by yang on 2017/9/1.
 */

public class RecordButton extends android.support.v7.widget.AppCompatButton {
    public RecordButton(Context context) {
        super(context);
        init();

    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public RecordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        volumeHandler=new ShowVolumeHandler();
    }
    private long startTime;
    private Dialog recordIndicator;
    private static ImageView view;
    private static int[] res = { R.drawable.mic_2, R.drawable.mic_3, R.drawable.mic_4, R.drawable.mic_5 };
    private static final int MIN_INTERVAL_TIME = 2000;
    private OnFinishedRecordListener finishedListener;
    private MediaRecorder recorder;
    private ObtainDecibelThread thread;
    private Handler volumeHandler;

    public void setSavePath(String Path){
        mFileName=Path;
    }
    private String mFileName=null;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mFileName == null)
            return false;
        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                initDialogAndStartRecord();
                break;
            case MotionEvent.ACTION_UP:
                finishRecord();
                break;
            case MotionEvent.ACTION_CANCEL:
                cancelRecord();
                break;
        }
        return true;
    }

    private void cancelRecord() {
        stopRecording();
        recordIndicator.dismiss();

        Toast.makeText(getContext(),"取消录音", Toast.LENGTH_LONG).show();
        File file=new File(mFileName);
        file.delete();
    }

    private void finishRecord() {
        stopRecording();
        recordIndicator.dismiss();

        long intervalTime=System.currentTimeMillis()-startTime;
        if(intervalTime<MIN_INTERVAL_TIME)
        {
            Toast.makeText(getContext(), "时间太短！", Toast.LENGTH_SHORT).show();
            File file=new File(mFileName);
            file.delete();
            return;
        }
        if (finishedListener != null)
            finishedListener.onFinishedRecord(mFileName);
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(mFileName);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();
        thread = new ObtainDecibelThread();
        thread.start();
    }

    private void stopRecording() {
        if (thread != null) {
            thread.exit();
            thread = null;
        }
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }

    private void initDialogAndStartRecord() {
        startTime = System.currentTimeMillis();
        recordIndicator=new Dialog(getContext(),R.style.dialog_style);
        view=new ImageView(getContext());
        view.setImageResource(R.drawable.mic_2);
        recordIndicator.setContentView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        recordIndicator.setOnDismissListener(onDismiss);
        WindowManager.LayoutParams lp = recordIndicator.getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        startRecording();
        recordIndicator.show();
    }


    private class ObtainDecibelThread extends Thread {

        private volatile boolean running = true;

        public void exit() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (recorder == null || !running) {
                    break;
                }
                int x = recorder.getMaxAmplitude();
                if (x != 0) {
                    int f = (int) (10 * Math.log(x) / Math.log(10));
                    if (f < 26)
                        volumeHandler.sendEmptyMessage(0);
                    else if (f < 32)
                        volumeHandler.sendEmptyMessage(1);
                    else if (f < 38)
                        volumeHandler.sendEmptyMessage(2);
                    else
                        volumeHandler.sendEmptyMessage(3);

                }

            }
        }

    }

    private OnDismissListener onDismiss = new OnDismissListener() {

        @Override
        public void onDismiss(DialogInterface dialog) {
            stopRecording();
        }
    };
    static class ShowVolumeHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            view.setImageResource(res[msg.what]);
        }
    }
    public interface OnFinishedRecordListener {
        public void onFinishedRecord(String audioPath);
    }
    public void setOnFinishedRecordListener(OnFinishedRecordListener listener) {
        finishedListener = listener;
    }

}
