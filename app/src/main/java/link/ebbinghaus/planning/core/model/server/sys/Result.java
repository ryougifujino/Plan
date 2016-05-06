package link.ebbinghaus.planning.core.model.server.sys;

import java.util.HashMap;
import java.util.Map;

public class Result<T> {
	
	public static final String SUCCESS_MSG_KEY = "success";
	public static final String FAILURE_SINGLE_MSG_KEY = "failure";
	public static final int RIGHT_CODE = 1;
	public static final int DEFAULT_ERROR_CODE = 0;
	
	/**
	 * 返回码，默认为 0
	 * 正确：1
	 * 错误：0 或其他
	 */
	private Integer code = DEFAULT_ERROR_CODE;
	
	/**
	 * 消息集
	 */
	private Map<String,String> msgs = new HashMap<>();
	
	/**
	 * 数据
	 */
	private T data;
	
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Map<String, String> getMsgs() {
		return msgs;
	}
	public void setMsgs(Map<String, String> msgs) {
		this.msgs = msgs;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	/** 设置成功  */
	public void setSuccess(T data,String successMsg){
		this.data = data;
		this.msgs.put(SUCCESS_MSG_KEY, successMsg);
		this.code = RIGHT_CODE;
	}
	
	/** 设置成功（无结果） */
	public void setSuccessMsg(String successMsg){
		setSuccess(null, successMsg);
	}
	
	/** 设置失败  */
	public void setFailure(T data,Integer errorCode,Map<String,String> failureMsgs){
		this.data = data;
		this.code = errorCode;
		this.msgs = failureMsgs;
	}
	
	/** 设置失败（无结果） */
	public void setFailureMsgs(Integer errorCode,Map<String,String> failureMsgs){
		setFailure(null, errorCode, failureMsgs);
	}
	
	/** 设置失败（单条失败消息） */
	public void setFailure(T data,Integer errorCode,String failureMsg){
		this.data = data;
		this.code = errorCode;
		this.msgs.put(FAILURE_SINGLE_MSG_KEY, failureMsg);
	}
	
	/** 设置失败（无结果、单条失败消息） */
	public void setFailureMsg(Integer errorCode,String failureMsg){
		setFailure(null, errorCode, failureMsg);
	}
	
	/** 设置失败（默认失败码） */
	public void setFailure(T data,Map<String,String> failureMsgs){
		setFailure(data, DEFAULT_ERROR_CODE, failureMsgs);
	}
	
	/** 设置失败（默认失败码、无结果） */
	public void setFailureMsgs(Map<String,String> failureMsgs){
		setFailure(null, failureMsgs);
	}
	
	/** 设置失败（默认失败码、单条失败消息） */
	public void setFailure(T data,String failureMsg){
		setFailure(data,DEFAULT_ERROR_CODE, failureMsg);
	}
	
	/** 设置失败（默认失败码、无结果、单条失败消息） */
	public void setFailureMsg(String failureMsg){
		setFailure(null, failureMsg);
	}
	
	
	
}