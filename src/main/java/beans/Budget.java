/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import annotations.GUIEditable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Bernhard1
 */
@Table
@Entity
@IdClass(BudgetPK.class)
public class Budget implements Serializable {
    

    @OneToOne
    @Id
    @GUIEditable(name = "Kategorie", showType = GUIEditable.ShowType.COMBOBOX)
    private Category category;
    @Column
    @GUIEditable(name = "Budget pro Monat")
    private BigDecimal budgetValue;
    @Id
    @Column
    private int month;
    @Id
    @Column
    private int year;

    @Transient
    Date validFromAsDate;

    public Budget() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        this.month = cal.get(Calendar.MONTH)+1;
        this.year = cal.get(Calendar.YEAR);
    }

    public Budget(Category category, BigDecimal budgetValue, int month, int year) {
        this.category = category;
        this.budgetValue = budgetValue;
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        this.month = cal.get(Calendar.MONTH)+1;
        this.year = cal.get(Calendar.YEAR);
    }

    

    
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getBudgetValue() {
        return budgetValue;
    }
    
    public String getBudgetValueAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(budgetValue.doubleValue());
    }

    public void setBudgetValue(BigDecimal budgetValue) {
        this.budgetValue = budgetValue;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int validFromMonth) {
        this.month = validFromMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int validFromYear) {
        this.year = validFromYear;
    }


    public Date getValidFromAsDate(){
        
        validFromAsDate = new Date(year -1900, month -1, 1);
        
        return validFromAsDate;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Budget other = (Budget) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "category=" + category +
                ", budgetValue=" + budgetValue +
                ", validFromMonth=" + month +
                ", validFromYear=" + year +
                ", validFromAsDate=" + validFromAsDate +
                '}';
    }
}
