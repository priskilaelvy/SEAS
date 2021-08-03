package seas.model;

public class StaffBean {
	private String idstaff, namastaff, jawatan, telstaff, pwstaff;
	boolean valid;
	
	public String getIdstaff() {
		return idstaff;
	}
	
	public void setIdstaff(String idstaff) {
		this.idstaff = idstaff;
	}
	
	public String getNamastaff() {
		return namastaff;
	}
	
	public void setNamastaff(String namastaff) {
		this.namastaff = namastaff;
	}
	
	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getTelstaff() {
		return telstaff;
	}

	public void setTelstaff(String telstaff) {
		this.telstaff = telstaff;
	}
	
	public String getPwstaff() {
		return pwstaff;
	}
	
	public void setPwstaff(String pwstaff) {
		this.pwstaff = pwstaff;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
		
	}
}
