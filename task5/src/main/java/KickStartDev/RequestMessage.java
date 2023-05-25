package KickStartDev;


public class RequestMessage<T> implements IMarshall {
	private final EKnownMethods method;
	private final T requestData;
	private final boolean isCallback;

	public RequestMessage(EKnownMethods method, T requestData, boolean isCallback) {
		super();
		this.method = method;
		this.requestData = requestData;
		this.isCallback = isCallback;
	}

	public T getRequestData() {
		return requestData;
	}

	public EKnownMethods getMethod() {
		return method;
	}
}

