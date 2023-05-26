package Shared;


import Shared.EKnownMethods;
import Shared.EResult;
import Shared.IMarshall;

public class RequestMessage<T> implements IMarshall {
	private final EKnownMethods method;
	private final EResult resultAs;
	private final T requestData;

	public RequestMessage(EKnownMethods method, T requestData, EResult resultAs) {
		super();
		this.method = method;
		this.requestData = requestData;
		this.resultAs = resultAs;
	}

	public T getRequestData() {
		return requestData;
	}

	public EKnownMethods getMethod() {
		return method;
	}
	public EResult getResultAs() {
		return resultAs;
	}
}

