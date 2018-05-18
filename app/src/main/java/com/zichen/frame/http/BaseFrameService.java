package com.zichen.frame.http;

import com.zichen.frame.entity.request.GetBannerListRequest;
import com.zichen.frame.entity.request.GetNewsListRequest;
import com.zichen.frame.entity.request.LoginRequest;
import com.zichen.frame.entity.request.MobileRequest;
import com.zichen.frame.entity.response.GetBannerListResponse;
import com.zichen.frame.entity.response.GetNewsListResponse;
import com.zichen.frame.entity.response.GetProductsForIndexResponse;
import com.zichen.frame.entity.response.LoginResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * author : zichen
 * time : 2018/5/15
 * desc : HTTP请求方法
 * version: 1.0
 */
public interface BaseFrameService {

//    /**
//     * 获取手机验证码
//     */
//    @POST("getVfcodeForRegister")
//    Observable<GetVfcodeForRegisterResponse> getVfCode(@Body GetVfcodeForRegisterRequest getVfcodeForRegisterRequest);
//
//    /**
//     * 检查手机号是否已注册
//     */
//    @POST("checkPhoneNumberExist")
//    Observable<CheckPhoneNumberExistResponse> checkPhoneNumberExist(@Body CheckPhoneNumberExistRequest checkPhoneNumberExistRequest);
//

    /**
     * 登录
     */
    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest request);
//
//    /**
//     * 登录  用于在请求拦截器中拦截token过期错误然后重新登录获取最新token。
//     */
//    @Headers("Cache-Control: public, max-age=10")
//    @POST("login")
//    @Deprecated
//    Call<LoginResponse> login2(@Body LoginRequest request);
//
//    /**
//     * 用户注册
//     */
//    @POST("register")
//    Observable<RegisterResponse> register(@Body RegisterRequest request);
//
//    /**
//     * 获取证件类型
//     */
//    @POST("getCertType")
//    Observable<GetCertTypeResponse> getCertType(@Body GetCertTypeRequest request);
//
//    /**
//     * 重置密码
//     */
//    @POST("resetPassword")
//    Observable<ResetPasswordResponse> resetPassword(@Body ResetPasswordRequest resetPasswordRequest);
//
//    /**
//     * 修改登录密码
//     */
//    @POST("updateLoginPassword")
//    Observable<MobileResponse> updateLoginPassword(@Body UpdateLoginPasswordRequest updateLoginPasswordRequest);
//
//    /**
//     * 邮箱设置
//     */
//    @POST("")
//    Observable<SetEmailResponse> setEmail(@Body SetEmailRequest setEmailRequest);
//
//    /**
//     * 修改手机号
//     */
//    @POST("updateMobile")
//    Observable<MobileResponse> updateMobile(@Body UpdateMobileRequest updateMobileRequest);
//
//    /**
//     * 设置/修改通讯地址
//     */
//    @POST("setAddress")
//    Observable<SetAddressResponse> setAddress(@Body SetAddressRequest setAddressRequest);
//
//    /**
//     * 获取通讯地址
//     */
//    @POST("getAddress")
//    Observable<GetAddressResponse> getAddress(@Body GetAddressRequest getAddressRequest);
//
//

    /**
     * 获取banner列表
     */
    @POST("getBannerList")
    Observable<GetBannerListResponse> getBanner(@Body GetBannerListRequest getBannerListRequest);
//
//    /**
//     * banner处理
//     */
//    @POST("bannerDeal")
//    Observable<MobileResponse> dealBanner(@Body BannerDealRequest bannerDealRequest);
//

    /**
     * 首页获取新闻资讯和民生公告
     */
    @POST("getNewsList")
    Observable<GetNewsListResponse> getNewsList(@Body GetNewsListRequest getNewsListRequest);
