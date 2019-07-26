package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   区域字典表
*/
public class ItripAreaDic implements Serializable {
        //主键
        private Long id;
        //区域名称
        private String name;
        //区域编号
        private String areaNo;
        //父级区域
        private Long parent;
        //0:未激活 1:已激活
        private Integer isActivated;
        //是否是商圈(0:不是 1:是)
        private Integer isTradingArea;
        //(0:不是 1：是)
        private Integer isHot;
        //区域级别(0:国家级 1:省级 2:市级 3:县/区)
        private Integer level;
        //1:国内 2：国外
        private Integer isChina;
        //
        private String pinyin;
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
            public void setName (String  name){
                this.name=name;
            }
            public  String getName(){
                return this.name;
            }
            public void setAreaNo (String  areaNo){
                this.areaNo=areaNo;
            }
            public  String getAreaNo(){
                return this.areaNo;
            }
            public void setParent (Long  parent){
                this.parent=parent;
            }
            public  Long getParent(){
                return this.parent;
            }
            public void setIsActivated (Integer  isActivated){
                this.isActivated=isActivated;
            }
            public  Integer getIsActivated(){
                return this.isActivated;
            }
            public void setIsTradingArea (Integer  isTradingArea){
                this.isTradingArea=isTradingArea;
            }
            public  Integer getIsTradingArea(){
                return this.isTradingArea;
            }
            public void setIsHot (Integer  isHot){
                this.isHot=isHot;
            }
            public  Integer getIsHot(){
                return this.isHot;
            }
            public void setLevel (Integer  level){
                this.level=level;
            }
            public  Integer getLevel(){
                return this.level;
            }
            public void setIsChina (Integer  isChina){
                this.isChina=isChina;
            }
            public  Integer getIsChina(){
                return this.isChina;
            }
            public void setPinyin (String  pinyin){
                this.pinyin=pinyin;
            }
            public  String getPinyin(){
                return this.pinyin;
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
