package model;


import java.time.LocalDate;

public class Computer {
	
	protected long id;
	protected String name;
	protected LocalDate introduced;
	protected LocalDate discontinued;
	protected Company company;

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	
	


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	public Computer() {
		super();

	}

	public Computer(long id) {
		super();
		this.id = id;

	}

	public Computer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Computer(long id, String name, LocalDate introduced) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
	}

	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	

}
