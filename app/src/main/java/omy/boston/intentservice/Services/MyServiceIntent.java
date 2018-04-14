package omy.boston.intentservice.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class MyServiceIntent extends IntentService {
    public static final String PARAMETER_NAME = "TASK_COUNT";
    private static final int DEFAULT_TASKCOUNT = 5;
    private Handler nkiHandler = new Handler();

    public MyServiceIntent() {
        super("MyServiceIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int taskCount = intent.getIntExtra(PARAMETER_NAME, DEFAULT_TASKCOUNT);

            for (int i = 0; i < taskCount; i++){
                simulirajDugiTask();
                int progress = (int) ((i + 1) / (float)taskCount * 100);
                showProgress(progress);
            }
        }
    }

    private void simulirajDugiTask(){
        try {
            Thread.sleep(2980);
        }catch (InterruptedException e){
        }
    }

    private void showProgress(final int progress){
        nkiHandler.post(new Runnable() {
            @Override
            public void run() {
                String tekst = "Na " + progress + " % " + "završenog!"; //getString(R.string.resursa)
                Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
            }
        });
    } /**  <-- onDESTROY - ide obavezno, ne radi bez toga!*/

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

