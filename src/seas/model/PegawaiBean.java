package seas.model;

public class PegawaiBean {
	private String idpegawai, namapegawai, jawatan_pegawai, tel_pegawai, pwpegawai;
	boolean valid;
	
	public String getIdpegawai() {
		return idpegawai;
	}
	

	public void setIdpegawai(String idpegawai) {
		this.idpegawai = idpegawai;
	}
	

	public String getNamapegawai() {
		return namapegawai;
	}
	

	public void setNamapegawai(String namapegawai) {
		this.namapegawai = namapegawai;
	}

	
	public String getJawatan_pegawai() {
		return jawatan_pegawai;
	}


	public void setJawatan_pegawai(String jawatan_pegawai) {
		this.jawatan_pegawai = jawatan_pegawai;
	}


	public String getTel_pegawai() {
		return tel_pegawai;
	}


	public void setTel_pegawai(String tel_pegawai) {
		this.tel_pegawai = tel_pegawai;
	}


	public String getPwpegawai() {
		return pwpegawai;
	}

	
	public void setPwpegawai(String pwpegawai) {
		this.pwpegawai = pwpegawai;
	}

	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
		
	}
}
