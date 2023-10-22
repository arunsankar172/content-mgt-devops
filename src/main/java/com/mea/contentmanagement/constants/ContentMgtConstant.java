package com.mea.contentmanagement.constants;

public class ContentMgtConstant {

	private ContentMgtConstant() {
		super();
	}

	/** The Constant CONFIG_BASE_LOCATIONS. */
	public static final String CONFIG_BASE_LOCATIONS = "config/refDataMgmt-config.properties";

	/** The Constant APP_ERROR_FILE. */
	public static final String APP_ERROR_FILE = "error/application-error";

	// Error Constants for Salary Processing

	/** The Constant CM_SYSTEM. */
	public static final String CM_SYSTEM = "Reference Data Management";

	/** The Constant CM_RUN_TIME_EXCEPTION. */
	public static final String CM_RUN_TIME_EXCEPTION = "CM_RUN_TIME_EXCEPTION";

	/** The Constant EGEN_1001. */
	public static final String EGEN_1001 = "Application is experiencing a error";

	/** The Constant EGEN_1002. */
	public static final String EGEN_1002 = "Application is experiencing a error.";

	/** The Constant EVAL_SP_1001. */
	public static final String EVAL_SP_1001 = "EVAL_SP_1001";

	/** The Constant EVAL_SP_1002. */
	public static final String EVAL_SP_1002 = "EVAL_SP_1002";

	/** The Constant EVAL_SP_1003. */
	public static final String EVAL_SP_1003 = "EVAL_SP_1003";

	/** The Constant EVAL_SP_1004. */
	public static final String EVAL_SP_1004 = "EVAL_SP_1004";

	/** The Constant ERROR_TYPE. */
	public static final String ERROR_TYPE = "ERROR_TYPE";

	/** The Constant TRANSACTION_ERROR_CODE. */
	public static final String TRANSACTION_ERROR_CODE = "2";

	/** The Constant VALIDATION_ERROR_CODE. */
	public static final String VALIDATION_ERROR_CODE = "1";

	/** The Constant INVALID_REF_NUMBER. */
	public static final String INVALID_REF_NUMBER = "Invalid Reference Number";

	/** The Constant TRANSACTION_ERROR_DESC. */
	public static final String TRANSACTION_ERROR_DESC = "Trasnsactional Error";

	/** The Constant VALIDATION_ERROR_DESC. */
	public static final String VALIDATION_ERROR_DESC = "Validation Error";

	/** The Constant WARN_TYPE. */
	public static final String WARN_TYPE = "Warning";

	/** The Constant INVALID_NUMBER. */
	public static final String INVALID_NUMBER = "Invalid Number";

	/** The Constant ACTION_EVENT_VIEW. */
	public static final String ACTION_EVENT_VIEW = "R";

	/** The Constant ACTION_EVENT_DELETE. */
	public static final String ACTION_EVENT_DELETE = "D";

	/** The Constant ACTION_EVENT_EDIT. */
	public static final String ACTION_EVENT_EDIT = "U";

	/** The Constant ACTION_EVENT_ADD. */
	public static final String ACTION_EVENT_ADD = "A";

	/** The Constant ACTIVE. */
	public static final Object ACTIVE = "ACT";

	/** The Constant INACTIVE. */
	public static final Object INACTIVE = "INACT";

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "dd/MM/yyyy";

	/** The Constant HEADERS. */
	public static final String HEADERS = "headers";

	/** The Constant STATUS_CODE. */
	public static final String STATUS_CODE = "statusCode";

	/** The Constant PARAMETER_GROUP_ID. */
	public static final String PARAMETER_GROUP_ID = "parameterGroupId";

	/** The Constant REFERENCE_CODE. */
	public static final String REFERENCE_CODE = "referenceCode";

	/** The Constant REFERENCE_TYPE_ID. */
	public static final String REFERENCE_TYPE_ID = "referenceTypeId";

	/** The Constant MODULE. */
	public static final String MODULE = "REF";
	
	/** The Constant APPLICATION_ID. */
	public static final String APPLICATION_ID = "applicationId";
	
	/** The Constant END_DATE_VALUE. */
	public static final String END_DATE_VALUE = "endDate value";
	
	public static final int CONTROL_ACTIVE = 0;
	
	public static final int CONTROL_EDITED = 1;
	
	public static final int CONTROL_SUBMITTED = 2;
	
	public static final int CONTROL_APPROVED = 3;
	
	public static final int CONTROL_DECLINED = 4;
	
	public static final int TASK_STATUS_SUBMITED = 1;

	public static final int TASK_STATUS_COMPLETED = 2;
	
}
