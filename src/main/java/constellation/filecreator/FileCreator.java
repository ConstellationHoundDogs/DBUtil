package constellation.filecreator;

import constellation.vo.DBInfo;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/29/13
 * Time: 5:08 PM
 */
public interface FileCreator {
    public void createFile(DBInfo dbInfo) throws IOException, JAXBException;
}
