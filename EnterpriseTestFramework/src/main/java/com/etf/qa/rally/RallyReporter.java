package com.etf.qa.rally;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etf.qa.common.Global;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import org.apache.commons.codec.binary.Base64;

public class RallyReporter {
	
	RallyRestApi restApi;
	
	private String attachmentContentRef;
	private String attachmentRef;
	private String testCaseRef;
	private String testSetRef = "";
	private String testBuildRef = "N/A";
	private String testNotesRef = "Selenium Test Run";
	private String testResultRef;
	private String userRef;
	
	private long attachmentSize;
	
	protected final static Logger logger = LoggerFactory.getLogger(RallyReporter.class);

	public RallyReporter() throws URISyntaxException {
		restApi = new RallyRestApi(new URI(Global.getRallyUrl()),Global.getRallyKey());
		restApi.setApplicationName("QA Automation");
	}
	
	public void Report(String tcName, String testSetName, String strVerdict) {
		
		logger.info("Reporting test case result to Rally without screenshot");
		
		
		 for (String strTestCaseName: tcName.split(",")){
			 
			 try {
					//Get the Rally User Reference
					userRef = QueryUser(Global.getRallyUsername());
					//Get the test case reference
					testCaseRef = QueryTestCase(strTestCaseName);
					//Get the test set reference
					if(Global.getRallyTestSet() != null) {
						testSetRef = QueryTestSet(Global.getRallyTestSet());
					}else {
						testSetRef = QueryTestSet(testSetName);
					}
					
					
					if((Global.getRallyBuild() != null ) && (Global.getRallyBuild() != "")) {
						testBuildRef = Global.getRallyBuild();
					}
					
					if((Global.getRallyBuildNotes() != null ) && ( Global.getRallyBuildNotes() != "" )) {
						testNotesRef = Global.getRallyBuildNotes();
					}
					
					//Create the test case result
					testResultRef = CreateTestResult(strVerdict, attachmentRef);			
					
				} catch (URISyntaxException e) {
				
					logger.error(e.toString());
				}
				catch (IOException e)
				{
					logger.error(e.toString());
				}
			 
		 }// end for
		
		
				
	}
	
	public void Report(String testCaseName, String testSetName, String screenShotPath, String strVerdict) {
		
		logger.info("Reporting test case result to Rally with screenshot");
		
		File file = new File(screenShotPath);
	
		 for (String strTestCaseName: testCaseName.split(",")){

				try {
					//Get the Rally User Reference
					userRef = QueryUser(Global.getRallyUsername());
					//Get the test case reference
					testCaseRef = QueryTestCase(strTestCaseName);
					//Get the test set reference
					if(Global.getRallyTestSet() != null) {
						testSetRef = QueryTestSet(Global.getRallyTestSet());
					}else {
						testSetRef = QueryTestSet(testSetName);//test set name can 
					}
					
					if((Global.getRallyBuild() != null ) && (Global.getRallyBuild() != "")) {
						testBuildRef = Global.getRallyBuild();
					}
					
					if((Global.getRallyBuildNotes() != null ) && ( Global.getRallyBuildNotes() != "" )) {
						testNotesRef = Global.getRallyBuildNotes();
					}
					
					//Create the test case result
					testResultRef = CreateTestResult(strVerdict, attachmentRef);
					//Create the attachment content
					attachmentContentRef = CreateAttachmentContent(screenShotPath);
					//create the attachment and tie it to test case result
					attachmentRef = CreateAttachment(file.getName(), testResultRef);
					
					
				} catch (URISyntaxException e) {
					
					logger.error(e.toString());
				}
				catch (IOException e)
				{
					logger.error(e.toString());
				}
			 
	      }//End For loop
		
	}
	
	
	public String CreateAttachmentContent(String screenShotPath) throws IOException {
		
		logger.info("Creating Rally Screenshot Attachment Content");
	
			String base64String;
			// Open file
			RandomAccessFile raFile = new RandomAccessFile(screenShotPath, "r");

			if (raFile.length() > 5120000)
				throw new IOException("File size too big for Rally attachment, > 5 MB");

			// Read file and return data
			byte[] fileBytes = new byte[(int) raFile.length()];
			raFile.readFully(fileBytes);
			base64String = Base64.encodeBase64String(fileBytes);
			attachmentSize = (int) raFile.length();

			// First create AttachmentContent from image string
			JsonObject attachmentContent = new JsonObject();
			attachmentContent.addProperty("Content", base64String);
			CreateRequest attachmentContentRequest = new CreateRequest("AttachmentContent", attachmentContent);
			CreateResponse attachmentContentResponse = restApi.create(attachmentContentRequest);
			attachmentContentRef = attachmentContentResponse.getObject().get("_ref").getAsString();
			logger.info(attachmentContentRef);
			return attachmentContentRef;

	}

