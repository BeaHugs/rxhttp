# Helper---帮助搭建Android项目开发
### 项目介绍:
> libs(网络框架,图片框架,项目基础框架...)
>> 网络框架:Retrofit+Rxjava.

>> 图片框架:Glide.

>> 基础框架:MVP

> common(Ui控件,常用的工具类)

>> XLoadView(简单实用的页面状态统一管理 ，加载中、无网络、无数据、出错等状态的随意切换)

>> 更新页面等...


### 网络库使用介绍:

#### 1、在application类里可进行初始化全局配置

        /**
         *统一请求头
         */
        BaseHeadersListener baseHeaderInterceptor = new BaseHeadersListener() {
            @Override
            public Map<String, String> buildHeaders() {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("token","token");
                return hashMap;
            }
        };


        //设置okhttp 参数
        OkHttpClient build = new OkHttpInfo.Builder(this)
                .setHeaders(baseHeaderInterceptor)//统一请求头
                .setCache(false)
                .setHasNetCacheTime(10)//默认有网络时候缓存60秒
                .setNoNetCacheTime(1)//默认无网络时候缓存3600秒
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
               // .setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //1、信任所有证书,不安全有风险（默认信任所有证书）
               // .setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();


        //设置 retrofit参数
        RxUtils.getInstance().init(this).config()
                .setOkClient(build)//设置的OKhttp参数
                .setBaseUrl("https://www.wanandroid.com");//网络请求的baseUrl
                
#### 2.Api请求方式及代码示例
##### 2.1 创建apiService



  public interface WanAndroid {
    @GET("/wxarticle/chapters/json")
    Flowable<PublicNumbersBean> getListofPublicNumbers();
}
      
      
      RxUtils.createApi(WanAndroid.class)//创建Apiservice对象
                .FgetListofPublicNumbers()
                .compose(BaseFlowableTransFormer.<PublicNumbersBean>switchSchedulers())//BaseFlowableTransFormer 自定义subscribe格式
                .subscribeWith(new BaseDisposableSubscriber<PublicNumbersBean>() {

                    @Override
                    public void doOnError(String errorMsg) {
                    }

                    @Override
                    public void doOnNext(PublicNumbersBean publicNumbersBean) {
                    }

                    @Override
                    public void doOnCompleted() {

                    }
                });
     
                
