package DataProviders;

import Utilities.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class Ajio_DataProvider {

    @DataProvider(name = "AjioData")
    Object[][] getData() throws IOException {
        {
            String path = "src/test/java/TestData/AjioData.xlsx";
            int rownum = ExcelUtils.getRowCount(path, "Sheet1");
            int colcount = ExcelUtils.getCellCount(path, "Sheet1", 2);
            String[][] ajioData = new String[rownum][colcount];
            int i=1;
                for (int j = 0; j < colcount; j++)
                {
                    ajioData[i][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
                }
            return ajioData;
        }
    }
}