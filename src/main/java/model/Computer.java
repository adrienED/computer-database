package model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Computer {

	public static class Builder {

		@Id	
		@GeneratedValue
		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private long companyID;

		public Builder() {
		}

		public Builder withId(long id) {

			this.id = id;

			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withIntroduced(LocalDate introduced) {

			this.introduced = introduced;
			return this;

		}

		public Builder withDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder withCompanyID(long companyID) {
			this.companyID = companyID;
			return this;
		}

		public Computer build() {

			Computer computer = new Computer();

			computer.id = this.id;

			computer.name = this.name;

			computer.introduced = this.introduced;

			computer.discontinued = this.discontinued;

			computer.companyID = this.companyID;

			return computer;

		}

	}

	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private long companyID;

	private Computer() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public long getCompanyID() {
		return companyID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (companyID ^ (companyID >>> 32));
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (companyID != other.companyID)
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyID=" + companyID + "]";
	}

}