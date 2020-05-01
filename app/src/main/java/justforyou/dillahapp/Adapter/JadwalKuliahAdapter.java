package justforyou.dillahapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import justforyou.dillahapp.Model.DataUmum;
import justforyou.dillahapp.R;

public class JadwalKuliahAdapter extends RecyclerView.Adapter<JadwalKuliahAdapter.ViewHolder> {

    private Context context;
    private Boolean utsOrUas;
    private List<DataUmum> results;

    public JadwalKuliahAdapter(Context context, List<DataUmum> results, Boolean utsOrUas) {
        this.context = context;
        this.results = results;
        this.utsOrUas = utsOrUas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataUmum result = results.get(position);

        holder.NamaMatkul.setText(result.getNamaMatkul());
        // holder.kelas.setText(" ("+result.getKelas()+")");
        holder.ruang.setText(" " + result.getRuang());
        holder.jam.setText(" " + result.getWaktuMulai() + " - " + result.getWaktuSelesai());
        holder.NamaDosen.setText(" " + result.getDosenNama());
        holder.Tanggal.setText(" " + result.getTanggal());

        if (!utsOrUas) {
            if (result.getDosenHadir().equals("H")) {
                holder.Hadir.setText(" " + "Dosen Hadir");
                holder.Hadir.setVisibility(View.VISIBLE);
            } else if (result.getDosenHadir().equals("I")) {
                holder.Hadir.setText(" " + "Dosen Ijin");
                holder.Hadir.setVisibility(View.VISIBLE);
            } else {
                holder.Hadir.setText(" " + result.getDosenHadir());
            }
        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView NamaMatkul, kelas, ruang, jam, NamaDosen, Hadir, Tanggal;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            NamaMatkul = itemView.findViewById(R.id.EdNamaMatkul);
            kelas = itemView.findViewById(R.id.EdKelas);
            jam = itemView.findViewById(R.id.EdWaktu);
            ruang = itemView.findViewById(R.id.EdLokasi);
            NamaDosen = itemView.findViewById(R.id.EdNamaDosen);
            Hadir = itemView.findViewById(R.id.Edhadir);
            Tanggal = itemView.findViewById(R.id.EdTanggal);
            //ruang = itemView.findViewById(R.id.ruang);
            //jam = itemView.findViewById(R.id.jam);
        }

        @Override
        public void onClick(View view) {

        }
    }
}