//
//    /**
//     * 获取新闻资讯/民生观点详情
//     */
//    @POST("getNewsDetail")
//    Observable<GetNewsDetailResponse> getNewsDetail(@Body GetNewsDetailRequest getNewsDetailRequest);
//
//    /**
//     * 获取首页tab图标
//     */
//    @POST("getIconList")
//    Observable<GetIconListResponse> getIconList(@Body MobileRequest request);
//
//
//    /**
//     * 首页广告推送
//     */
//    @POST("getAdvertisement")
//    Observable<GetAdvertisementResponse> getAdvertisement(@Body MobileRequest request);
//
//    /**
//     * 广告推送处理
//     */
//    @POST("advertisementDeal")
//    Observable<AdvertisementDealResponse> dealAdvertisement(@Body AdvertisementDealRequest advertisementDealRequest);
//
//    /**
//     * 获取用户风险测评及财富顾问
//     */
//    @POST("getRiskAppraisalAndAdviser")
//    Observable<GetRiskAppraisalAndAdviserResponse> getRiskAppraisalAndAdviser(@Body MobileRequest request);
//

    /**
     * 获取首页产品
     */
    @POST("getProductsForIndex")
    Observable<GetProductsForIndexResponse> getProductForHome(@Body MobileRequest request);
//
//    /**
//     * 获取产品列表
//     */
//    @POST("getProductList")
//    Observable<GetProductListResponse> getProductList(@Body GetProductListRequest getProductListRequest);
//
//    /**
//     * 获取产品详情
//     */
//    @POST("getProductDetail")
//    Observable<GetProductDetailResponse> getProductDetail(@Body GetProductDetailRequest getProductDetailRequest);
//
//    /**
//     * 获取产品净值
//     */
//    @POST("getNetList")
//    Observable<GetNetListResponse> getNetList(@Body GetNetListRequest getNetListRequest);
//
//    /**
//     * 客户资产查询
//     */
//    @POST("getProperty")
//    Observable<GetPropertyResponse> getProperty(@Body MobileRequest request);
//
//    /**
//     * 获取持有资产
//     */
//    @POST("getHoldProperty")
//    Observable<GetHoldPropertyResponse> getHoldProperty(@Body GetHoldPropertyRequest getHoldPropertyRequest);
//
//    /**
//     * 获取交易明细
//     */
//    @POST("getTradeDetail")
//    Observable<GetTradeDetailResponse> getTradeDetail(@Body GetTradeDetailRequest getTradeDetailRequest);
//
//    /**
//     * 获取预约记录
//     */
//    @POST("getOrderRecord")
//    Observable<GetOrderRecordResponse> getOrderRecord(@Body GetOrderRecordRequest getOrderRecordRequest);
//
//    /**
//     * 获取我的信息
//     */
//    @POST("getMyAccount")
//    Observable<GetMyAccountResponse> getMyAccount(@Body MobileRequest getMyAccountRequest);
//
//    /**
//     * 获取实名认证信息
//     */
//    @POST("getCertification")
//    Observable<GetCertificationResponse> getCertification(@Body GetCertificationRequest getCertificationRequest);
//
//    /**
//     * 进行实名认证
//     */
//    @POST("doCertification")
//    Observable<DoCertificationResponse> doCertification(@Body DoCertificationRequest doCertificationRequest);
//
//    /**
//     * 获取投资者分类信息
//     */
//    @POST("getQualifiedInvestor")
//    Observable<GetQualifiedInvestorResponse> getQualifiedInvestor(@Body MobileRequest request);
//
//    /**
//     * 进行投资者分类认证
//     */
//    @POST("doQualifiedInvestor")
//    Observable<DoQualifiedInvestorResponse> doQualifiedInvestor(@Body DoQualifiedInvestorRequest request);
//
//    /**
//     * 进行普通投资者分类认证
//     */
//    @POST("doQualifiedInvestor")
//    Observable<MobileResponse> doQualifiedNormalInvestor(@Body DoQualifiedNormalInvestorRequest request);
//
//    /**
//     * 获取风险测评信息
//     */
//    @POST("getRiskAppraisal")
//    Observable<GetRiskAppraisalResponse> getRiskAppraisal(@Body MobileRequest request);
//
//    /**
//     * 获取财富顾问信息
//     */
//    @POST("getAdviser")
//    Observable<GetAdviserResponse> getAdviser(@Body GetAdviserRequest getAdviserRequest);
//
//    /**
//     * 申请财富顾问
//     */
//    @POST("doAdviser")
//    Observable<DoAdviserResponse> doAdviser(@Body DoAdviserRequest doAdviserRequest);
//
//    /**
//     * 获取关于我们信息
//     */
//    @POST("getAboutUs")
//    Observable<GetAboutUsResponse> getAboutUs(@Body MobileRequest request);
//    /**
//     * 统计点击联系我们
//     */
//    @POST("valueAddServiceDeal")
//    Observable<MobileResponse> statContactUs(@Body ValueAddServiceDealRequest request);
//
//    /**
//     * 添加意见反馈信息
//     */
//    @POST("addFeedback")
//    Observable<MobileResponse> addFeedback(@Body AddFeedbackRequest addFeedbackRequest);
//
//    /**
//     * 查询风险等级最新测评问卷
//     */
//    @POST("searchRiskEvaluation")
//    Observable<SearchRiskEvaluationResponse> searchRiskEvaluation(@Body SearchRiskEvaluationRequest searchRiskEvaluationRequest);
//
//    /**
//     * 提交问卷
//     */
//    @POST("submitRiskEvaluation")
//    Observable<SubmitRiskEvaluationResponse> submitRiskEvaluation(@Body SubmitRiskEvaluationRequest submitRiskEvaluationRequest);
//
//    /**
//     * 获取我的积分
//     */
//    @POST("getIntegration")
//    Observable<GetIntegrationResponse> getIntegration(@Body GetIntegrationRequest getIntegrationRequest);
//
//    /**
//     * 获取我的会员
//     */
//    @POST("getMember")
//    Observable<GetMemberResponse> getMember(@Body GetMemberRequest getMemberRequest);
//
//    /**
//     * 获取会员权益详情
//     */
//    @POST("getLegalRightDetail")
//    Observable<GetLegalRightDetailResponse> getLegalRightDetail(@Body GetLegalRightDetailRequest getLegalRightDetailRequest);
//
//    /**
//     * 获取增值服务栏目类别
//     */
//    @POST("getValueAddServiceType")
//    Observable<GetValueAddServiceTypeResponse> getValueAddServiceType(@Body GetValueAddServiceTypeRequest getValueAddServiceTypeRequest);
//
//    /**
//     * 获取增值服务栏目内容
//     */
//    @POST("getValueAddService")
//    Observable<GetValueAddServiceResponse> getValueAddService(@Body GetValueAddServiceRequest getValueAddServiceRequest);
//
//    /**
//     * 获取增值服务栏目内容详情
//     */
//    @POST("getValueAddServiceDetail")
//    Observable<GetValueAddServiceDetailResponse> getValueAddServiceDetail(@Body GetValueAddServiceDetailRequest getValueAddServiceDetailRequest);
//
//    /**
//     * 附件下载记录
//     */
//    @POST("")
//    Observable<AttachmentDownLoadRecordResponse> attachmentDownLoadRecord(@Body AttachmentDownLoadRecordRequest attachmentDownLoadRecordRequest);
//
//    /**
//     * 获取会员活动
//     */
//    @POST("getLeaguerActivity")
//    Observable<GetLeaguerActivityResponse> getLeaguerActivity(@Body GetLeaguerActivityRequest getLeaguerActivityRequest);
//
//    /**
//     * 获取会员活动详情
//     */
//    @POST("getLeaguerActivityDetail")
//    Observable<GetLeaguerActivityDetailResponse> getLeaguerActivityDetail(@Body GetLeaguerActivityDetailRequest getLeaguerActivityDetailRequest);
//
//    /**
//     * 获取消息/公告列表
//     */
//    @POST("getInformationList")
//    Observable<GetInformationListResponse> getInformationList(@Body GetInformationListRequest getInformationListRequest);
//
//    /**
//     * 获取消息/公告详情
//     */
//    @POST("getInformationDetail")
//    Observable<GetInformationDetailResponse> getInformationDetail(@Body GetInformationDetailRequest getInformationDetailRequest);
//
//    /**
//     * 验证手机验证码
//     */
//    @POST("checkVfcode")
//    Observable<CheckVfcodeResponse> checkVfcode(@Body CheckVfcodeRequest checkVfcodeRequest);
//
//    /**
//     * 获取图形验证码时的临时sessionid
//     */
//    @POST("active")
//    Observable<MobileResponse> getTempSessionId(@Body MobileRequest mobileRequest);
//
//    /**
//     * 检查版本更新
//     */
//    @POST("androidVersionUpdate")
//    Observable<CheckAppUpdateResponse> checkAppUpdate(@Body CheckAppUpdateRequest checkAppUpdateRequest);
//
//    /**
//     * 产品预约
//     */
//    @POST("productOrder")
//    Observable<ProductOrderResponse> productOrder(@Body ProductOrderRequest productOrderRequest);
//
//    /**
//     * 获取头像
//     */
//    @POST("getHeadAppear")
//    Observable<GetHeadAppearResponse> getHeadAppear(@Body MobileRequest request);
//
//    /**
//     * 上传头像。 接口不支持这种文件上传方式。
//     */
//    @Multipart
//    @POST("uploadHeadAppear")
//    @Deprecated
//    Observable<MobileResponse> uploadAvatar(@Part MultipartBody.Part picture, @Part("fileName") RequestBody fileName, @Part("sessionId") RequestBody sessionId);
//
//    /**
//     * 上传头像2
//     */
//    @POST("uploadHeadAppear")
//    Observable<MobileResponse> uploadAvatar(@Body UploadHeadAppearRequest request);
//
//    /**
//     * 获取合格投资者认证信息
//     */
//    @POST("getInvestorInformation")
//    Observable<GetInvestorInformationResponse> getInvestorInformation(@Body MobileRequest request);
//
//    /**
//     * 进行合格投资者认证
//     */
//    @POST("doInvestor")
//    Observable<MobileResponse> doInvestor(@Body DoInvestorRequest request);
//
//
//    /**
//     * 添加修改个人信息
//     */
//    @POST("updatePersonInfo")
//    Observable<MobileResponse> updatePersonInfo(@Body UpdatePersonInfoRequest request);
//
//    /**
//     * 获取个人信息
//     */
//    @POST("getPersonInfo")
//    Observable<GetPersonInfoResponse> getPersonInfo(@Body MobileRequest request);
//
//
//    /**
//     * 获取机构信息
//     */
//    @POST("getMechanismInfo")
//    Observable<GetMechanismInfoResponse> getMechanismInfo(@Body MobileRequest request);
//
//    /**
//     * 添加修改机构信息
//     */
//    @POST("updateMechanismInfo")
//    Observable<MobileResponse> updateMechanismInfo(@Body UpdateMechanismInfoRequest request);
//
//    /**
//     * 进行投资者转换申请
//     */
//    @POST("doInvestorConversion")
//    Observable<DoInvestorConversionResponse> doInvestorConversion(@Body MobileRequest request);
//
//    /**
//     * 查询投资者转换信息
//     */
//    @POST("getInvestorConversion")
//    Observable<GetInvestorConversionResponse> getInvestorConversion(@Body MobileRequest request);
//
//    /**
//     * 获取职业
//     */
//    @POST("getOccupation")
//    Observable<GetOccupationResponse> getOccupation(@Body MobileRequest request);
//
//    /**
//     * 获取国籍
//     */
//    @POST("getNationality")
//    Observable<GetNationalityResponse> getNationality(@Body MobileRequest request);
//
//    /**
//     * 获取职务
//     */
//    @POST("getDuties")
//    Observable<GetDutiesResponse> getDuties(@Body MobileRequest request);
//
//    /**
//     * 获取受益人
//     */
//    @POST("getBeneficiary")
//    Observable<GetBeneficiaryResponse> getBeneficiary(@Body MobileRequest request);
//
//    /**
//     * 查询财富顾问
//     */
//    @POST("getFundBrokerInfo")
//    Observable<MobileResponse> getFundBrokerInfo(@Body GetFundBrokerInfoRequest request);
//
//    /**
//     * 是否开启通知
//     */
//    @POST("isOpenNotice")
//    Observable<MobileResponse> isOpenNotice(@Body IsOpenNoticeRequest request);
//
//    /**
//     * 退出登录
//     */
//    @POST("logoff")
//    Observable<LogOffResponse> logoff(@Body LogOffRequest request);
//
//    /**
//     * 下载文件
//     *
//     * @param fileUrl 动态url
//     */
//    @Streaming
//    @GET
//    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
//
//    /**
//     * 获取披露公告详情
//     */
//    @POST("getContantDetail")
//    Observable<GetContantDetailResponse> getContantDetail(@Body GetContantDetailRequest request);
//
//    /**
//     * 获取是否有未读的增值服务
//     */
//    @POST("findUnReadAddServer")
//    Observable<FindUnReadAddServerResponse> findUnReadAddServer(@Body MobileRequest request);

}

