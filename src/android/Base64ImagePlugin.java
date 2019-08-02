package com.wandong.cordova.base64img;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Base64ImagePlugin extends CordovaPlugin {

    public static  final String SAVE = "save";

    public static final String TAG ="Base64ImagePlugin";
    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {
        if(SAVE.equals(action)){
            String base64DataStr = args.getString(0);
            // 1.去掉base64中的前缀
            String base64Str = base64DataStr.substring(base64DataStr.indexOf(",") + 1, base64DataStr.length());
            // 获取手机相册的路径地址
            String galleryPath= Environment.getExternalStorageDirectory()
                    + File.separator + Environment.DIRECTORY_DCIM
                    +File.separator+"Camera"+File.separator;
            //创建文件来保存，第二个参数是文件名称，可以根据自己来命名
            File file = new File(galleryPath, System.currentTimeMillis() + ".png");
            String fileName = file.toString();
            // 3. 解析保存图片
            byte[] data = Base64.decode(base64Str, Base64.DEFAULT);

            for (int i = 0; i < data.length; i++) {
                if (data[i] < 0) {
                    data[i] += 256;
                }
            }
            OutputStream os = null;
            try {
                os = new FileOutputStream(fileName);
                os.write(data);
                os.flush();
                os.close();
                JSONObject returnObj = new JSONObject();
                returnObj.put("msg","success!");
                returnObj.put("url",fileName);
                callbackContext.success(returnObj);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                JSONObject returnObj = new JSONObject();
                returnObj.put("msg","error:"+e.getMessage());
                callbackContext.error(returnObj);
                return false;
            } finally {
                //通知相册更新
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                this.cordova.getContext().sendBroadcast(intent);

                //ToastUtils.show(mContext, "图片已保存在相册中");
            }

        }
        return false;
    }

}
