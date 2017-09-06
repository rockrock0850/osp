package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/4/19 �W�� 01:21:01 by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BuzContentItemMapId generated by hbm2java
 */
@Embeddable
public class BuzContentItemMapId  implements java.io.Serializable {


     private String contentId;
     private String itemId;

    public BuzContentItemMapId() {
    }

    public BuzContentItemMapId(String contentId, String itemId) {
       this.contentId = contentId;
       this.itemId = itemId;
    }
   


    @Column(name="CONTENT_ID", nullable=false, length=20)
    public String getContentId() {
        return this.contentId;
    }
    
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }


    @Column(name="ITEM_ID", nullable=false, length=20)
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BuzContentItemMapId) ) return false;
		 BuzContentItemMapId castOther = ( BuzContentItemMapId ) other; 
         
		 return ( (this.getContentId()==castOther.getContentId()) || ( this.getContentId()!=null && castOther.getContentId()!=null && this.getContentId().equals(castOther.getContentId()) ) )
 && ( (this.getItemId()==castOther.getItemId()) || ( this.getItemId()!=null && castOther.getItemId()!=null && this.getItemId().equals(castOther.getItemId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getContentId() == null ? 0 : this.getContentId().hashCode() );
         result = 37 * result + ( getItemId() == null ? 0 : this.getItemId().hashCode() );
         return result;
   }   


}

