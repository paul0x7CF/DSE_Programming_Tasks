package KickStartDev;

public class ResponseMessage<T> implements IMarshall {

	private final String objectName;
	private final T responseData;

	public ResponseMessage(String objectName, T responseData) {
		this.responseData = responseData;
		this.objectName = objectName;
	}

	public T getResponseData() {
		return responseData;
	}
	public String getObjectName() {
		return objectName;
	}
}