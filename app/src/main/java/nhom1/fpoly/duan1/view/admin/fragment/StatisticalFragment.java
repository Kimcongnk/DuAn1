package nhom1.fpoly.duan1.view.admin.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.dao.StatisticalDao;

public class StatisticalFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        ImageView imgStart = view.findViewById(R.id.imgStart);
        ImageView imgEnd = view.findViewById(R.id.imgEnd);

        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtKq = view.findViewById(R.id.txtKetQua);
        TextView txtStart = view.findViewById(R.id.txtStart);
        TextView txtEnd = view.findViewById(R.id.txtEnd);

        Calendar calendar =Calendar.getInstance();


        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay="";
                                String thang ="";
                                if (dayOfMonth<10){
                                    ngay="0"+dayOfMonth;
                                }else{
                                    ngay=String.valueOf(dayOfMonth);
                                }
                                if ((month+1)<10){
                                    thang = "0"+(month+1);
                                }else {
                                    thang = String.valueOf(month+1);
                                }
                                txtStart.setText(year+"-"+thang+"-"+ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)


                );
                datePickerDialog.show();
            }
        });
        imgEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay="";
                                String thang ="";
                                if (dayOfMonth<10){
                                    ngay="0"+dayOfMonth;
                                }else{
                                    ngay=String.valueOf(dayOfMonth);
                                }
                                if ((month+1)<10){
                                    thang = "0"+(month+1);
                                }else {
                                    thang = String.valueOf(month+1);
                                }
                                txtEnd.setText(year+"-"+thang+"-"+ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)


                );
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticalDao statisticalDAO = new StatisticalDao(getContext());
                String ngaybatdau = txtStart.getText().toString();
                String ngayketthuc = txtEnd.getText().toString();
                int doanhthu = statisticalDAO.getDoanhThu(ngaybatdau,ngayketthuc);
                txtKq.setText(doanhthu+" vnd");

            }
        });
        return view;
    }
}