package com.fet.crm.osp.platform.core.db.model;
// Generated 2017/2/24 �U�� 01:10:48 by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysSkillOrderTypeMapId generated by hbm2java
 */
@Embeddable
public class SysSkillOrderTypeMapId  implements java.io.Serializable {


     private String skillId;
     private String orderTypeId;

    public SysSkillOrderTypeMapId() {
    }

    public SysSkillOrderTypeMapId(String skillId, String orderTypeId) {
       this.skillId = skillId;
       this.orderTypeId = orderTypeId;
    }
   


    @Column(name="SKILL_ID", nullable=false, length=40)
    public String getSkillId() {
        return this.skillId;
    }
    
    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }


    @Column(name="ORDER_TYPE_ID", nullable=false, length=40)
    public String getOrderTypeId() {
        return this.orderTypeId;
    }
    
    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysSkillOrderTypeMapId) ) return false;
		 SysSkillOrderTypeMapId castOther = ( SysSkillOrderTypeMapId ) other; 
         
		 return ( (this.getSkillId()==castOther.getSkillId()) || ( this.getSkillId()!=null && castOther.getSkillId()!=null && this.getSkillId().equals(castOther.getSkillId()) ) )
 && ( (this.getOrderTypeId()==castOther.getOrderTypeId()) || ( this.getOrderTypeId()!=null && castOther.getOrderTypeId()!=null && this.getOrderTypeId().equals(castOther.getOrderTypeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSkillId() == null ? 0 : this.getSkillId().hashCode() );
         result = 37 * result + ( getOrderTypeId() == null ? 0 : this.getOrderTypeId().hashCode() );
         return result;
   }   


}

