package constellation.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/27/13
 * Time: 10:49 PM.
 */

public class Table {
    public String tableName;
    public int columnsNumber;

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    public List<Column> columns = new ArrayList<Column>();

}
