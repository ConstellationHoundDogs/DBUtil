package constellation.filecreator;

import constellation.DBUtil;
import constellation.vo.DBInfo;
import constellation.vo.Table;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: vladimir
 * Date: 11/30/13
 * Time: 10:07 AM
 */
public class PlainTextFileCreator implements FileCreator {

    /**
     * Creates "PlainTextFile" containing database information displayed in text format
     * @param dbInfo
     */
    @Override
    public void createFile(DBInfo dbInfo) {
        File file = new File("PlainTextFile");
        try {
            file.createNewFile();
        } catch (IOException e) {
            DBUtil.logger.error(e.getMessage());
        }
        try {
            System.setOut(new PrintStream(file));
            textDBInfoOutput(dbInfo);
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        } catch (FileNotFoundException e) {
            DBUtil.logger.error(e.getMessage());
        }
    }

    private void textDBInfoOutput(DBInfo dbInfo) {
        System.out.println(dbInfo.getDataBaseName());
        System.out.println(dbInfo.getDataBaseVersion());

        for(Table t : dbInfo.tables){
            System.out.println(t.tableName);
            System.out.println(t.columnsNumber);
            for(int i = 0; i < t.columns.size(); i++){
                System.out.print(t.columns.get(i).columnType + " ");
            }
            System.out.println();
            for(int i = 0; i < t.columns.get(0).content.size(); i++){
                for(int j = 0; j < t.columnsNumber; j++){
                    System.out.print(t.columns.get(j).content.get(i) + " ");
                }
                System.out.println();
            }
        }
    }

}
