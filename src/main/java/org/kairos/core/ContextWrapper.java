package org.kairos.core;


public class ContextWrapper extends Context {

    protected Context mBase;
    protected Intent intent;
    
    public ContextWrapper(Context base) {
        this.mBase = base;
    }
    
    public Context getBaseContext() {
        return mBase;
    }     
    
    @Override
    public void startActivity(Class<? extends Activity> activity) {
        mBase.startActivity(activity);
    }

    @Override
    public void startActivity(Intent intent) {
        mBase.startActivity(intent);
    }

    public Intent getIntent() {
        return intent;
    }

    @Override
    public void onBackPressed() {
        mBase.onBackPressed();
    }


}
