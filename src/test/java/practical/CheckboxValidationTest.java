package practical;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckboxValidationTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
    }

    @Test
    public void checkboxIdentificationAndValidation() {

        String filePath = new File(
                "src/test/resources/checkboxes.html"
        ).getAbsolutePath();

        driver.get("file://" + filePath);

        // Identify all checkboxes
        List<WebElement> checkboxes =
                driver.findElements(By.cssSelector("input[type='checkbox']"));

        int totalCheckboxes = checkboxes.size();
        int checkedCount = 0;
        int uncheckedCount = 0;

        // Count checked and unchecked checkboxes
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkedCount++;
            } else {
                uncheckedCount++;
            }
        }

        System.out.println("====================================");
        System.out.println("CHECKBOX IDENTIFICATION AND VALIDATION");
        System.out.println("====================================");
        System.out.println("Total checkboxes   : " + totalCheckboxes);
        System.out.println("Checked checkboxes : " + checkedCount);
        System.out.println("Unchecked checkboxes: " + uncheckedCount);

        // Validate initial counts
        assertEquals(6, totalCheckboxes);
        assertEquals(2, checkedCount);
        assertEquals(4, uncheckedCount);

        // Dynamic selection
        WebElement pythonCheckbox =
                driver.findElement(By.cssSelector(
                        "input[value='Python']"));

        assertTrue(!pythonCheckbox.isSelected());

        pythonCheckbox.click();

        System.out.println("Python checkbox selected: "
                + pythonCheckbox.isSelected());

        assertTrue(pythonCheckbox.isSelected());

        // Dynamic deselection
        WebElement javaCheckbox =
                driver.findElement(By.cssSelector(
                        "input[value='Java']"));

        assertTrue(javaCheckbox.isSelected());

        javaCheckbox.click();

        System.out.println("Java checkbox selected after deselection: "
                + javaCheckbox.isSelected());

        assertTrue(!javaCheckbox.isSelected());

        // Recalculate final counts
        checkedCount = 0;
        uncheckedCount = 0;

        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                checkedCount++;
            } else {
                uncheckedCount++;
            }
        }

        System.out.println("------------------------------------");
        System.out.println("FINAL CHECKBOX STATUS");
        System.out.println("Checked checkboxes : " + checkedCount);
        System.out.println("Unchecked checkboxes: " + uncheckedCount);
        System.out.println("------------------------------------");

        // Validate final counts
        assertEquals(2, checkedCount);
        assertEquals(4, uncheckedCount);

        System.out.println("All checkbox validations passed.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
