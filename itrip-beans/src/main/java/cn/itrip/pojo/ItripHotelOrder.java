package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   订单表
*/
public class ItripHotelOrder implements Serializable {
        //主键
        private Long id;
        //用户id
        private Long userId;
        //订单类型(0:旅游产品 1:酒店产品 2：机票产品)
        private Integer orderType;
        //订单号
        private String orderNo;
        //交易编号
        private String tradeNo;
        //冗余字段 酒店id
        private Long hotelId;
        //冗余字段 酒店名称
        private String hotelName;
        //房间id
        private Long roomId;
        //消耗数量
        private Integer count;
        //预订天数
        private Integer bookingDays;
        //入住时间
        private Date checkInDate;
        //退房时间
        private Date checkOutDate;
        //订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
        private Integer orderStatus;
        //支付金额
        private Double payAmount;
        //支付方式:1:支付宝 2:微信 3:到店付
        private Integer payType;
        //联系手机号
        private String noticePhone;
        //联系邮箱
        private String noticeEmail;
        //特殊需求
        private String specialRequirement;
        //是否需要发票（0：不需要 1：需要）
        private Integer isNeedInvoice;
        //发票类型（0：个人 1：公司）
        private Integer invoiceType;
        //发票抬头
        private String invoiceHead;
        //入住人名称
        private String linkUserName;
        //0:WEB端 1:手机端 2:其他客户端
        private Integer bookType;
        //预定时间
        private Date creationDate;
        //
        private Long createdBy;
        //支付完成时间
        private Date modifyDate;
        //
        private Long modifiedBy;
        //get set 方法
            public void setId (Long  id){
                this.id=id;
            }
            public  Long getId(){
                return this.id;
            }
            public void setUserId (Long  userId){
                this.userId=userId;
            }
            public  Long getUserId(){
                return this.userId;
            }
            public void setOrderType (Integer  orderType){
                this.orderType=orderType;
            }
            public  Integer getOrderType(){
                return this.orderType;
            }
            public void setOrderNo (String  orderNo){
                this.orderNo=orderNo;
            }
            public  String getOrderNo(){
                return this.orderNo;
            }
            public void setTradeNo (String  tradeNo){
                this.tradeNo=tradeNo;
            }
            public  String getTradeNo(){
                return this.tradeNo;
            }
            public void setHotelId (Long  hotelId){
                this.hotelId=hotelId;
            }
            public  Long getHotelId(){
                return this.hotelId;
            }
            public void setHotelName (String  hotelName){
                this.hotelName=hotelName;
            }
            public  String getHotelName(){
                return this.hotelName;
            }
            public void setRoomId (Long  roomId){
                this.roomId=roomId;
            }
            public  Long getRoomId(){
                return this.roomId;
            }
            public void setCount (Integer  count){
                this.count=count;
            }
            public  Integer getCount(){
                return this.count;
            }
            public void setBookingDays (Integer  bookingDays){
                this.bookingDays=bookingDays;
            }
            public  Integer getBookingDays(){
                return this.bookingDays;
            }
            public void setCheckInDate (Date  checkInDate){
                this.checkInDate=checkInDate;
            }
            public  Date getCheckInDate(){
                return this.checkInDate;
            }
            public void setCheckOutDate (Date  checkOutDate){
                this.checkOutDate=checkOutDate;
            }
            public  Date getCheckOutDate(){
                return this.checkOutDate;
            }
            public void setOrderStatus (Integer  orderStatus){
                this.orderStatus=orderStatus;
            }
            public  Integer getOrderStatus(){
                return this.orderStatus;
            }
            public void setPayAmount (Double  payAmount){
                this.payAmount=payAmount;
            }
            public  Double getPayAmount(){
                return this.payAmount;
            }
            public void setPayType (Integer  payType){
                this.payType=payType;
            }
            public  Integer getPayType(){
                return this.payType;
            }
            public void setNoticePhone (String  noticePhone){
                this.noticePhone=noticePhone;
            }
            public  String getNoticePhone(){
                return this.noticePhone;
            }
            public void setNoticeEmail (String  noticeEmail){
                this.noticeEmail=noticeEmail;
            }
            public  String getNoticeEmail(){
                return this.noticeEmail;
            }
            public void setSpecialRequirement (String  specialRequirement){
                this.specialRequirement=specialRequirement;
            }
            public  String getSpecialRequirement(){
                return this.specialRequirement;
            }
            public void setIsNeedInvoice (Integer  isNeedInvoice){
                this.isNeedInvoice=isNeedInvoice;
            }
            public  Integer getIsNeedInvoice(){
                return this.isNeedInvoice;
            }
            public void setInvoiceType (Integer  invoiceType){
                this.invoiceType=invoiceType;
            }
            public  Integer getInvoiceType(){
                return this.invoiceType;
            }
            public void setInvoiceHead (String  invoiceHead){
                this.invoiceHead=invoiceHead;
            }
            public  String getInvoiceHead(){
                return this.invoiceHead;
            }
            public void setLinkUserName (String  linkUserName){
                this.linkUserName=linkUserName;
            }
            public  String getLinkUserName(){
                return this.linkUserName;
            }
            public void setBookType (Integer  bookType){
                this.bookType=bookType;
            }
            public  Integer getBookType(){
                return this.bookType;
            }
            public void setCreationDate (Date  creationDate){
                this.creationDate=creationDate;
            }
            public  Date getCreationDate(){
                return this.creationDate;
            }
            public void setCreatedBy (Long  createdBy){
                this.createdBy=createdBy;
            }
            public  Long getCreatedBy(){
                return this.createdBy;
            }
            public void setModifyDate (Date  modifyDate){
                this.modifyDate=modifyDate;
            }
            public  Date getModifyDate(){
                return this.modifyDate;
            }
            public void setModifiedBy (Long  modifiedBy){
                this.modifiedBy=modifiedBy;
            }
            public  Long getModifiedBy(){
                return this.modifiedBy;
            }
}
