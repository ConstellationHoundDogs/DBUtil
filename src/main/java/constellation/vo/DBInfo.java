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
public class DBInfo {


    private String dataBaseName;
    private String dataBaseVersion;

    private List<String> schemaNames = new ArrayList<String>();
    private List<Table> tables = new ArrayList<Table>();

    @XmlElement
    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    @XmlElement
    public String getDataBaseVersion() {
        return dataBaseVersion;
    }


    public void setDataBaseVersion(String dataBaseVersion) {
        this.dataBaseVersion = dataBaseVersion;
    }

    @XmlElementWrapper(name = "schemas")
    @XmlElement (name = "schema")
    public List<String> getSchemaNames() {
        return schemaNames;
    }

    @XmlElementWrapper(name = "tables")
    @XmlElement (name = "table")
    public List<Table> getTables() {
        return tables;
    }

}
