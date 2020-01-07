# Helper---帮助搭建Android项目开发
### 项目介绍:
# RxHttp

对RxJava2+Retrofit2+OkHttp3的封装，实现接口请求和文件下载，文件上传

# 功能简介

- 网络请求（RxRequest.create()）
  - 支持监听请求声明周期，如开始结束和网络错误
  - 支持多BaseUrl，可针对不同请求重定向
  - 支持针对不同请求设置不同缓存策略，如无网强制获取缓存，有网缓存有效10秒
  - 支持添加公共请求参数
  - 支持自定义异常处理和异常提示消息
- 文件下载（RxRequest.downloadFile(this,url)）
  - 支持断点续传
  - 支持下载进度回调
  - 支持下载速度回调
  - 支持下载过程状态监听
  - 支持在仅保存下载路径未保存进度时自动恢复断点续传
  - 支持自动获取真实文件名



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


    Disposable request = RxRequest.create(FreeApi.api().getBannerList()).listener(new RxRequest.RequestListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onError(BaseException handle) {
            }

            @Override
            public void onFinish() {
            }
        }).request(new RxRequest.ResultCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(int code, List<BannerBean> data) {
            }

            @Override
            public void onFailed(int code, String msg) {
            }
        });
        
        
        

  ##### 2.2 下载文件资源

     
        RxDownload mRxDownload = RxRequest.downloadFile(this,et_url.getText().toString());
        mRxDownload.setDownloadListener(new RxDownload.DownloadListener() {
        
                    /***
                     * DownloadListener   下载状态监听
                     */
                    @Override
                    public void onStarting(DownloadInfo info) {
                    }

                    @Override
                    public void onDownloading(DownloadInfo info) {
                    }

                    @Override
                    public void onError(DownloadInfo info, Throwable e) {
                    }

                    @Override
                    public void onStopped(DownloadInfo info) {
                    }

                    @Override
                    public void onCanceled(DownloadInfo info) {
                    }

                    @Override
                    public void onCompletion(DownloadInfo info) {
                    }
                })
                .setProgressListener(new RxDownload.ProgressListener() {
                   /**
                     *  ProgressListener 下载进度监听
                     */
                    @Override
                    public void onProgress(float progress, long downloadLength, long contentLength) {
                    }

                    @Override
                    public void onAleraProgress(float progress, String downloadLength, String contentLength) {
                    }
                })
                .setSpeedListener(new RxDownload.SpeedListener() {
                   /**
                     *  SpeedListener 网速监听
                     */
                    @Override
                    public void onSpeedChange(float bytePerSecond, String speedFormat) {
                    }
                });
     mRxDownload.start();//开始下载
     mRxDownload.stop(); //暂停下载
     mRxDownload.cancel();//取消下载

 
 ### DownloadInfo

用于保存下载信息，如需断点续传，需要自己保存以下几个必传项

- #### String url

  下载文件的链接**（必传项）**

- #### String saveDirPath

  自定义下载文件的保存目录**（断点续传时必传项）**

- #### String saveFileName

  自定义下载文件的保存文件名，需带后缀名**（断点续传时必传项）**

- #### long downloadLength

  已下载文件的长度**（断点续传时必传项）**

- #### long contentLength

  下载文件的总长度

- #### State state

  当前下载状态

  - ##### STARTING

    正在开始

  - ##### DOWNLOADING

    正在下载

  - ##### STOPPED

    未开始/已停止

  - ##### ERROR

    下载出错

  - ##### COMPLETION

    下载完成

- #### Mode mode

  获取保存路径的文件已存在但未保存下载进度时的模式

  - ##### APPEND

    追加

  - ##### REPLACE

    替换

  - ##### RENAME

    重命名

- #### create(String)

  创建一个下载对象，参数为url

- #### create(String, String, String)

  创建一个下载对象，参数为url/保存目录/文件名

- #### create(String, String, String, long, long)

  创建一个下载对象，参数为url/保存目录/文件名/已下载长度/总长度

### RxDownload

- #### create(DownloadInfo)

  用于新建一个下载任务

- #### setDownloadListener(DownloadListener)

  设置下载状态监听

  - ##### onStarting()

    正在开始，正在连接服务器

  - ##### onDownloading()

    正在下载

  - ##### onStopped()

    已停止，不会删除已下载部分，支持断点续传

  - ##### onCanceled()

    已取消，会删除已下载的部分文件，再次开始会重新下载

  - ##### onCompletion(DownloadInfo)

    下载完成

  - ##### onError(Throwable)

    下载出错

- #### setProgressListener(ProgressListener)

  - ##### onProgress(float)

    下载进度回调（0~1）

- #### setSpeedListener(SpeedListener)

  - ##### onSpeedChange(float, String)

    下载速度回调，两个值分别为每秒下载比特数和格式化后速度（如：1.2KB/s，3.24MB/s）

- #### start()

  开始下载/继续下载

- #### stop()

  停止下载，不会删除已下载部分，支持断点续传

- #### cancel()

  取消下载，会删除已下载的部分文件，再次开始会重新下载


