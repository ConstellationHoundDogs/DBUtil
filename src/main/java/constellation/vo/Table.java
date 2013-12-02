package constellation.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:49 PM.
 */

public class Table {


    private String tableID;
    private String tableName;
    private List<Column> columns = new ArrayList<Column>();
    private int columnsNumber;

    @XmlTransient
    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    @XmlElement
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @XmlElement
    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    public List<Column> getColumns() {
        return columns;
    }

}
