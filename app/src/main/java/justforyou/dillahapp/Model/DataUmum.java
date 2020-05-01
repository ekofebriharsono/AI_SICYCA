package justforyou.dillahapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUmum {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("klkl_id")
    @Expose
    private String klklId;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("hari")
    @Expose
    private String hari;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("dosen_nik")
    @Expose
    private String dosenNik;
    @SerializedName("dosen_nama")
    @Expose
    private String dosenNama;
    @SerializedName("dosen_hadir")
    @Expose
    private String dosenHadir;
    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;
    @SerializedName("waktu_selesai")
    @Expose
    private String waktuSelesai;
    @SerializedName("count_down")
    @Expose
    private String countDown;
    @SerializedName("nama_matkul")
    @Expose
    private String namaMatkul;
    @SerializedName("ruang")
    @Expose
    private String ruang;
    @SerializedName("sks")
    @Expose
    private String sks;
    @SerializedName("kehadiran")
    @Expose
    private String kehadiran;
    @SerializedName("absen")
    @Expose
    private Integer absen;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getKlklId() {
        return klklId;
    }

    public void setKlklId(String klklId) {
        this.klklId = klklId;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDosenNik() {
        return dosenNik;
    }

    public void setDosenNik(String dosenNik) {
        this.dosenNik = dosenNik;
    }

    public String getDosenNama() {
        return dosenNama;
    }

    public void setDosenNama(String dosenNama) {
        this.dosenNama = dosenNama;
    }

    public String getDosenHadir() {
        return dosenHadir;
    }

    public void setDosenHadir(String dosenHadir) {
        this.dosenHadir = dosenHadir;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getCountDown() {
        return countDown;
    }

    public void setCountDown(String countDown) {
        this.countDown = countDown;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getKehadiran() {
        return kehadiran;
    }

    public void setKehadiran(String kehadiran) {
        this.kehadiran = kehadiran;
    }

    public Integer getAbsen() {
        return absen;
    }

    public void setAbsen(Integer absen) {
        this.absen = absen;
    }
}
