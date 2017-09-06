package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/2/24 �U�� 01:10:48 by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BuzRecordContentId generated by hbm2java
 */
@Embeddable
public class BuzRecordContentId  implements java.io.Serializable {


     private String orderMId;
     private String contentId;
     private String itemId;

    public BuzRecordContentId() {
    }

    public BuzRecordContentId(String orderMId, String contentId, String itemId) {
       this.orderMId = orderMId;
       this.contentId = contentId;
       this.itemId = itemId;
    }
   


    @Column(name="ORDER_M_ID", nullable=false, length=36)
    public String getOrderMId() {
        return this.orderMId;
    }
    
    public void setOrderMId(String orderMId) {
        this.orderMId = orderMId;
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
		 if ( !(other instanceof BuzRecordContentId) ) return false;
		 BuzRecordContentId castOther = ( BuzRecordContentId ) other; 
         
		 return ( (this.getOrderMId()==castOther.getOrderMId()) || ( this.getOrderMId()!=null && castOther.getOrderMId()!=null && this.getOrderMId().equals(castOther.getOrderMId()) ) )
 && ( (this.getContentId()==castOther.getContentId()) || ( this.getContentId()!=null && castOther.getContentId()!=null && this.getContentId().equals(castOther.getContentId()) ) )
 && ( (this.getItemId()==castOther.getItemId()) || ( this.getItemId()!=null && castOther.getItemId()!=null && this.getItemId().equals(castOther.getItemId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getOrderMId() == null ? 0 : this.getOrderMId().hashCode() );
         result = 37 * result + ( getContentId() == null ? 0 : this.getContentId().hashCode() );
         result = 37 * result + ( getItemId() == null ? 0 : this.getItemId().hashCode() );
         return result;
   }   


}


