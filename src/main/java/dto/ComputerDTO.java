package dto;


public class ComputerDTO {
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO companyDTO;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introducedDate) {
		this.introduced = introducedDate;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinuedDate) {
		this.discontinued = discontinuedDate;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	@Override
	public String toString() {
		return "ComputerDTO [id = " + id + ", name=" + name + ", introducedDate=" + introduced + ", discontinuedDate="
				+ discontinued + ", CompanyDTO=" + companyDTO.toString() + "]";
	}

}