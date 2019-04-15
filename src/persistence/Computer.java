package persistence;
import java.util.Date;

public class Computer {
	
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

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	protected long id;
	protected String name;
	protected Date introduced;
	protected Date discontinued;
	protected long company_id;

	
	public Computer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Computer(long id, String name, Date introduced) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
	}

	public Computer(long id, String name, Date introduced, Date discontinued) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	public Computer(long id, String name, Date introduced, Date discontinued, long company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}


}
