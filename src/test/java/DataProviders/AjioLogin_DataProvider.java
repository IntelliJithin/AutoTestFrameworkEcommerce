package DataProviders;

import org.testng.annotations.DataProvider;

public class AjioLogin_DataProvider {

    @DataProvider(name = "AjioLoginData")
    Object[][] getData1(){
        return new String[][]{
                {"username@gmail.com", "passwword", "T-shirt", "Jithin Ravindran",
                 "+911234567890", "680008", "street", "homename"}
        };
    }

    @DataProvider(name = "AjioEditedData")
    Object[][] getData2(){
        return new String[][]{
                {"username@gmail.com", "password", "T-shirt", "streetEdited", "homenameEdited"}
        };
    }

    @DataProvider(name = "AjioData_TC_004")
    Object[][] getData3(){
        return new String[][]{
                {"T-shirt", "Tops", "Track Pants"}
        };
    }

    @DataProvider(name = "AjioData_TC_013")
    Object[][] getData8(){
        return new String[][]{
                {"T-shirt", "Tops"}
        };
    }

    @DataProvider(name = "AjioData_TC")
    Object[][] getData4(){
        return new String[][]{
                {"T-shirt", "invalid"}
        };
    }

    @DataProvider(name = "AjioLogin")
    Object[][] getData5(){
        return new String[][]{
                {"username@gmail.com", "password"}
        };
    }

    @DataProvider(name = "AjioData_TC_003")
    Object[][] getData6(){
        return new String[][]{
                {"Jackets", "Item(s) added to your bag"}
        };
    }

    @DataProvider(name = "AjioData_TC_000")
    Object[][] getData7(){
        return new String[][]{
                {"T Shirt"}
        };
    }
}
