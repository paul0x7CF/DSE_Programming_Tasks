package KickStartDev;


public class RequestMessage<T> implements IMarshall {
	private final EKnownMethods method;
	private final T requestData;

	public RequestMessage(EKnownMethods method, T requestData) {
		super();
		this.method = method;
		this.requestData = requestData;
	}

	public T getRequestData() {
		return requestData;
	}

	public EKnownMethods getMethod() {
		return method;
	}
}

