package ch.unibas.medizin.osce.client.a_nonroo.client.place;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.RequestFactory;

public class StandardizedPatientDetailsPlace extends Place {
	
	
	private String token;
	
	public enum Operation {
		DETAILS, EDIT, CREATE
	}
	private static final String SEPARATOR = "!";


	private EntityProxyId<?> proxyId;
	private Operation operation = null;
	
	
	public EntityProxyId<?> getProxyId() {
		return proxyId;
	}

	public StandardizedPatientDetailsPlace(){
		this.token = "SystemStartPlace";
	}
	public StandardizedPatientDetailsPlace(EntityProxyId<?> record) {
		this(record, Operation.DETAILS);
	}
	
	public StandardizedPatientDetailsPlace(Operation operation) {
		this.operation = operation;
	}

    public StandardizedPatientDetailsPlace(EntityProxyId<?> stableId, Operation operation) {
		this.operation = operation;
		proxyId = stableId;
	}

	public Operation getOperation() {
		return operation;
	}
	
	public StandardizedPatientDetailsPlace(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Tokenizer.
	 */

	public static class Tokenizer implements PlaceTokenizer<StandardizedPatientDetailsPlace> {
		private final RequestFactory requests;

		public Tokenizer(RequestFactory requests) {
			Log.debug("StandardizedPatientDetailsPlace.Tokenizer");
			this.requests = requests;
		}

		public StandardizedPatientDetailsPlace getPlace(String token) {
			Log.debug("StandardizedPatientDetailsPlace.Tokenizer.getPlace");
			String bits[] = token.split(SEPARATOR);
			Operation operation = Operation.valueOf(bits[1]);
			if (Operation.DETAILS == operation) {
				return new StandardizedPatientDetailsPlace(requests.getProxyId(bits[0]), Operation.DETAILS);
			}
			if (Operation.EDIT == operation) {
				return new StandardizedPatientDetailsPlace(requests.getProxyId(bits[0]), Operation.EDIT);
			}
			if (Operation.CREATE == operation) {
				return new StandardizedPatientDetailsPlace(/*requests.getProxyId(bits[0]), */Operation.CREATE);
			}

			return new StandardizedPatientDetailsPlace(token);
		}

		public String getToken(StandardizedPatientDetailsPlace place) {
			Log.debug("StandardizedPatientDetailsPlace.Tokenizer.getToken");
			if (Operation.DETAILS == place.getOperation()) {
				return place.getProxyId() + SEPARATOR + StandardizedPatientDetailsPlace.Operation.DETAILS;
			}
			if (Operation.EDIT == place.getOperation()) {
				return place.getProxyId() + SEPARATOR + StandardizedPatientDetailsPlace.Operation.EDIT;
			}
			if (Operation.CREATE == place.getOperation()) {
				return /*place.getProxyId() + SEPARATOR + */StandardizedPatientDetailsPlace.Operation.CREATE.toString();
			}
        
			return place.getToken();
		}
	}


	

}
