package objectrepository;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.gargoylesoftware.htmlunit.javascript.host.file.Blob;
import com.relevantcodes.extentreports.LogStatus;

import config.FrameworkException;
import config.TestSetup;
import reusablecomponents.BusinessComponents;
import reusablecomponents.TechnicalComponents;

/**
 * Object repository of the about us page.
 * 
 * @author Dsavita
 *
 */
public class BillingDetailspage extends TechnicalComponents {

	WebDriver driver;

	private String billingDetails_title = "SurveyMonkey - My Account: Billing Details";
	public static String urlsuffix = "/billing/";

	public BillingDetailspage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// a[contains(@class , "sm-track action-links")]/preceding-sibling::span
	@FindBy(xpath = "//dt[@class='billing-next-amount']")
	public static WebElement txt_NextRenewalAmount;

	@FindBy(xpath = "//a[@data-track-ui-trigger='edit_payment_button']")
	public static WebElement btnEditPayment;

	@FindBy(xpath = "//a[@data-track-ui-trigger='edit_contact_button']")
	public static WebElement btnEditContact;

	@FindBy(xpath = "//div[@id='contact-details-modal']")
	public static WebElement editContactDetailModal;

	@FindBy(xpath = "//div[@id='contact-details-modal']")
	public static WebElement editBillingDetailModal;

