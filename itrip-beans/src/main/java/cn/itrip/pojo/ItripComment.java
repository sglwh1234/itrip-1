package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   评论表
*/
public class ItripComment implements Serializable {
        //主键
        private Long id;
        //如果产品是酒店的话 改字段保存酒店id
        private Long hotelId;
        //商品id
        private Long productId;
        //订单id
        private Long orderId;
        //商品类型(0:旅游产品 1:酒店产品 2:机票产品)
        private Integer productType;
        //评论内容
        private String content;
        //用户编号
        private Long userId;
        //是否包含图片(当用户上传评论时检测)0:无图片 1:有图片
        private Integer isHavingImg;
        //位置评分
        private Integer positionScore;
        //设施评分
        private Integer facilitiesScore;
        //服务评分
        private Integer serviceScore;
        //卫生评分
        private Integer hygieneScore;
        //综合评分
        private Integer score;
        //出游类型
        private Long tripMode;
        //是否满意（0：有待改善 1：值得推荐）
        private Integer isOk;
        //
        private Date creationDate;
        //
        private Long createdBy;
        //
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
            public void setHotelId (Long  hotelId){
                this.hotelId=hotelId;
            }
            public  Long getHotelId(){
                return this.hotelId;
            }
            public void setProductId (Long  productId){
                this.productId=productId;
            }
            public  Long getProductId(){
                return this.productId;
            }
            public void setOrderId (Long  orderId){
                this.orderId=orderId;
            }
            public  Long getOrderId(){
                return this.orderId;
            }
            public void setProductType (Integer  productType){
                this.productType=productType;
            }
            public  Integer getProductType(){
                return this.productType;
            }
            public void setContent (String  content){
                this.content=content;
            }
            public  String getContent(){
                return this.content;
            }
            public void setUserId (Long  userId){
                this.userId=userId;
            }
            public  Long getUserId(){
                return this.userId;
            }
            public void setIsHavingImg (Integer  isHavingImg){
                this.isHavingImg=isHavingImg;
            }
            public  Integer getIsHavingImg(){
                return this.isHavingImg;
            }
            public void setPositionScore (Integer  positionScore){
                this.positionScore=positionScore;
            }
            public  Integer getPositionScore(){
                return this.positionScore;
            }
            public void setFacilitiesScore (Integer  facilitiesScore){
                this.facilitiesScore=facilitiesScore;
            }
            public  Integer getFacilitiesScore(){
                return this.facilitiesScore;
            }
            public void setServiceScore (Integer  serviceScore){
                this.serviceScore=serviceScore;
            }
            public  Integer getServiceScore(){
                return this.serviceScore;
            }
            public void setHygieneScore (Integer  hygieneScore){
                this.hygieneScore=hygieneScore;
            }
            public  Integer getHygieneScore(){
                return this.hygieneScore;
            }
            public void setScore (Integer  score){
                this.score=score;
            }
            public  Integer getScore(){
                return this.score;
            }
            public void setTripMode (Long  tripMode){
                this.tripMode=tripMode;
            }
            public  Long getTripMode(){
                return this.tripMode;
            }
            public void setIsOk (Integer  isOk){
                this.isOk=isOk;
            }
            public  Integer getIsOk(){
                return this.isOk;
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
