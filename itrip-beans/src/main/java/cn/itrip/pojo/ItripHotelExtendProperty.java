package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   
*/
public class ItripHotelExtendProperty implements Serializable {
        //
        private Long id;
        //酒店id
        private Long hotelId;
        //推广属性
        private Long extendPropertyId;
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
            public void setExtendPropertyId (Long  extendPropertyId){
                this.extendPropertyId=extendPropertyId;
            }
            public  Long getExtendPropertyId(){
                return this.extendPropertyId;
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