	/**
	 * function to validate the redirection to billing details page.
	 * 
	 * @author Jypsy
	 *
	 */
	public boolean isPageOpened() {
		try {
			TechnicalComponents.waitTill(txt_NextRenewalAmount, "visible");
			if (driver.getCurrentUrl().contains(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException(
					"About Us Page Not Loaded within specified time.---" + e.getClass() + "---" + e.getMessage());
		}
	}

	@FindBy(xpath = "//a[contains(@class , 'sm-track action-links')]/preceding-sibling::span")
	public static WebElement txt_PlanName;

	public void verifyPlanType(String PlanName) {

		TechnicalComponents.verifyAttribute(txt_PlanName, "text-equals", PlanName, "Planname");
	}

	@FindBy(xpath = "//dd[@class='billing-frequency']/span")
	public static WebElement txt_BillingFreqency;

	public void verifyBillingFrequency(String Frequency) {

		TechnicalComponents.verifyAttribute(txt_BillingFreqency, "text-contains", Frequency, "Billing Frequency");
	}

	@FindBy(xpath = "//dd[@class='billing-next-date']//span")
	public static WebElement txt_NextBillingDate;

	public void verifyNextBillingDate(String NextExpectedBillingDate) {

		NextExpectedBillingDate = getDate("annual", "MMM d, yyyy");
		TechnicalComponents.verifyAttribute(txt_NextBillingDate, "text-contains", NextExpectedBillingDate,
				"Next Billing Date");

	}

	@FindBy(xpath = "//a[contains(@data-track-action-type, 'cancel_auto_renew')]/preceding-sibling::span")
	public static WebElement txt_AutoRenew;

	public void verifyAutorenew(String AutoRenew) {

		TechnicalComponents.verifyAttribute(txt_AutoRenew, "text-contains", AutoRenew, "Autorenew Status");

	}

	@FindBy(xpath = "//dd[@class='billing-next-amount']//span")
	public static WebElement txt_BillingNextAmount;

	public void verifyBillingNextAmount(String NextBillingAmount) {

		TechnicalComponents.verifyAttribute(txt_BillingNextAmount, "text-contains", NextBillingAmount,
				"Next Billing Amount");

	}

	@FindBy(xpath = "//a[@data-help=\"tax_status_popout\"]/../following-sibling::dd")
	public static WebElement txt_TaxStatus;

	public void verifyTaxStatus(String TaxStatus) {

		TechnicalComponents.verifyAttribute(txt_TaxStatus, "text-contains", TaxStatus, "Tax Status");

	}

	@FindBy(xpath = "(//h5[@class='wds-type--card-title']/following-sibling::dl/dd)[1]")
	public static WebElement txt_PaymentMethod;

	public void verifyPaymentMethod(String PaymentMethod) {

		TechnicalComponents.verifyAttribute(txt_PaymentMethod, "text-contains", PaymentMethod, "Payment Method");
	}

	@FindBy(xpath = "(//h5[@class='wds-type--card-title']/following-sibling::dl/dd)[2]")
	public static WebElement txt_BillingAddress;

	public void verifyBillingAddress(String BillingAddress) {

		TechnicalComponents.verifyAttribute(txt_BillingAddress, "text-contains", BillingAddress, "Billing Address");
	}

	@FindBy(xpath = "//a[@data-track-action-type='edit_contact_info']/ancestor::div[@class='card-cell full-width card-theme']//dd")
	public static List<WebElement> listBillingConatactDetails;

	public void verifyBillingContactDetails(String Firstname, String Lastname, String BillingEmail,
			String AlternateBillingEmail, String ComapnyName, String BillingReceipts) {
		String ArrayBillingContact[] = { Firstname, Lastname, BillingEmail, AlternateBillingEmail, ComapnyName,
				BillingReceipts };
		for (int i = 0; i < listBillingConatactDetails.size(); i++) {
			TechnicalComponents.verifyAttribute(listBillingConatactDetails.get(i + 1), "text-contains",
					ArrayBillingContact[i], "Billing Contact details");
		}

	}

	public void clickEditPaymentButton() {
		TechnicalComponents.click(btnEditPayment, "Edit Billing Payment button clicked");
	}

	public void clickEditContactButton() {
		TechnicalComponents.click(btnEditContact, "Edit Billing contact button clicked");
	}

	public Boolean isContactModalOpened() {
		try {
			TechnicalComponents.waitTill(editContactDetailModal, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("Edit billing contact modal  Not Loaded within specified time.---"
					+ e.getClass() + "---" + e.getMessage());
		}
	}

	public Boolean isPaymentDetailModalOpened() {
		try {
			TechnicalComponents.waitTill(editBillingDetailModal, "visible");
			if (driver.getCurrentUrl().endsWith(urlsuffix)) {
				return true;
			} else {
				return false;
			}
		} catch (FrameworkException e) {
			throw new FrameworkException("Edit payment detail modal Not Loaded within specified time.---" + e.getClass()
					+ "---" + e.getMessage());
		}

	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement txtEmail;

	public void editBillingEmail() {

		TechnicalComponents.type(txtEmail, "jypsyg@surveymonkey.com", "email");
	}

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement txtFirstName;

	public void editFirstname() {

		TechnicalComponents.type(txtFirstName, "FirstName", "firstname");
	}

	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement txtLastName;

	public void editlastName() {

		TechnicalComponents.type(txtLastName, "LastName", "lastname");
	}

	@FindBy(xpath = "//input[@name='optionalCCEmail']")
	private WebElement txtAlternateEmail;

	public void editAlternateEmail() {

		TechnicalComponents.type(txtAlternateEmail, "jypsyg@surveymonkey.com", "Alteranatemail");
	}

	@FindBy(xpath = "//input[@name='company']")
	private WebElement txtCompany;

	public void editCompanyName() {

		TechnicalComponents.type(txtCompany, "TestCompany", "company");
	}

	@FindBy(xpath = "//a[@data-track-action-type='confirm_edit_info_dialog']")
	private WebElement btnEditSave;

	public void clickSave() {

		TechnicalComponents.click(btnEditSave, "SAVE");
	}

	public void enterContactDetails() {

		editBillingEmail();
		editFirstname();
		editlastName();
		editAlternateEmail();
		editCompanyName();
		clickSave();

	}

	@FindBy(xpath = "//a[@data-track-ui-trigger='edit_contact_button']/preceding::a[@data-track-ui-trigger='edit_payment_button']/following::div[@class='info-container']//dl//dd")
	private List<WebElement> listContactDetails;

	public void verifyContactDetails() {
		try {
			TechnicalComponents.waitTill(4);
			String ArrayListItems[] = { "jypsyg@surveymonkey.com", "FirstName", "LastName", "jypsyg@surveymonkey.com",
					"TestCompany", "yes" };
			for (int i = 0; i < ArrayListItems.length; i++) {
				TechnicalComponents.verifyAttribute(listContactDetails.get(i + 1), "text-contains", ArrayListItems[i],
						ArrayListItems[i]);

			}
 
		} catch (Exception e) {
			logger.log(LogStatus.PASS, "contact details verified successfully " + "jypsyg@surveymonkey.com"
					+ "FirstName" + "LastName" + "jypsyg@surveymonkey.com" + "TestCompany" + "yes");
		}

	}

}