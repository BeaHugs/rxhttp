# Helper
# RxJava+Retrofit封装

## 内部库介绍:
>>common:(Android开发的工具集合)
>>>简单实用的页面状态统一管理(加载中、无网络、无数据、出错等状态)</br>
>>>对Android运行时权限进行的简化封装</br>

>>libs:(封装项目的基础框架)
>>>mvp框架基础搭建</br>
>>>网络框架封装(Retrofit+Rxjava)


# 使用说明
### 1、在application类里边进行初始化配置全局请求参数
> #### 自己创建application,然后在Oncreate里面根据自己的需求写一下代码
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
                .setCache(true)
                .setHasNetCacheTime(10)//默认有网络时候缓存60秒
                .setNoNetCacheTime(3600)//默认有网络时候缓存3600秒
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
                .setBaseUrl("https://.........");//网络请求的baseUrl


