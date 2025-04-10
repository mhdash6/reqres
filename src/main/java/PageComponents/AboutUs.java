package PageComponents;

import BasePage.BasePage;
import org.openqa.selenium.By;
import utilities.Waits;
import static utilities.Gets.getInnerText;



public class AboutUs<T> extends BasePage<T> {
    private final T currentPage;
    final By closeBtn= By.cssSelector("#videoModal .modal-footer > button");
    final By exitBtn= By.cssSelector("#videoModal button.close");
    final By errorMsg = By.cssSelector("div[aria-label='Modal Window'] div[role='document']");
    final By body = By.id("videoModal");

    AboutUs(T currentPage){
        this.currentPage=currentPage;
        Waits.waitForVisibility(body,1);
    }

    public String getErrorMsg(){
        return getInnerText(errorMsg);
    }

    public T clickClose(){
        click(closeBtn);
        return currentPage;
    }

    public void clickExit(){
        click(exitBtn);
    }
}
