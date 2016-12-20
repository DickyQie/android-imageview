#  Android自定义ImageView实现图片圆形 ，椭圆和矩形圆角显示
<p>Android中的ImageView只能显示矩形的图片，为了用户体验更多，Android实现圆角矩形，圆形或者椭圆等图形，一般通过自定义ImageView来实现，首先获取到图片的Bitmap，然后通过<strong>Paint</strong>和<strong>onDraw</strong>（）进行圆形图片显示。</p> 
<p>效果图：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="605" src="https://static.oschina.net/uploads/space/2016/1125/155928_E964_2945455.png" width="311"></p> 
<p><strong><span style="color:#B22222">代码：</span></strong></p> 
<pre><code class="language-html">activity_image.xml
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"&gt;
 
    &lt;com.example.imageviewdrawdemo.ZQImageViewRoundOval
        android:id="@+id/cicle"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/qie"&gt;
    &lt;/com.example.imageviewdrawdemo.ZQImageViewRoundOval&gt;
 
    &lt;com.example.imageviewdrawdemo.ZQImageViewRoundOval
        android:id="@+id/roundRect"
        android:layout_width="286dp"
        android:layout_height="190dp"
        android:layout_marginTop="10dp"
        android:src="@drawable/l1"&gt;
    &lt;/com.example.imageviewdrawdemo.ZQImageViewRoundOval&gt;
 
    &lt;com.example.imageviewdrawdemo.ZQImageViewRoundOval
        android:id="@+id/oval"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:src="@drawable/l2"&gt;
    &lt;/com.example.imageviewdrawdemo.ZQImageViewRoundOval&gt;
 
&lt;/LinearLayout&gt;</code></pre> 
<pre><code class="language-java">自定义ImageView类
/**
 * 实现圆形、圆角，椭圆等自定义图片View。
 * @author zq
 *
 */
public class  ZQImageViewRoundOval extends ImageView {
 
    private Paint mPaint;
 
    private int mWidth;
 
    private int mHeight;
 
    private int mRadius;//圆半径
 
    private RectF mRect;//矩形凹行大小
 
    private int mRoundRadius;// 圆角大小
 
    private BitmapShader mBitmapShader;//图形渲染
 
    private Matrix mMatrix;
 
    private int mType;// 记录是圆形还是圆角矩形
 
    public static final int TYPE_CIRCLE = 0;// 圆形
    public static final int TYPE_ROUND = 1;// 圆角矩形
    public static final int TYPE_OVAL = 2;//椭圆形
    public static final int DEFAUT_ROUND_RADIUS = 10;//默认圆角大小
 
    public ZQImageViewRoundOval(Context context) {
        this(context, null);
        // TODOAuto-generated constructor stub
    }
 
    public ZQImageViewRoundOval(Context context, AttributeSet attrs) {
        this(context,attrs, 0);
        // TODOAuto-generated constructor stub
    }
 
    public ZQImageViewRoundOval(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs, defStyle);
        initView();
    }
 
    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
        mRoundRadius = DEFAUT_ROUND_RADIUS;
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, intheightMeasureSpec) {
        // TODOAuto-generated method stub
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        // 如果是绘制圆形，则强制宽高大小一致
        if (mType == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(),getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }
 
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
 
        if (null ==getDrawable()) {
            return;
        }
        setBitmapShader();
        if (mType == TYPE_CIRCLE) {
            canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        } else if (mType == TYPE_ROUND) {
            mPaint.setColor(Color.RED);
            canvas.drawRoundRect(mRect, mRoundRadius, mRoundRadius, mPaint);
        }else if(mType == TYPE_OVAL){
            canvas.drawOval(mRect, mPaint);
        }
    }
 
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODOAuto-generated method stub
        super.onSizeChanged(w,h, oldw, oldh);
        mRect = new RectF(0,0, getWidth(), getHeight());
    }
 
    /**
     * 设置BitmapShader
     */
    private void setBitmapShader() {
        Drawable drawable = getDrawable();
        if (null ==drawable) {
            return;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        // 将bitmap作为着色器来创建一个BitmapShader
        mBitmapShader = newBitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
        float scale =1.0f;
        if (mType == TYPE_CIRCLE) {
            // 拿到bitmap宽或高的小值
            int bSize =Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = mWidth * 1.0f /bSize;
 
        } else if (mType == TYPE_ROUND || mType == TYPE_OVAL) {
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            scale = Math.max(getWidth() * 1.0f/ bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale,scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
 
    }
 
    /**
     * drawable转bitmap
     *
     * @paramdrawable
     * @return
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawableinstanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable =(BitmapDrawable) drawable;
            returnbitmapDrawable.getBitmap();
        }
        int w =drawable.getIntrinsicWidth();
        int h =drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w,h, Config.ARGB_8888);
        Canvas canvas = newCanvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }
    /**
     * 单位dp转单位px
     */
    public int dpTodx(int dp){
       
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
                dp,getResources().getDisplayMetrics()); 
    }
   
    public int getType(){
        return mType;
    }
    /**
     * 设置图片类型：圆形、圆角矩形、椭圆形
     * @param mType
     */
    public void setType(int mType) {
        if(this.mType !=mType){
            this.mType = mType;
            invalidate();
        }
       
    }
    public int getRoundRadius() {
        return mRoundRadius;
    }
    /**
     * 设置圆角大小
     * @parammRoundRadius
     */
    public void setRoundRadius(int mRoundRadius) {
        if(this.mRoundRadius !=mRoundRadius){
            this.mRoundRadius =mRoundRadius;
            invalidate();
        }
       
    }
}</code></pre> 
<pre><code class="language-java">/****
 *
 * 自定义ImageView实现圆形图片，圆角矩形图片  椭圆图片
 *
 * @author zhangqie
 *
 */
public class MainActivity extends Activity {
 
  
    private  ZQImageViewRoundOvaliv_circle;//圆形图片
    private ZQImageViewRoundOval iv_roundRect;//圆角矩形图片
    private ZQImageViewRoundOval iv_oval;//椭圆图片
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_image);
      initViews();
   }
   /**
    * 初始化Views
    */
   private void initViews(){
      iv_circle =(ZQImageViewRoundOval)findViewById(R.id.cicle);
      iv_roundRect =(ZQImageViewRoundOval)findViewById(R.id.roundRect);
      iv_oval =(ZQImageViewRoundOval)findViewById(R.id.oval);
      
     
      iv_roundRect.setType(ZQImageViewRoundOval.TYPE_ROUND);
      iv_roundRect.setRoundRadius(6);//矩形凹行大小
      
       iv_oval.setType(ZQImageViewRoundOval.TYPE_OVAL);
       iv_oval.setRoundRadius(45);// 圆角大小
   }
}</code></pre> 
<p><em><strong><span style="color:#B22222">源码下载：</span></strong></em></p> 
<p>&nbsp;</p> 
<p>Eclipse下载：<a href="http://download.csdn.net/detail/dickyqie/9621330" target="_blank" rel="nofollow">http://download.csdn.net/detail/dickyqie/9621330</a></p> 
<p>AndroidStudio下载：<a href="https://github.com/DickyQie/ImageViewDrawDemo" target="_blank" rel="nofollow">&nbsp;https://github.com/DickyQie/ImageViewDrawDemo&nbsp;&nbsp;</a></p>

