package constellation.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:48 PM
 */
@XmlRootElement
public class DBInfo {

    @XmlElement
    public String dataBaseName;
    @XmlElement
    public String dataBaseVersion;

    @XmlElementWrapper(name = "schemas")
    @XmlElement(name = "schema")
    public List<String> schemaNames = new ArrayList<String>();

    @XmlElementWrapper(name = "tables")
    @XmlElement(name = "table")
    public List<Table> tables = new ArrayList<Table>();

}
