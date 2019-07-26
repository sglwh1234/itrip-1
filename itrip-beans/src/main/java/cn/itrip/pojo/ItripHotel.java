package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   酒店表
*/
public class ItripHotel implements Serializable {
        //主键
        private Long id;
        //酒店名称
        private String hotelName;
        //所在国家id
        private Long countryId;
        //所在省份id
        private Long provinceId;
        //所在城市id
        private Long cityId;
        //酒店所在地址
        private String address;
        //酒店介绍（保存附文本）
        private String details;
        //酒店设施
        private String facilities;
        //酒店政策
        private String hotelPolicy;
        //酒店类型(1:国内酒店 2:国际酒店)
        private Integer hotelType;
        //(1:经济酒店  2:二星级酒店  3:三星级 4:四星酒店 5星酒店)
        private Integer hotelLevel;
        //是否是团购酒店
        private Integer isGroupPurchase;
        //城市名称 冗余字段
        private String redundantCityName;
        //省名称 冗余字段
        private String redundantProvinceName;
        //国家名称 冗余字段
        private String redundantCountryName;
        //酒店库存（冗余，每天开定时任务的时候更新）
        private Integer redundantHotelStore;
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
            public void setHotelName (String  hotelName){
                this.hotelName=hotelName;
            }
            public  String getHotelName(){
                return this.hotelName;
            }
            public void setCountryId (Long  countryId){
                this.countryId=countryId;
            }
            public  Long getCountryId(){
                return this.countryId;
            }
            public void setProvinceId (Long  provinceId){
                this.provinceId=provinceId;
            }
            public  Long getProvinceId(){
                return this.provinceId;
            }
            public void setCityId (Long  cityId){
                this.cityId=cityId;
            }
            public  Long getCityId(){
                return this.cityId;
            }
            public void setAddress (String  address){
                this.address=address;
            }
            public  String getAddress(){
                return this.address;
            }
            public void setDetails (String  details){
                this.details=details;
            }
            public  String getDetails(){
                return this.details;
            }
            public void setFacilities (String  facilities){
                this.facilities=facilities;
            }
            public  String getFacilities(){
                return this.facilities;
            }
            public void setHotelPolicy (String  hotelPolicy){
                this.hotelPolicy=hotelPolicy;
            }
            public  String getHotelPolicy(){
                return this.hotelPolicy;
            }
            public void setHotelType (Integer  hotelType){
                this.hotelType=hotelType;
            }
            public  Integer getHotelType(){
                return this.hotelType;
            }
            public void setHotelLevel (Integer  hotelLevel){
                this.hotelLevel=hotelLevel;
            }
            public  Integer getHotelLevel(){
                return this.hotelLevel;
            }
            public void setIsGroupPurchase (Integer  isGroupPurchase){
                this.isGroupPurchase=isGroupPurchase;
            }
            public  Integer getIsGroupPurchase(){
                return this.isGroupPurchase;
            }
            public void setRedundantCityName (String  redundantCityName){
                this.redundantCityName=redundantCityName;
            }
            public  String getRedundantCityName(){
                return this.redundantCityName;
            }
            public void setRedundantProvinceName (String  redundantProvinceName){
                this.redundantProvinceName=redundantProvinceName;
            }
            public  String getRedundantProvinceName(){
                return this.redundantProvinceName;
            }
            public void setRedundantCountryName (String  redundantCountryName){
                this.redundantCountryName=redundantCountryName;
            }
            public  String getRedundantCountryName(){
                return this.redundantCountryName;
            }
            public void setRedundantHotelStore (Integer  redundantHotelStore){
                this.redundantHotelStore=redundantHotelStore;
            }
            public  Integer getRedundantHotelStore(){
                return this.redundantHotelStore;
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