	public String CreateAttachment(String fileName, String strTestResultRef) throws IOException
	{
	
		logger.info("Creating Rally Screenshot Attachment");
		
		// Now create the Attachment itself
        JsonObject attachment = new JsonObject();
        attachment.addProperty("TestCaseResult", strTestResultRef);
        attachment.addProperty("Content", attachmentContentRef);
        attachment.addProperty("Name", fileName);
        attachment.addProperty("Description", "Attachment From REST");
        attachment.addProperty("ContentType","image/png");
        attachment.addProperty("Size", attachmentSize);
        attachment.addProperty("User", userRef);       
        
        CreateRequest attachmentCreateRequest = new CreateRequest("Attachment", attachment);
        CreateResponse attachmentResponse= restApi.create(attachmentCreateRequest);
		
        attachmentRef = attachmentResponse.getObject().get("_ref").getAsString();
        logger.info("Attachment  created: " + attachmentRef); 
        
        if (attachmentResponse.wasSuccessful()) {
            logger.info("Successfully created Attachment");
            return attachmentRef;
        } else {
            String[] attachmentContentErrors;
            attachmentContentErrors = attachmentResponse.getErrors();
                    logger.error("Error occurred creating Attachment: ");
            for (int i=0; i<attachmentContentErrors.length;i++) {
                    logger.error(attachmentContentErrors[i]);
            }                   
        }
        
		return null;
		
	}
        
    public String CreateTestResult(String strVerdict, String strAttachmentRef) throws IOException
    	{
    	
    	logger.info("Creating Rally Test Result");

    			JsonObject tcResult = new JsonObject();
    			tcResult.addProperty("Verdict", strVerdict);
    			tcResult.addProperty("Date", DateTime.now().toString());
    			tcResult.addProperty("Notes", testNotesRef );
    			tcResult.addProperty("Build", testBuildRef );
    			tcResult.addProperty("Tester", userRef);
    			tcResult.addProperty("TestCase", testCaseRef);
    			tcResult.addProperty("TestSet", testSetRef);
    			
    			logger.info("Reported Result - Verdict: " + strVerdict);
    			logger.info("Reported Result - Test Case: " + testCaseRef);
    			logger.info("Reported Result - Test Set: " + testSetRef);
    			
    			
    			CreateRequest createRequest = new CreateRequest("testcaseresult", tcResult);
    			CreateResponse createResponse = restApi.create(createRequest);
    			
    			try {
    				testResultRef = createResponse.getObject().get("_ref").getAsString();
    			}
    			catch (Exception ex) {
    				logger.info("Error logging test case to rally.");
    			}
    			

    			if (createResponse.wasSuccessful()) {
    				logger.info(String.format("Created %s", testResultRef));
    				//logger.info("Test case execution successfully reported to Rally");
    				
    				return testResultRef;
    				
    			} else {
    				String[] createErrors;
    				createErrors = createResponse.getErrors();
    				logger.info("Error occurred creating Test Case: ");
    				for (int i = 0; i < createErrors.length; i++) {
    					logger.error(createErrors[i]);
    				}
    			}
        	
        	return null;

    	}

	public String QueryTestCase(String tcName) throws IOException, URISyntaxException {

		QueryRequest testCaseRequest = new QueryRequest("TestCase");
		testCaseRequest.setFetch(new Fetch("FormattedID", "Name"));
		testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", tcName));

		try {
			
			QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
			testCaseRef = testCaseQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();
		}
		catch(Exception ex) {
			logger.info("*******Error looking up Test Case " + tcName +".");
		}
		
		
		return testCaseRef;//

	}

	public String QueryUser(String username) throws URISyntaxException, IOException
	 {
	
	 QueryRequest userRequest = new QueryRequest("User");
	 userRequest.setFetch(new Fetch("UserName", "Subscription","DisplayName"));
	 userRequest.setQueryFilter(new QueryFilter("UserName", "=", username));
	
	 
	 try {
		 
		 QueryResponse userQueryResponse = restApi.query(userRequest);
		 JsonArray userQueryResults = userQueryResponse.getResults();
		 JsonElement userQueryElement = userQueryResults.get(0);
		 JsonObject userQueryObject = userQueryElement.getAsJsonObject();
		 userRef = userQueryObject.get("_ref").getAsString();
		 
	 }
		catch(Exception ex) {
			logger.info("*******Error looking up Rally Username " + username +".");
		}
	 
	 return userRef;
	 
	 
	
	 }
	
	public String QueryTestSet(String tsName)throws IOException, URISyntaxException {
		
		if(!tsName.equals(""))
		{
			QueryResponse testSetQueryResponse = null;
			QueryRequest testSetRequest = new QueryRequest("TestSet");
			testSetRequest.setFetch(new Fetch("FormattedID", "Name"));
			testSetRequest.setQueryFilter(new QueryFilter("FormattedID", "=", tsName));
			
			try {
				
				testSetQueryResponse = restApi.query(testSetRequest);
				testSetRef = testSetQueryResponse.getResults().get(0).getAsJsonObject().get("_ref").getAsString();	
			}
			catch(Exception ex) {
				logger.info("*******Error looking up Test Set " + tsName +".");
			}
			
			
		}

		return testSetRef;//
	}
	


}// class
