package constellation.filecreator;

import constellation.DBUtil;
import constellation.vo.DBInfo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/29/13
 * Time: 5:09 PM
 */

public class XMLFileCreator implements FileCreator {
    @Override
    public void createFile(DBInfo dbInfo) {
        File xmlFile = new File("XMLFile");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DBInfo.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dbInfo, xmlFile);
        } catch (JAXBException e) {
            DBUtil.logger.error("Error in XMLFileCreator:" + e.getMessage());
        }
    }
}
