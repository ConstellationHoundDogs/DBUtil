package constellation.filecreator;

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
     * @param dbInfo VO containing Database information
     */
    @Override
    public void createFile(DBInfo dbInfo) throws IOException {
        File file = new File("PlainTextFile");

        file.createNewFile();


        PrintStream printStream = new PrintStream(new FileOutputStream(file));
        textDBInfoOutput(dbInfo, printStream);

    }

    private void textDBInfoOutput(DBInfo dbInfo, PrintStream printStream) {
        printStream.println(dbInfo.getDataBaseName());
        printStream.println(dbInfo.getDataBaseVersion());

        for(Table t : dbInfo.getTables()){
            printStream.println(t.getTableName());
            printStream.println(t.getColumnsNumber());
            for(int i = 0; i < t.getColumns().size(); i++){
                printStream.print(t.getColumns().get(i).getColumnType() + " ");
            }
            printStream.println();
            for(int i = 0; i < t.getColumns().get(0).getContent().size(); i++){
                for(int j = 0; j < t.getColumnsNumber(); j++){
                    printStream.print(t.getColumns().get(j).getContent().get(i) + " ");
                }
                printStream.println();
            }
        }
    }
}
