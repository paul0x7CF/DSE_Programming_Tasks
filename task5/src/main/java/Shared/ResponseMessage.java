package Shared;

import Shared.EKnownMethods;
import Shared.IMarshall;

public class ResponseMessage<T> implements IMarshall {

	private final EKnownMethods method;
	private final T responseData;

	public ResponseMessage(EKnownMethods method, T responseData) {
		this.responseData = responseData;
		this.method = method;
	}

	public T getResponseData() {
		return responseData;
	}
	public EKnownMethods getMethod() {
		return method;
	}
}