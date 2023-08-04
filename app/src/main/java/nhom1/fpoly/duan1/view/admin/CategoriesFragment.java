package nhom1.fpoly.duan1.view.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import nhom1.fpoly.duan1.R;
import nhom1.fpoly.duan1.adapter.admin.SalesReportAdapter;
import nhom1.fpoly.duan1.dao.OrderDetailDao;
import nhom1.fpoly.duan1.dao.StatisticalDao;
import nhom1.fpoly.duan1.model.ProductSalesReport;

public class CategoriesFragment extends Fragment {
    private SalesReportAdapter salesReportAdapter;
    private TextView txtKq;
private View nullThongKe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        RecyclerView recyclerViewSalesReport = view.findViewById(R.id.recyclerViewSalesReport);
        ImageView imgStart = view.findViewById(R.id.imgStart);
        ImageView imgEnd = view.findViewById(R.id.imgEnd);

        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        txtKq = view.findViewById(R.id.txtKetQua);
        TextView txtStart = view.findViewById(R.id.txtStart);
        TextView txtEnd = view.findViewById(R.id.txtEnd);
        Calendar calendar = Calendar.getInstance();
nullThongKe = view.findViewById(R.id.no_oder);

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if ((month + 1) < 10) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }
                                txtStart.setText(year + "-" + thang + "-" + ngay);
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
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10) {
                                    ngay = "0" + dayOfMonth;
                                } else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if ((month + 1) < 10) {
                                    thang = "0" + (month + 1);
                                } else {
                                    thang = String.valueOf(month + 1);
                                }
                                txtEnd.setText(year + "-" + thang + "-" + ngay);
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
                String ngaybatdau = txtStart.getText().toString();
                String ngayketthuc = txtEnd.getText().toString();

                StatisticalDao orderDetailDao = new StatisticalDao(getContext());
                List<ProductSalesReport> salesReportList = orderDetailDao.getProductSalesReport(ngaybatdau, ngayketthuc);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerViewSalesReport.setLayoutManager(layoutManager);
                if(salesReportList.isEmpty()){
                    nullThongKe.setVisibility(View.VISIBLE);
                }else {
                    nullThongKe.setVisibility(View.GONE);
                }
                // Create and set the adapter for the RecyclerView
                salesReportAdapter = new SalesReportAdapter(getContext(), salesReportList);
                recyclerViewSalesReport.setAdapter(salesReportAdapter);
                updateTotalRevenue();
            }
        });

        StatisticalDao orderDetailDao = new StatisticalDao(getContext());
        List<ProductSalesReport> salesReportList = orderDetailDao.getProductSalesReportAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewSalesReport.setLayoutManager(layoutManager);
        // Create and set the adapter for the RecyclerView
        if(salesReportList.isEmpty()){
            nullThongKe.setVisibility(View.VISIBLE);
        }else {
            nullThongKe.setVisibility(View.GONE);
        }
        salesReportAdapter = new SalesReportAdapter(getContext(), salesReportList);
        recyclerViewSalesReport.setAdapter(salesReportAdapter);
        updateTotalRevenue();

        return view;
    }

    private void updateTotalRevenue() {
        int totalRevenue = salesReportAdapter.getTotalRevenue();
        txtKq.setText("$" + totalRevenue);
    }
}