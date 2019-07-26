package cn.itrip.pojo;
import java.io.Serializable;
import java.util.Date;
/***
*   图片表
*/
public class ItripImage implements Serializable {
        //主键
        private Long id;
        //图片类型(0:酒店图片1:房间图片2:评论图片)
        private String type;
        //关联id
        private Long targetId;
        //图片s上传顺序位置
        private Integer position;
        //图片地址
        private String imgUrl;
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
            public void setType (String  type){
                this.type=type;
            }
            public  String getType(){
                return this.type;
            }
            public void setTargetId (Long  targetId){
                this.targetId=targetId;
            }
            public  Long getTargetId(){
                return this.targetId;
            }
            public void setPosition (Integer  position){
                this.position=position;
            }
            public  Integer getPosition(){
                return this.position;
            }
            public void setImgUrl (String  imgUrl){
                this.imgUrl=imgUrl;
            }
            public  String getImgUrl(){
                return this.imgUrl;
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
