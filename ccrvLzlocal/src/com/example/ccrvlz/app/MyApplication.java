package com.example.ccrvlz.app;

import com.baidu.location.LocationClient;
import com.pcmgr.mld.detect.PcmgrAppMonitorTool;

import android.app.Application;

public class MyApplication extends Application {
	
	public LocationClient mLocationClient;

    @Override
    public void onCreate() {
        super.onCreate();
        
        mLocationClient = new LocationClient(this.getApplicationContext());
        
        PcmgrAppMonitorTool.init(this);
        PcmgrAppMonitorTool.enableActivityMemoryLeakDetect(true);
        PcmgrAppMonitorTool.enableActivityStartupTimeMonitor(true);
        PcmgrAppMonitorTool.startActivityMemoryLeakDetect();
        PcmgrAppMonitorTool.startActivityStartupTimeMonitor();
        
    }


}
