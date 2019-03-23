package com.cg.requesttest.api;


import com.cg.requesttest.data.response.BookSearchResponse;
import com.cg.requesttest.data.response.IpResult;
import com.cg.requesttest.data.response.MyResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author
 * @version 1.0
 * @date 3/5/2018
 * @GET 表明这是get请求
 * @POST 表明这是post请求
 * @PUT 表明这是put请求
 * @DELETE 表明这是delete请求
 * @PATCH 表明这是一个patch请求，该请求是对put请求的补充，用于更新局部资源
 * @HEAD 表明这是一个head请求
 * @OPTIONS 表明这是一个option请求
 * @HTTP 通用注解, 可以替换以上所有的注解，其拥有三个属性：method，path，hasBody
 * @Headers 用于添加固定请求头，可以同时添加多个。通过该注解添加的请求头不会相互覆盖，而是共同存在
 * @Header 作为方法的参数传入，用于添加不固定值的Header，该注解会更新已有的请求头
 * @Body 多用于post请求发送非表单数据, 比如想要以post方式传递json格式数据
 * @Filed 多用于post请求中表单字段, Filed和FieldMap需要FormUrlEncoded结合使用
 * @FiledMap 和@Filed作用一致，用于不确定表单参数
 * @Part 用于表单字段, Part和PartMap与Multipart注解结合使用, 适合文件上传的情况
 * @PartMap 用于表单字段, 默认接受的类型是Map<String,REquestBody>，可用于实现多文件上传
 * <p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * </p>
 * Part标志上文的内容可以是富媒体形势，比如上传一张图片，上传一段音乐，即它多用于字节流传输。
 * 而Filed则相对简单些，通常是字符串键值对。
 * @Path 用于url中的占位符,{占位符}和PATH只用在URL的path部分，url中的参数使用Query和QueryMap代替，保证接口定义的简洁
 * @Query 用于Get中指定参数
 * @QueryMap 和Query使用类似
 * @Url 指定请求路径
 * //使用@Headers添加多个请求头
 *   @Headers({
 *             "User-Agent:android",
 *             "apikey:123456789",
 *     })
 * 
 */

public interface RequestApiInterface {
    /**
     * https://www.cnblogs.com/oceanfhy/p/7699379.html
     * https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3
     * Get方法请求参数都会以key=value的方式拼接在url后面，
     * 第一种就是像上文提到的直接在interface中添加@Query注解，
     * 还有一种方式是通过Interceptor实现，直接看如何通过Interceptor实现请求参数的添加。
     * addQueryParameter就是添加请求参数的具体代码，这种方式比较适用于所有的请求都需要添加的参数，一般现在的网络请求都会
     * 添加token作为用户标识，那么这种方式就比较适合。创建完成自定义的Interceptor后，还需要在Retrofit创建client处完成添加
       addInterceptor(new CustomInterceptor())
     * @param name
     * @param tag
     * @param start
     * @param count
     * @return
     */
    @Headers({"baseurl:douban"})
//    @GET(UrlConstants.searchBooksUrl)
    @GET("v2/book/search")
    Call<BookSearchResponse> callTypeGet(@Query("q") String name, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);
    /**
     url 	想要提交的网页地址 	
     desc 	对干货内容的描述 	单独的文字描述
     who 	提交者 ID 	干货提交者的网络 ID
     type 	干货类型 	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
     debug 	当前提交为测试数据 	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
     * @return
     */
    @FormUrlEncoded
    @Headers({"baseurl:gank"})
    @POST("api/add2gank")
    //    @POST(UrlConstants.postDataUrl)
    Call<Object> callTypePost(@Field("url") String url, @Field("desc") String desc, @Field("who") String who, @Field("type") String type, @Field("debug") Boolean debug);

    @GET("service/getIpInfo.php")
    Observable<BaseResponse<IpResult>> rxGet(@Query("ip") String ip);

    /** 
     * @date   2019/3/21
     * @version 1.0
     * @param  * @param 根据不同的类型，data返回不同的值
     * @return  
     */ 
    @GET("test/cg")
    Observable<BaseResponse<MyResponse.DataBean>> psalms(@Query("param") int type);


}
