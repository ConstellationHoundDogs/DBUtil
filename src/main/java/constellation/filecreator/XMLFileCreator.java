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

    /**
     * Creates "XMLFile" containing database information displayed in XML format
     * @param dbInfo VO containing Database information
     */
    @Override
    public void createFile(DBInfo dbInfo) throws JAXBException {
        File xmlFile = new File("XMLFile");
        JAXBContext jaxbContext = JAXBContext.newInstance(DBInfo.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dbInfo, xmlFile);
    }
}
