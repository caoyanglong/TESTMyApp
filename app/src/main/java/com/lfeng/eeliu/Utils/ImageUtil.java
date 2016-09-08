package com.lfeng.eeliu.Utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;

import com.lfeng.eeliu.base.BaseFragmentActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 对图片的简单修饰， 包括（将bitmap转换成圆形 图片， 图片文件 转base64 字符串 ，图片压缩功能  bitmap 保存在本地等功能 等）
 * @author 曹阳龙
 * 
 * 2015-9-12
 */
public class ImageUtil {
    /**
	 * 可以将 bitmap 图片 修饰成圆形的图片
	 * @param bmp     位图
	 * @param radius  半径
	 * @return
	 */
	 public static Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {  
	        Bitmap scaledSrcBmp;  
	        int diameter = radius * 2;  
	  
	        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片  
	        int bmpWidth = bmp.getWidth();  
	        int bmpHeight = bmp.getHeight();  
	        int squareWidth = 0, squareHeight = 0;  
	        int x = 0, y = 0;  
	        Bitmap squareBitmap;  
	        if (bmpHeight > bmpWidth) {// 高大于宽  
	            squareWidth = squareHeight = bmpWidth;  
	            x = 0;  
	            y = (bmpHeight - bmpWidth) / 2;  
	            // 截取正方形图片  
	            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
	                    squareHeight);  
	        } else if (bmpHeight < bmpWidth) {// 宽大于高  
	            squareWidth = squareHeight = bmpHeight;  
	            x = (bmpWidth - bmpHeight) / 2;  
	            y = 0;  
	            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
	                    squareHeight);  
	        } else {  
	            squareBitmap = bmp;  
	        }  
	  
	        if (squareBitmap.getWidth() != diameter  
	                || squareBitmap.getHeight() != diameter) {  
	            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,  
	                    diameter, true);  
	  
	        } else {  
	            scaledSrcBmp = squareBitmap;  
	        }  
	        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),  
	                scaledSrcBmp.getHeight(), Config.ARGB_8888);  
	        Canvas canvas = new Canvas(output);  
	  
	        Paint paint = new Paint();  
	        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),  
	                scaledSrcBmp.getHeight());  
	  
	        paint.setAntiAlias(true);  
	        paint.setFilterBitmap(true);  
	        paint.setDither(true);  
	        canvas.drawARGB(0, 0, 0, 0);  
	        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,  
	                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,  
	                paint);  
	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);  
	        // bitmap回收(recycle导致在布局文件XML看不到效果)  
	        // bmp.recycle();  
	        // squareBitmap.recycle();  
	        // scaledSrcBmp.recycle();  
	        bmp = null;  
	        squareBitmap = null;  
	        scaledSrcBmp = null;  
	        return output;  
	    } 
	 
	 /**
      * 转换图片成圆形
      * @param bitmap 传入Bitmap对象
      * @return
      */
     public Bitmap toRoundBitmap(Bitmap bitmap) {
             int width = bitmap.getWidth();
             int height = bitmap.getHeight();
             float roundPx;
             float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
             if (width <= height) {
                     roundPx = width / 2;
                     top = 0;
                     bottom = width;
                     left = 0;
                     right = width;
                     height = width;
                     dst_left = 0;
                     dst_top = 0;
                     dst_right = width;
                     dst_bottom = width;
             } else {
                     roundPx = height / 2;
                     float clip = (width - height) / 2;
                     left = clip;
                     right = width - clip;
                     top = 0;
                     bottom = height;
                     width = height;
                     dst_left = 0;
                     dst_top = 0;
                     dst_right = height;
                     dst_bottom = height;
             }
               
             Bitmap output = Bitmap.createBitmap(width,
                             height, Config.ARGB_8888);
             Canvas canvas = new Canvas(output);
               
             final int color = 0xff424242;
             final Paint paint = new Paint();
             final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
             final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
             final RectF rectF = new RectF(dst);

             paint.setAntiAlias(true);
               
             canvas.drawARGB(0, 0, 0, 0);
             paint.setColor(color);
             canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

             paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
             canvas.drawBitmap(bitmap, src, dst, paint);
             return output;
     }
     
     /**
      * 将图片的四角圆化
      * @param bitmap 原图
      * @param roundPixels 圆滑率
      * @param half 是否截取半截
      * @return
      */
     public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels,int half)
     {
         int width=bitmap.getWidth();
         int height=bitmap.getHeight();
          
         //创建一个和原始图片一样大小位图
         Bitmap roundConcerImage = Bitmap.createBitmap(width,height, Config.ARGB_8888);
         //创建带有位图roundConcerImage的画布
         Canvas canvas = new Canvas(roundConcerImage);
         //创建画笔
         Paint paint = new Paint();
         //创建一个和原始图片一样大小的矩形
         Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
         RectF rectF = new RectF(rect);
         // 去锯齿
         paint.setAntiAlias(true);
          
          
         //画一个和原始图片一样大小的圆角矩形
         canvas.drawRoundRect(rectF, roundPixels, roundPixels , paint);
         //设置相交模式
         paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
         //把图片画到矩形去
         canvas.drawBitmap(bitmap, null, rect, paint);
         switch(half){
//             case HalfType.LEFT:
//                 return Bitmap.createBitmap(roundConcerImage, 0, 0, width/2, height);
//             case HalfType.RIGHT:
//                 return Bitmap.createBitmap(roundConcerImage, width/2, 0, width/2, height);
//             case HalfType.TOP:
//                 return Bitmap.createBitmap(roundConcerImage, 0, 0, width, height/2);
//             case HalfType.BOTTOM:
//                 return Bitmap.createBitmap(roundConcerImage, 0, height/2, width, height/2);
//             case HalfType.NONE:
//                 return roundConcerImage;
//             default:
//                 return roundConcerImage;
         }
		return roundConcerImage;
     }
	 /**
	  * 传递来一个路径 将文件转换未  base64的字符
	  * @param path              文件路径   sd 文件  如图片
	  * @return                  base64 的字符
	  * @throws Exception
	  */
     public static String encodeBase64File(String path) throws Exception {
 		File  file = new File(path);
 		FileInputStream inputFile = new FileInputStream(file);
 		byte[] data = new byte[(int)file.length()];
 		inputFile.read(data);
 		inputFile.close();
 		return Base64.encodeToString(data,Base64.DEFAULT);
 		}
     /**
      * bitmap 转 base64的字符串
      * @param data
      * @return
      * @throws Exception
      */
     @SuppressLint("NewApi")
	public static String encodeBase64File(byte[] data) throws Exception {
    	 return Base64.encodeToString(data, Base64.DEFAULT);
  		}
     /**
      * 将bitmap 转化为  字节数组
      * @param bm         bitmap 位图
      * @return           
      */
     public static byte[] Bitmap2Bytes(Bitmap bm) {
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	 bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
    	 return baos.toByteArray();
     }
     private Options getBitmapOption(int inSampleSize){
         System.gc();
         Options options = new Options();
         options.inPurgeable = true;
         options.inSampleSize = inSampleSize;
         return options;
 }


     /**
      * 图片压缩
      * 第一次压缩
      * @param image
      * @return
      */
     public static Bitmap comp(Bitmap image) {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         long raw = image.getByteCount();
//         int raw = image.getRowBytes();
         image.compress(Bitmap.CompressFormat.PNG, 100, baos);
         long length = baos.toByteArray().length;
         int a = (int) (200/(float)length*100/1024);
         if(a == 0){
             a = 1;
         }
         else if(a>=100){
            a = 100;
         }
         if( baos.toByteArray().length / 1024>200) {//判断如果图片大于200KB,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
             baos.reset();//重置baos即清空baos
             image.compress(Bitmap.CompressFormat.PNG, a, baos);//这里压缩50%，把压缩后的数据存放到baos中
         }
         ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
         Options newOpts = new Options();
         //开始读入图片，此时把options.inJustDecodeBounds 设回true了  
         newOpts.inJustDecodeBounds = true;  
         Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
         newOpts.inJustDecodeBounds = false;  
         int w = newOpts.outWidth;  
         int h = newOpts.outHeight;  
         //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为  
         float hh = 320f;//这里设置高度为800f
         float ww = 240f;//这里设置宽度为480f
         //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可  
         int be = 1;//be=1表示不缩放  
         if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放  
             be = (int) (newOpts.outWidth / ww);  
         } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放  
             be = (int) (newOpts.outHeight / hh);  
         }  
         if (be <= 0)  
             be = 1;  
         newOpts.inSampleSize = be;//设置缩放比例  
         //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了  
         isBm = new ByteArrayInputStream(baos.toByteArray());  
         bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);  
         return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
     }

     
     /**
      * 图片再次压缩
      * @param image
      * @return
      */
     public static Bitmap compressImage(Bitmap image) {
 		ByteArrayOutputStream baos = new ByteArrayOutputStream();
 		image.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
 		int options = 100;
 		while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
 			baos.reset();// 重置baos即清空baos
 			image.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
 			options -= 10;// 每次都减少10
            if(options<10){
                break;
            }
 		}
 		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
 		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
 		return bitmap;
 	}
     /**
     * 将图标转化为base64
     * @param bitmap  图片
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap){
        String base64 = null;
        byte data[] = Bitmap2Bytes(bitmap);
        try {
           base64 = encodeBase64File(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }

    /**
     * 压缩照片的 监听器
     */
    public interface OnCompressImageListener{
        int start = 1001;
        int end = 1002;
        int success = 1003;
        int failed = 1004;
        /**
         * 压缩后的照片
         * @param bitmap
         */
        void onSuccess(Bitmap bitmap);

        /**
         * 压缩失败的原照片
         * @param bitmap
         */
        void onFailed(Bitmap bitmap);

        /**
         * 压缩中
         */
        void onStart();

        /**
         * 压缩结束
         */
        void onEnd();

    }
    private OnCompressImageListener listener;
    /**
     * 接收处理压缩的事件
     */
    private Handler compressHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int code = msg.what;
            switch (code){
                case OnCompressImageListener.start:
                    listener.onStart();
                break;
                case OnCompressImageListener.success:
                    listener.onSuccess((Bitmap) msg.obj);
                break;
                case OnCompressImageListener.failed:
                    listener.onFailed((Bitmap) msg.obj);
                break;
                case OnCompressImageListener.end:
                    listener.onEnd();
                break;
            }
        }
    };
    /**
     * 异步 压缩照片   照片处理  属于耗时 操作
     */
    public void asyncComp(final Bitmap bitmap){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(listener != null){
                    sendMsg(compressHandler, OnCompressImageListener.failed, bitmap);
                    sendMsg(compressHandler, OnCompressImageListener.end, null);
                }
                Bitmap res = null;
                res = comp(bitmap);
               if(listener != null){
                   if(res!=null) {
                       sendMsg(compressHandler, OnCompressImageListener.success, res);
                   }
                   else{
                       sendMsg(compressHandler, OnCompressImageListener.failed, bitmap);
                   }
               }
                if(listener!=null){
                    sendMsg(compressHandler, OnCompressImageListener.end,null);
                }
            }
        }).start();
    }

    /**
     * String
     * @param path
     */
    public  void asyncComp(final String path){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                if(listener != null){
                    msg.what = OnCompressImageListener.start;
                    compressHandler.sendMessage(msg);
                }
                Bitmap bitmap = BitmapAndDrawable.bitmapFromFile(path);
                Bitmap res = null;
                res = comp(bitmap);
                if(bitmap == null){
                    if(listener != null){
                        sendMsg(compressHandler, OnCompressImageListener.failed, bitmap);
                        sendMsg(compressHandler, OnCompressImageListener.end,null);
                    }
                    return;
                }
                if(listener != null){
                    if(res!=null) {
                        sendMsg(compressHandler, OnCompressImageListener.success, res);
                    }
                    else{
                        sendMsg(compressHandler, OnCompressImageListener.failed, bitmap);
                    }
                }
                if(listener!=null){
                    sendMsg(compressHandler, OnCompressImageListener.end,null);
                }
            }
        }).start();
    }

    /**
     * 设置监听器
     * @param l
     */
    public void setListener(OnCompressImageListener l){
        this.listener = l;
    }

    private void sendMsg(Handler handler,int what,Object obj){
        Message msg = Message.obtain();
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }

    /**
     * 调用 系统裁剪头像
     * @param data
     */
    public static void cropImage(Uri data,BaseFragmentActivity activity){
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(data, "image/*");
            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 160);
            intent.putExtra("outputY", 160);
            intent.putExtra("return-data", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(SDCard.getPath(Constants.picture), "header.png")));
            activity.startActivityForResult(intent, PictureUtil.PHOTO_CROP);
        } catch (Exception e) {
        }
    }


 	
}