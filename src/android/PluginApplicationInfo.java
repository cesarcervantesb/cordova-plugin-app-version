package com.ccervantesb.appinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * This class echoes a string called from JavaScript.
 */
public class PluginApplicationInfo extends CordovaPlugin {

    private CallbackContext callbackContext;
    private JSONObject resultJSON;
    private PackageManager packageManager;
    private ApplicationInfo applicationInfo;
    private PackageInfo packageInfo;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action.equals("getApplicationInfo")) {
            this.getApplicationInfo(callbackContext);
            return true;
        }
        return false;
    }

    private void getApplicationInfo(CallbackContext callbackContext) {
        this.resultJSON = new JSONObject();
        this.packageManager = (PackageManager) cordova.getActivity().getPackageManager();
        try {
            this.applicationInfo = (ApplicationInfo) this.packageManager.getApplicationInfo(cordova.getActivity().getPackageName(), 0);
            this.packageInfo = (PackageInfo) this.packageManager.getPackageInfo(cordova.getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.handleError("PackageManager_NameNotFoundException", "Package manager name not found.", callbackContext);
        }
        try {
            this.resultJSON.put("appName", this.packageManager.getApplicationLabel(this.applicationInfo));
            this.resultJSON.put("packageName", cordova.getActivity().getPackageName());
            this.resultJSON.put("versionName", this.packageInfo.versionName);
            this.resultJSON.put("versionCode", this.packageInfo.versionCode);
        }
        catch(JSONException e) {
            e.printStackTrace();
            Log.e("PluginApplicationInfo", "Error creating JSON Object", e);
            this.handleError("Error creating JSON Object", "Error: " + e.getMessage(), callbackContext);
        }
        PluginResult result = new PluginResult(PluginResult.Status.OK, this.resultJSON);
        callbackContext.sendPluginResult(result);
    }

    private void handleError(String title, String message, CallbackContext callbackContext) {
        try {
            this.resultJSON.put("error", true);
            this.resultJSON.put("title", title);
            this.resultJSON.put("message", message);
            PluginResult result = new PluginResult(PluginResult.Status.ERROR, this.resultJSON);
            callbackContext.sendPluginResult(result);
        } catch (JSONException e) {
            Log.e("PluginApplicationInfo", "Error creating JSON Object", e);
        }
    }
}
