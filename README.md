# THttp

##
###①注册
   		//兼容5.0以下使用https和使用证书访问https
        mThttp=THttp.register(this,R.raw.qgar);
        //访问http或5.0以上访问https
        mThttp=THttp.register(this);
###②回调
    StringRequest request1=new StringRequest(HttpConn.Method.GET, url,null,new ResultModel
                    .OnListener() {

                @Override
                public void complate(String result) {
                    Log.w(TAG, "complate: "+result );
                }

                @Override
                public void Error(String error) {
                    Log.w(TAG, "Error: "+error );
                }
            }){
                @Override
                public Map<String, String> getHeader() {
                    Map<String,String > map=new HashMap<>();
                    map.put("token","");
                    return map;
                }
            };
			//整体和Volly的用法相似。
###③加入队列
    mThttp.addRequest(request1);
###④解注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        THttp.unRegist();
    }