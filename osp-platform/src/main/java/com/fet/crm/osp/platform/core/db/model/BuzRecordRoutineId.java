package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/2/24 �U�� 01:10:48 by Hibernate Tools 4.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BuzRecordRoutineId generated by hbm2java
 */
@Embeddable
public class BuzRecordRoutineId  implements java.io.Serializable {


     private String processUserId;
     private Date processDate;
     private String contentId;
     private String itemId;

    public BuzRecordRoutineId() {
    }

    public BuzRecordRoutineId(String processUserId, Date processDate, String contentId, String itemId) {
       this.processUserId = processUserId;
       this.processDate = processDate;
       this.contentId = contentId;
       this.itemId = itemId;
    }
   


    @Column(name="PROCESS_USER_ID", nullable=false, length=20)
    public String getProcessUserId() {
        return this.processUserId;
    }
    
    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }


    @Column(name="PROCESS_DATE", nullable=false, length=7)
    public Date getProcessDate() {
        return this.processDate;
    }
    
    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
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
		 if ( !(other instanceof BuzRecordRoutineId) ) return false;
		 BuzRecordRoutineId castOther = ( BuzRecordRoutineId ) other; 
         
		 return ( (this.getProcessUserId()==castOther.getProcessUserId()) || ( this.getProcessUserId()!=null && castOther.getProcessUserId()!=null && this.getProcessUserId().equals(castOther.getProcessUserId()) ) )
 && ( (this.getProcessDate()==castOther.getProcessDate()) || ( this.getProcessDate()!=null && castOther.getProcessDate()!=null && this.getProcessDate().equals(castOther.getProcessDate()) ) )
 && ( (this.getContentId()==castOther.getContentId()) || ( this.getContentId()!=null && castOther.getContentId()!=null && this.getContentId().equals(castOther.getContentId()) ) )
 && ( (this.getItemId()==castOther.getItemId()) || ( this.getItemId()!=null && castOther.getItemId()!=null && this.getItemId().equals(castOther.getItemId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProcessUserId() == null ? 0 : this.getProcessUserId().hashCode() );
         result = 37 * result + ( getProcessDate() == null ? 0 : this.getProcessDate().hashCode() );
         result = 37 * result + ( getContentId() == null ? 0 : this.getContentId().hashCode() );
         result = 37 * result + ( getItemId() == null ? 0 : this.getItemId().hashCode() );
         return result;
   }   


}


