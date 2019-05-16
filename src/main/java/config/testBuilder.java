package config;

import model.Company;
import model.Computer;

public class testBuilder {

	public static void main(String[] args) {
		

		Company company = new Company.Builder()

	            .withParameter(3L,"Apple")
	            .build();
	
	System.out.println(company);
	
	
	
	Computer computer = new Computer.Builder()

			.build();
	
	System.out.println(computer);

}
}

