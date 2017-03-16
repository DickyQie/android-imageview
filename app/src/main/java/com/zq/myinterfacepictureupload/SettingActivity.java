package com.zq.myinterfacepictureupload;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zq.myinterfacepictureupload.util.ACache;
import com.zq.myinterfacepictureupload.util.UtilFileDB;
import com.zq.myinterfacepictureupload.util.UtilImags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;

/**
 * Created by zq on 2016/6/11.
 */

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    String URL = "url";
    TextView homeTopName;
    ZQRoundOvalImageView zqRoundOvalImageView;
    PopupWindow pop;
    LinearLayout ll_popup;
    Intent intent;
    String urlsf = "";
    int img = 1;
    ACache aCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);
        initView();
    }

    private void initView() {
        homeTopName = (TextView) findViewById(R.id.home_top_name);
        homeTopName.setText("设置");
        aCache = ACache.get(SettingActivity.this);
        zqRoundOvalImageView = (ZQRoundOvalImageView) findViewById(R.id.my_setting_txlehs);
        zqRoundOvalImageView.setOnClickListener(this);
        findViewById(R.id.home_tour_close).setOnClickListener(this);
        initData();
    }

    private void initData() {
        if (UtilFileDB.SELETEFile(aCache, "stscimage") != null) {
            if (aCache.getAsBitmap("myimg") == null) {
                getImage(UtilFileDB.LOGINIMGURL(aCache));
            } else {
                zqRoundOvalImageView.setImageBitmap(aCache.getAsBitmap("myimg"));
            }
        }
    }

    public void getImage(String url) {
        OkHttpUtils.get().url(url).tag(this).build().connTimeOut(20000)
                .readTimeOut(20000).writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        zqRoundOvalImageView.setImageBitmap(bitmap);
                        aCache.put("myimg", bitmap);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_setting_txlehs:
                showPopupWindow();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        SettingActivity.this, R.anim.activity_translate_in));
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.home_tour_close:
                intent = new Intent();
                intent.putExtra("urlimg", urlsf);
                setResult(img, intent);
                finish();
                break;

        }
    }

    /****
     * 头像提示框
     */
    public void showPopupWindow() {
        pop = new PopupWindow(SettingActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows,
                null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, 1);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent picture = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 2);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK
                && null != data) {
            String sdState = Environment.getExternalStorageState();
            if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为图片格式
            Bitmap bmp = (Bitmap) bundle.get("data");
            FileOutputStream fout = null;
            String filename = null;
            try {
                filename = UtilImags.SHOWFILEURL(SettingActivity.this) + "/" + name;
            } catch (IOException e) {
            }
            try {
                fout = new FileOutputStream(filename);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fout);
            } catch (FileNotFoundException e) {
                showToastShort("上传失败");
            } finally {
                try {
                    fout.flush();
                    fout.close();
                } catch (IOException e) {
                    showToastShort("上传失败");
                }
            }
            zqRoundOvalImageView.setImageBitmap(bmp);
            staffFileupload(new File(filename));
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = this.getContentResolver().query(selectedImage,
                        filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                Bitmap bmp = BitmapFactory.decodeFile(picturePath);
                // 获取图片并显示
                zqRoundOvalImageView.setImageBitmap(bmp);
                saveBitmapFile(UtilImags.compressScale(bmp), UtilImags.SHOWFILEURL(SettingActivity.this) + "/stscname.jpg");
                staffFileupload(new File(UtilImags.SHOWFILEURL(SettingActivity.this) + "/stscname.jpg"));
            } catch (Exception e) {
                showToastShort("上传失败");
            }
        }
    }

    public void saveBitmapFile(Bitmap bitmap, String path) {
        File file = new File(path);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void staffFileupload(File file) {
        if (false) {
            showToastShort("网络未连接");
            return;
        }
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, URL, MYUPDATEIMG(file),
                new RequestCallBack<String>() {

                    @Override
                    public void onFailure(HttpException arg0, String arg1) {

                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        JSONObject jsonobj;
                        try {
                            jsonobj = new JSONObject(arg0.result.toString());
                            String errno = jsonobj.getString("errno");
                            String error = jsonobj.getString("error");
                            if (errno.equals("0") && error.equals("success")) {
                                JSONArray jsonarray = jsonobj.getJSONArray("data");
                                JSONObject jsonobjq = jsonarray.getJSONObject(0);
                                urlsf = jsonobjq.getString("url");
                                UtilFileDB.ADDFile(aCache, "stscimage", urlsf);
                                img = 3;
                                showToastShort("头像修改成功");

                            } else {
                                showToastShort("头像修改失败");
                            }
                        } catch (JSONException e) {
                            return;
                        }
                    }

                });

    }

    /***
     * 修改头像
     *
     * @return
     */
    public static final RequestParams MYUPDATEIMG(File file) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("c", "profile");
        params.addBodyParameter("a", "setavatar");
        params.addBodyParameter("uid", "");
        params.addBodyParameter("username", "");
        if (file != null) {
            params.addBodyParameter("filedata", file);
        }
        return params;
    }

    private void showToastShort(String string) {
        Toast.makeText(SettingActivity.this, string, Toast.LENGTH_LONG).show();
    }
}
