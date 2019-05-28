package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="computer")
public class Computer {

	@Id
	private long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "introduced", nullable = true)
	private LocalDate introduced;
	@Column(name ="discontinued", nullable = true)
	private LocalDate discontinued;
	@Column(name ="company_id")
	private long companyID;

	public Computer() {
	}


	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, long companyID) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyID = companyID;
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


	public long getCompanyID() {
		return companyID;
	}


	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

}