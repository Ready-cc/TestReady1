package myTools;

/**
 * @describtion:testlink API
 * @author 
 * time 2014.3.25
 * casename 判断失败
 * 通过caseid 可以上传结果，备注无法上传
 *  case表--nodes_hierarchy
 *
 **/
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.testng.log4testng.Logger;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class ApiObject {
	private static Logger logger = Logger.getLogger(ApiObject.class);
	private static TestLinkAPI api = null;
	private String planName;
	private String projectName;
	private String url;
	private String devKey;
	private TestCase[] tcs;
	private TestPlan tl;
	private String buildName;
	private String platformName;
	private Integer tcID = 0;
	private Integer planID = 0;
	private TestCase tcase;

	//
	// private static TestLinkAPI api = ApiObject.getAPI();

	// 上传附件
	/*	
	 * public void uploadAttchment(String picturePath) {
		File attachmentFile = new File(picturePath);
		String fileContent = null;
		tcs = api.getTestCasesForTestPlan(tl.getId(), null, null, null, null,
				null, null, null, ExecutionType.MANUAL, null, null);
		try {
			byte[] byteArray = FileUtils.readFileToByteArray(attachmentFile);
			fileContent = new String(Base64.encodeBase64(byteArray));
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(-1);
		}

		Attachment attachment = api.uploadTestCaseAttachment(
				35, //executionid uploadExecutionAttachment方法通过执行id
//				23,// uploadTestCaseAttachment 方法 通过caseid值上传附件
				"2.jpg", // title
				"In this screen the attendant is defining the customer plan", // description
				"Autor:terry1", // fileName
				"text/JPG", // fileType
				fileContent); // content

		System.out.println("Attachment uploaded");
		
	
		System.out.println("Attachment uploaded");
	}
	*/
    public void testUpload(TestCase tc, String file, String title,
            String desc, String fileName, String fileType) {
        File attFile = new File(file);
        String fileContent = "";
        try {
            byte[] byteArray = FileUtils.readFileToByteArray(attFile);
            fileContent = new String(Base64.encodeBase64(byteArray));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        Attachment attachment = api.uploadTestCaseAttachment(
        		tc.getId(), // 测试用例编号
                title, // 上传附件的标题
                desc, // 附件的描述信息
                fileName, // 附件的文件名称
                fileType, // 上传附件的 MIME 文件类型
                fileContent); // 附件的文件内容
        logger.debug(" 附件上传完成，" + attachment.toString());
    }
    
	public ApiObject(String url, String devKey, String projectName,
			String planName, String buildName, String platformName) {
		this.url = url;
		this.devKey = devKey;
		this.projectName = projectName;
		this.planName = planName;
		this.buildName = buildName;
		this.platformName = platformName;
	}

	public TestLinkAPI getAPI() {
		if (null == api) {
			try {
				api = new TestLinkAPI(new URL(url), devKey);
			} catch (TestLinkAPIException te) {
				logger.error(te.getMessage(), te);
			} catch (MalformedURLException mue) {
				logger.error(mue.getMessage(), mue);
			}
		}
		return api;
	}

	/**
	 * @decribtion:1.9.4版本的testlink 通过getTestCasesForTestPlan return的name=null
	 * 	but通过plan--suits-case可以取到name
	 */
		public void getTestCases() {
			tl = api.getTestPlanByName(planName, projectName);
			TestSuite[] ts = api.getTestSuitesForTestPlan(tl.getId());
			for (int i = 0; i < ts.length; i++) {
				tcs = api.getTestCasesForTestSuite(ts[i].getId(), null, null);
			}
		}
//		返回case
		public TestCase getTestCase(String testcasename) {
			tl = api.getTestPlanByName(planName, projectName);
			TestSuite[] ts = api.getTestSuitesForTestPlan(tl.getId());
			for (int i = 0; i < ts.length; i++) {
				tcs = api.getTestCasesForTestSuite(ts[i].getId(), null, null);
			}
			for (TestCase tc : tcs) {
				String casename = tc.getName();
				if (testcasename.equals(casename)) {
					tcase = tc;
					break;
				}
			}
			return tcase;
		}
//		1.9.3版本
		public void getTestCasess() {
			this.planName = planName;
			this.projectName = projectName;
			tl = api.getTestPlanByName(planName, projectName);
			tcs = api.getTestCasesForTestPlan(tl.getId(), null, null, null, null,null, null, null, ExecutionType.MANUAL, null, null);
/*			for (TestCase tc : tcs) {
				System.out.println(tc.toString());
			}
*/
		}


	public void executeTestCase(String testcasename, int status, String comments) {
		Integer planID = tl.getId();

		for (TestCase tc : tcs) {
			String casename = tc.getName();
			if (testcasename.equals(casename)) {
				tcID = tc.getId();
				tcase = tc;
				
				break;
			}

		}

		switch (status) {
		case 1:
			api.reportTCResult(tcID, null, planID, ExecutionStatus.PASSED,
					null, buildName,comments, null, null, null, platformName,
					null, null);
			break;
		case 2:
			api.reportTCResult(tcID, null, planID, ExecutionStatus.FAILED,
					null, buildName,comments, null, null, null, platformName,
					null, null);
			break;
		case 3:
			api.reportTCResult(tcID, null, planID, ExecutionStatus.BLOCKED,
					null, buildName,comments, null, null, null, platformName,
					null, null);
			break;
		default:
			System.out.println("please check your status");
		}

	}
	public static void main(String args[]) {
		String url = "http://localhost/testlink/lib/api/xmlrpc.php";
		String devKey = "c8f14c7eb9e5364e8fc7aca932119277";
		String projectName = "xigua";
		String tl = "plan1";
		String buildName = "release-april";
		String platform = "IE9";

		ApiObject testlinkapi = new ApiObject(url, devKey, projectName, tl,
				buildName, platform);

		testlinkapi.getAPI();
		testlinkapi.getTestCases();
//		 testlinkapi.uploadAttchment("C:/Users/huitao/Pictures/LOGO/2.JPG");
//		 ApiObject.testUpload(tc,"C:/Users/huitao/Pictures/LOGO/1.jpg",11,11, 11, "jpg");
		testlinkapi.executeTestCase("tc1", 1, "tested by CCF");
		System.out.println(testlinkapi.tcase.getFullExternalId());
	}
}
