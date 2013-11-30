package constellation.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:48 PM
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DBInfo {

    @XmlElement
    private String dataBaseName;
    @XmlElement
    private String dataBaseVersion;

    @XmlElementWrapper(name = "schemas")
    @XmlElement(name = "schema")
    public List<String> schemaNames = new ArrayList<String>();

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    public List<Table> tables = new ArrayList<Table>();


    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }


    public String getDataBaseVersion() {
        return dataBaseVersion;
    }


    public void setDataBaseVersion(String dataBaseVersion) {
        this.dataBaseVersion = dataBaseVersion;
    }

    public List<String> getSchemaNames() {
        return schemaNames;
    }

    public void setSchemaNames(List<String> schemaNames) {
        this.schemaNames = schemaNames;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
