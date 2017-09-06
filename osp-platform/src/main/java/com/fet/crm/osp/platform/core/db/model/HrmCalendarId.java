package com.fet.crm.osp.platform.core.db.model;
// Generated May 10, 2017 12:40:59 AM by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HrmCalendarId generated by hbm2java
 */
@Embeddable
public class HrmCalendarId  implements java.io.Serializable {


     private String dtYear;
     private String dtMon;
     private String dtDay;

    public HrmCalendarId() {
    }

    public HrmCalendarId(String dtYear, String dtMon, String dtDay) {
       this.dtYear = dtYear;
       this.dtMon = dtMon;
       this.dtDay = dtDay;
    }
   


    @Column(name="DT_YEAR", nullable=false, length=4)
    public String getDtYear() {
        return this.dtYear;
    }
    
    public void setDtYear(String dtYear) {
        this.dtYear = dtYear;
    }


    @Column(name="DT_MON", nullable=false, length=2)
    public String getDtMon() {
        return this.dtMon;
    }
    
    public void setDtMon(String dtMon) {
        this.dtMon = dtMon;
    }


    @Column(name="DT_DAY", nullable=false, length=2)
    public String getDtDay() {
        return this.dtDay;
    }
    
    public void setDtDay(String dtDay) {
        this.dtDay = dtDay;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HrmCalendarId) ) return false;
		 HrmCalendarId castOther = ( HrmCalendarId ) other; 
         
		 return ( (this.getDtYear()==castOther.getDtYear()) || ( this.getDtYear()!=null && castOther.getDtYear()!=null && this.getDtYear().equals(castOther.getDtYear()) ) )
 && ( (this.getDtMon()==castOther.getDtMon()) || ( this.getDtMon()!=null && castOther.getDtMon()!=null && this.getDtMon().equals(castOther.getDtMon()) ) )
 && ( (this.getDtDay()==castOther.getDtDay()) || ( this.getDtDay()!=null && castOther.getDtDay()!=null && this.getDtDay().equals(castOther.getDtDay()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDtYear() == null ? 0 : this.getDtYear().hashCode() );
         result = 37 * result + ( getDtMon() == null ? 0 : this.getDtMon().hashCode() );
         result = 37 * result + ( getDtDay() == null ? 0 : this.getDtDay().hashCode() );
         return result;
   }   


}


