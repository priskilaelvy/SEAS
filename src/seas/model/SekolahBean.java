package seas.model;

public class SekolahBean {
	private String kodsekolah, nama_sklh, jenis_sklh, telefon, lamanweb, alamat, gurubesar_pengetua, guru_data;
	boolean valid;
	
	public String getKodsekolah() {
		return kodsekolah;
	}
	
	public void setKodsekolah(String kodsekolah) {
		this.kodsekolah = kodsekolah;
	}
	
	public String getNama_sklh() {
		return nama_sklh;
	}
	
	public void setNama_sklh(String nama_sklh) {
		this.nama_sklh = nama_sklh;
	}
	
	public String getJenis_sklh() {
		return jenis_sklh;
	}
	
	public void setJenis_sklh(String jenis_sklh) {
		this.jenis_sklh = jenis_sklh;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public String getLamanweb() {
		return lamanweb;
	}
	
	public void setLamanweb(String lamanweb) {
		this.lamanweb = lamanweb;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public String getGurubesar_pengetua() {
		return gurubesar_pengetua;
	}
	
	public void setGurubesar_pengetua(String gurubesar_pengetua) {
		this.gurubesar_pengetua = gurubesar_pengetua;
	}
	
	public String getGuru_data() {
		return guru_data;
	}
	
	public void setGuru_data(String guru_data) {
		this.guru_data = guru_data;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
