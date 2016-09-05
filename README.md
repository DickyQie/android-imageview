#  Android自定义ImageView实现图片圆形 ，椭圆和矩形圆角显示
<div id="article_content" class="article_content">

<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px">android中的ImageView只能显示矩形的图片，为了用户体验更多，<span style="font-family:tahoma,arial,宋体; line-height:25.2px">Android实现<span style="color:#ff0000">圆角矩形，圆形或者椭圆等图形</span>，一般通过自定义<span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px">ImageView来实现</span></span>，首先获取到图片的Bitmap，然后通过<span style="color:#cc0000"><strong>Paint</strong></span>和<strong><span style="color:#cc0000">onDraw</span></strong>（）进行圆形图片显示。</span></span></p>
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px">效果图：</span></span></p>
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img src="http://img.blog.csdn.net/20160905140037080?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" alt=""><br>
</span></span></p>
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px; color:#ff0000">代码：</span></span></p>
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px; color:#ff0000">activity_image.xml</span></span></p>
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px; color:#ff0000"></span></span></p>
<p><span style="color:teal">&lt;?</span><span style="color:#3F7F7F">xml</span> <span style="color:#7F007F">
version</span>=<em><span style="color:#2A00FF">&quot;1.0&quot;</span></em> <span style="color:#7F007F">
encoding</span>=<em><span style="color:#2A00FF">&quot;utf-8&quot;</span></em><span style="color:teal">?&gt;</span></p>
<p><span style="color:teal">&lt;</span><span style="color:#3F7F7F">LinearLayout</span>
<span style="color:#7F007F">xmlns:android</span>=<em><span style="color:#2A00FF">&quot;http://schemas.android.com/apk/res/android&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_width</span>=<em><span style="color:#2A00FF">&quot;match_parent&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_height</span>=<em><span style="color:#2A00FF">&quot;match_parent&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:orientation</span>=<em><span style="color:#2A00FF">&quot;vertical&quot;</span></em><span style="color:teal">&gt;</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:id</span>=<em><span style="color:#2A00FF">&quot;@&#43;id/cicle&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_width</span>=<em><span style="color:#2A00FF">&quot;100dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_height</span>=<em><span style="color:#2A00FF">&quot;100dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:src</span>=<em><span style="color:#2A00FF">&quot;@drawable/qie&quot;</span></em><span style="color:teal">&gt;</span></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;/</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span><span style="color:teal">&gt;</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:id</span>=<em><span style="color:#2A00FF">&quot;@&#43;id/roundRect&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_width</span>=<em><span style="color:#2A00FF">&quot;286dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_height</span>=<em><span style="color:#2A00FF">&quot;190dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_marginTop</span>=<em><span style="color:#2A00FF">&quot;10dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:src</span>=<em><span style="color:#2A00FF">&quot;@drawable/l1&quot;</span></em><span style="color:teal">&gt;</span></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;/</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span><span style="color:teal">&gt;</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:id</span>=<em><span style="color:#2A00FF">&quot;@&#43;id/oval&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_width</span>=<em><span style="color:#2A00FF">&quot;wrap_content&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_height</span>=<em><span style="color:#2A00FF">&quot;wrap_content&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:layout_marginTop</span>=<em><span style="color:#2A00FF">&quot;20dp&quot;</span></em></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#7F007F">android:src</span>=<em><span style="color:#2A00FF">&quot;@drawable/l2&quot;</span></em><span style="color:teal">&gt;</span></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:teal">&lt;/</span><span style="color:#3F7F7F">com.example.imageviewdrawdemo.ZQImageViewRoundOval</span><span style="color:teal">&gt;</span></p>
<p>&nbsp;</p>
<p><span style="color:teal">&lt;/</span><span style="color:#3F7F7F">LinearLayout</span><span style="color:teal">&gt;</span></p>
自定义ImageView类
<p><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-size:14px"></span></span></p>
<p></p>
<p><span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span><span style="color:#3F5FBF">实现圆形、圆角，椭圆等自定义图片</span><span style="color:#3F5FBF">View</span><span style="color:#3F5FBF">。</span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span><strong><span style="color:#7F9FBF">@author</span></strong><span style="color:#3F5FBF">
<u>zhangqie</u></span></p>
<p><span style="color:#3F5FBF">&nbsp;*</span></p>
<p><span style="color:#3F5FBF">&nbsp;*/</span></p>
<p><strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
class &nbsp;</span></strong>ZQImageViewRoundOval <strong><span style="color:#7F0055">extends</span></strong> ImageView {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span></strong> Paint <span style="color:#0000C0">
mPaint</span>;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
int</span></strong> <span style="color:#0000C0">mWidth</span>;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
int</span></strong> <u><span style="color:#0000C0">mHeight</span></u>;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
int</span></strong> <span style="color:#0000C0">mRadius</span>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">圆半径</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span></strong> RectF <span style="color:#0000C0">
mRect</span>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">矩形凹行大小</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
int</span></strong> <span style="color:#0000C0">mRoundRadius</span>;<span style="color:#3F7F5F">//
</span><span style="color:#3F7F5F">圆角大小</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private&nbsp;</span></strong>BitmapShader <span style="color:#0000C0">
mBitmapShader</span>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">图形渲染</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span></strong> Matrix <span style="color:#0000C0">
mMatrix</span>;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
int</span></strong> <span style="color:#0000C0">mType</span>;<span style="color:#3F7F5F">//
</span><span style="color:#3F7F5F">记录是圆形还是圆角矩形</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span> <span style="color:#7F0055">
int</span></strong> <em><span style="color:#0000C0">TYPE_CIRCLE</span></em> = 0;<span style="color:#3F7F5F">//
</span><span style="color:#3F7F5F">圆形</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span> <span style="color:#7F0055">
int</span></strong> <em><span style="color:#0000C0">TYPE_ROUND</span></em> = 1;<span style="color:#3F7F5F">//
</span><span style="color:#3F7F5F">圆角矩形</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span> <span style="color:#7F0055">
int</span></strong> <em><span style="color:#0000C0">TYPE_OVAL</span></em> = 2;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">椭圆形</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span> <span style="color:#7F0055">
int</span></strong> <em><span style="color:#0000C0">DEFAUT_ROUND_RADIUS</span></em> = 10;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">默认圆角大小</span></p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public&nbsp;</span></strong>ZQImageViewRoundOval(Context context) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">this</span></strong>(context, <strong>
<span style="color:#7F0055">null</span></strong>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><strong><span style="color:#7F9FBF">TODO</span></strong><span style="color:#3F7F5F">Auto-generated constructor stub</span></p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public&nbsp;</span></strong>ZQImageViewRoundOval(Context context, AttributeSet attrs) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">this</span></strong>(context,attrs, 0);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><strong><span style="color:#7F9FBF">TODO</span></strong><span style="color:#3F7F5F">Auto-generated constructor stub</span></p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public&nbsp;</span></strong>ZQImageViewRoundOval(Context context, AttributeSet attrs,
<strong><span style="color:#7F0055">int</span></strong> defStyle){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">super</span></strong>(context,attrs, defStyle);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; initView();</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
void&nbsp;</span></strong>initView() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mPaint</span> = <strong><span style="color:#7F0055">new</span></strong> Paint();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mPaint</span>.setAntiAlias(<strong><span style="color:#7F0055">true</span></strong>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mMatrix</span> = <strong><span style="color:#7F0055">new</span></strong> Matrix();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mRoundRadius</span> = <em><span style="color:#0000C0">DEFAUT_ROUND_RADIUS</span></em>;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">protected</span> <span style="color:#7F0055">
void&nbsp;</span></strong>onMeasure(<strong><span style="color:#7F0055">int</span></strong> widthMeasureSpec,
<strong><span style="color:#7F0055">int</span></strong>heightMeasureSpec) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><strong><span style="color:#7F9FBF">TODO</span></strong><span style="color:#3F7F5F">Auto-generated method stub</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">super</span></strong>.onMeasure(widthMeasureSpec,heightMeasureSpec);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">如果是绘制圆形，则强制宽高大小一致</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<span style="color:#0000C0">mType</span> ==
<em><span style="color:#0000C0">TYPE_CIRCLE</span></em>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mWidth</span> = Math.<em>min</em>(getMeasuredWidth(),getMeasuredHeight());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mRadius</span> = <span style="color:#0000C0">
mWidth</span> / 2;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; setMeasuredDimension(<span style="color:#0000C0">mWidth</span>, <span style="color:#0000C0">
mWidth</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">protected</span> <span style="color:#7F0055">
void&nbsp;</span></strong>onDraw(Canvas canvas) {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<strong><span style="color:#7F0055">null</span></strong> ==getDrawable()) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong>;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; setBitmapShader();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<span style="color:#0000C0">mType</span> ==
<em><span style="color:#0000C0">TYPE_CIRCLE</span></em>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; canvas.drawCircle(<span style="color:#0000C0">mRadius</span>, <span style="color:#0000C0">
mRadius</span>, <span style="color:#0000C0">mRadius</span>, <span style="color:#0000C0">
mPaint</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; } <strong><span style="color:#7F0055">else</span> <span style="color:#7F0055">
if</span></strong> (<span style="color:#0000C0">mType</span> == <em><span style="color:#0000C0">TYPE_ROUND</span></em>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mPaint</span>.setColor(Color.<em><span style="color:#0000C0">RED</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; canvas.drawRoundRect(<span style="color:#0000C0">mRect</span>, <span style="color:#0000C0">
mRoundRadius</span>, <span style="color:#0000C0">mRoundRadius</span>, <span style="color:#0000C0">
mPaint</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }<strong><span style="color:#7F0055">else</span> <span style="color:#7F0055">
if</span></strong>(<span style="color:#0000C0">mType</span> == <em><span style="color:#0000C0">TYPE_OVAL</span></em>){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; canvas.drawOval(<span style="color:#0000C0">mRect</span>, <span style="color:#0000C0">
mPaint</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">protected</span> <span style="color:#7F0055">
void&nbsp;</span></strong>onSizeChanged(<strong><span style="color:#7F0055">int</span></strong> w,
<strong><span style="color:#7F0055">int</span></strong> h, <strong><span style="color:#7F0055">int</span></strong> oldw,
<strong><span style="color:#7F0055">int</span></strong> oldh) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><strong><span style="color:#7F9FBF">TODO</span></strong><span style="color:#3F7F5F">Auto-generated method stub</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">super</span></strong>.onSizeChanged(w,h, oldw, oldh);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mRect</span> = <strong><span style="color:#7F0055">new</span></strong> RectF(0,0, getWidth(), getHeight());</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><span style="color:#3F5FBF">设置</span><span style="color:#3F5FBF">BitmapShader</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; */</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
void&nbsp;</span></strong>setBitmapShader() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Drawable drawable = getDrawable();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<strong><span style="color:#7F0055">null</span></strong> ==drawable) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong>;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Bitmap bitmap = drawableToBitmap(drawable);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">将</span><span style="color:#3F7F5F">bitmap</span><span style="color:#3F7F5F">作为着色器来创建一个</span><span style="color:#3F7F5F">BitmapShader</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mBitmapShader</span> = <strong><span style="color:#7F0055">new</span></strong>BitmapShader(bitmap, TileMode.<em><span style="color:#0000C0">CLAMP</span></em>, TileMode.<em><span style="color:#0000C0">CLAMP</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">float</span></strong> scale =1.0f;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<span style="color:#0000C0">mType</span> ==
<em><span style="color:#0000C0">TYPE_CIRCLE</span></em>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">拿到</span><span style="color:#3F7F5F">bitmap</span><span style="color:#3F7F5F">宽或高的小&#20540;</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">int</span></strong> bSize =Math.<em>min</em>(bitmap.getWidth(), bitmap.getHeight());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; scale = <span style="color:#0000C0">mWidth</span> * 1.0f /bSize;</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; } <strong><span style="color:#7F0055">else</span> <span style="color:#7F0055">
if</span></strong> (<span style="color:#0000C0">mType</span> == <em><span style="color:#0000C0">TYPE_ROUND</span></em> ||
<span style="color:#0000C0">mType</span> == <em><span style="color:#0000C0">TYPE_OVAL</span></em>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">如果图片的宽或者高与</span><span style="color:#3F7F5F">view</span><span style="color:#3F7F5F">的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们</span><span style="color:#3F7F5F">view</span><span style="color:#3F7F5F">的宽高；所以我们这里取大&#20540;；</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;scale = Math.<em>max</em>(getWidth() * 1.0f/ bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// shader</span><span style="color:#3F7F5F">的变换矩阵，我们这里主要用于放大或者缩小</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mMatrix</span>.setScale(scale,scale);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">设置变换矩阵</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mBitmapShader</span>.setLocalMatrix(<span style="color:#0000C0">mMatrix</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">mPaint</span>.setShader(<span style="color:#0000C0">mBitmapShader</span>);</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * <u>drawable</u></span><span style="color:#3F5FBF">转</span><span style="color:#3F5FBF">bitmap</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><strong><span style="color:#7F9FBF">@param</span></strong><span style="color:#3F5FBF">drawable</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><strong><span style="color:#7F9FBF">@return</span></strong></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; */</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span></strong> Bitmap drawableToBitmap(Drawable drawable) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (drawable<strong><span style="color:#7F0055">instanceof</span></strong> BitmapDrawable) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; BitmapDrawable bitmapDrawable =(BitmapDrawable) drawable;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong>bitmapDrawable.getBitmap();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">int</span></strong> w =drawable.getIntrinsicWidth();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">int</span></strong> h =drawable.getIntrinsicHeight();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Bitmap bitmap = Bitmap.<em>createBitmap</em>(w,h, Config.<em><span style="color:#0000C0">ARGB_8888</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Canvas canvas = <strong><span style="color:#7F0055">new</span></strong>Canvas(bitmap);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; drawable.setBounds(0, 0, w, h);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; drawable.draw(canvas);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> bitmap;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><span style="color:#3F5FBF">单位</span><u><span style="color:#3F5FBF">dp</span></u><span style="color:#3F5FBF">转单位</span><u><span style="color:#3F5FBF">px</span></u></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; */</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
int</span></strong> dpTodx(<strong><span style="color:#7F0055">int</span></strong> dp){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> (<strong><span style="color:#7F0055">int</span></strong>)TypedValue.<em>applyDimension</em>(TypedValue.<em><span style="color:#0000C0">COMPLEX_UNIT_DIP</span></em>,&nbsp;
</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; dp,getResources().getDisplayMetrics());&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
int</span></strong> getType(){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> <span style="color:#0000C0">
mType</span>;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><span style="color:#3F5FBF">设置图片类型：圆形、圆角矩形、椭圆形</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><strong><span style="color:#7F9FBF">@param</span></strong><span style="color:#3F5FBF"> mType</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; */</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void</span></strong> setType(<strong><span style="color:#7F0055">int</span></strong> mType) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong>(<strong><span style="color:#7F0055">this</span></strong>.<span style="color:#0000C0">mType</span> !=mType){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">this</span></strong>.<span style="color:#0000C0">mType</span> = mType;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; invalidate();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
int&nbsp;</span></strong>getRoundRadius() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> <span style="color:#0000C0">
mRoundRadius</span>;</p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><span style="color:#3F5FBF">设置圆角大小</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; * </span><strong><span style="color:#7F9FBF">@param</span></strong><span style="color:#3F5FBF">mRoundRadius</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp;&nbsp;&nbsp; */</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void&nbsp;</span></strong>setRoundRadius(<strong><span style="color:#7F0055">int</span></strong> mRoundRadius) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong>(<strong><span style="color:#7F0055">this</span></strong>.<span style="color:#0000C0">mRoundRadius</span> !=mRoundRadius){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">this</span></strong>.<span style="color:#0000C0">mRoundRadius</span> =mRoundRadius;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; invalidate();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp; }</p>
<p>}</p>
<span style="color:rgb(255,0,0); font-family:Verdana,Arial,Helvetica,sans-serif; font-size:14px; line-height:21px">MainActivity类</span><br>
<p></p>
<p><span style="color:#3F5FBF">/****</span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span><span style="color:#3F5FBF">自定义</span><span style="color:#3F5FBF">ImageView</span><span style="color:#3F5FBF">实现圆形图片，圆角矩形图片</span><span style="color:#3F5FBF">&nbsp;
</span><span style="color:#3F5FBF">椭圆图片</span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span></p>
<p><span style="color:#3F5FBF">&nbsp;* </span><strong><span style="color:#7F9FBF">@author</span></strong><span style="color:#3F5FBF">
<u>zhangqie</u></span></p>
<p><span style="color:#3F5FBF">&nbsp;*</span></p>
<p><span style="color:#3F5FBF">&nbsp;*/</span></p>
<p><strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
class&nbsp;</span></strong>MainActivity <strong><span style="color:#7F0055">extends</span></strong> Activity {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span></strong>&nbsp; ZQImageViewRoundOval<u><span style="color:#0000C0">iv_circle</span></u>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">圆形图片</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private&nbsp;</span></strong>ZQImageViewRoundOval
<span style="color:#0000C0">iv_roundRect</span>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">圆角矩形图片</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">private&nbsp;</span></strong>ZQImageViewRoundOval
<span style="color:#0000C0">iv_oval</span>;<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">椭圆图片</span></p>
<p>&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">protected</span> <span style="color:#7F0055">
void&nbsp;</span></strong>onCreate(Bundle savedInstanceState) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">super</span></strong>.onCreate(savedInstanceState);</p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;<span style="white-space:pre"> </span>setContentView(R.layout.<em><span style="color:#0000C0">activity_image</span></em>);</p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;<span style="white-space:pre"> </span>initViews();</p>
<p>&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp; <span style="color:#3F5FBF">/**</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp; &nbsp;* </span><span style="color:#3F5FBF">初始化</span><span style="color:#3F5FBF">Views</span></p>
<p><span style="color:#3F5FBF">&nbsp;&nbsp; &nbsp;*/</span></p>
<p>&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
void&nbsp;</span></strong>initViews(){</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_circle</span> =(ZQImageViewRoundOval)findViewById(R.id.<em><span style="color:#0000C0">cicle</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_roundRect</span> =(ZQImageViewRoundOval)findViewById(R.id.<em><span style="color:#0000C0">roundRect</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_oval</span> =(ZQImageViewRoundOval)findViewById(R.id.<em><span style="color:#0000C0">oval</span></em>);</p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_roundRect</span>.setType(ZQImageViewRoundOval.<em><span style="color:#0000C0">TYPE_ROUND</span></em>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_roundRect</span>.setRoundRadius(6);<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">矩形凹行大小</span></p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_oval</span>.setType(ZQImageViewRoundOval.<em><span style="color:#0000C0">TYPE_OVAL</span></em>);</p>
<p>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <span style="color:#0000C0">iv_oval</span>.setRoundRadius(45);<span style="color:#3F7F5F">//
</span><span style="color:#3F7F5F">圆角大小</span></p>
<p>&nbsp;&nbsp; }</p>
<p>}</p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px; line-height:26px">
<span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-family:Arial; line-height:35px"><span style="color:rgb(204,0,0); font-family:&quot;Microsoft Yahei&quot;; font-size:18px; line-height:28px; text-indent:32px">源码下载：</span><br>
</span></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px; line-height:26px">
<span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-family:Arial; line-height:35px"><br>
</span></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px; line-height:26px">
<span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-family:Arial; line-height:35px">Eclipse下载：<a target="_blank" href="http://download.csdn.net/detail/dickyqie/9621330">http://download.csdn.net/detail/dickyqie/9621330</a></span><br>
</span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px; line-height:26px">
<span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><span style="font-family:Arial; line-height:35px">AndroidStudio下载：<a target="_blank" href="https://github.com/DickyQie/ImageViewDrawDemo"> https://github.com/DickyQie/ImageViewDrawDemo&nbsp;&nbsp;</a></span></span></p>
<div><span style="font-family:Verdana,Arial,Helvetica,sans-serif; line-height:21px"><br>
</span></div>
<br>
<br>
   
</div>
