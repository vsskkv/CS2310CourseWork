import java.io.FileNotFoundException;

public class DataController implements Controller {


	
	public DataController() {
		
	}
	@Override
	public String listAllDancersIn(String dance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listAllDancesAndPerformers() {
		return model.listAllDances();
	}

	@Override
	public String checkFeasibilityOfRunningOrder(String filename, int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateRunningOrder(int gaps) {
		// TODO Auto-generated method stub
		return null;
	}

}
