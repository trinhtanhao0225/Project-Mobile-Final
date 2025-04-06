package com.example.banquanao;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.banquanao.Api.ApiClient;
import com.example.banquanao.Api.DepotApi;
import com.example.banquanao.Api.TypeApi;
import com.example.banquanao.Model.ColorQuantity;
import com.example.banquanao.Model.Depot;
import com.example.banquanao.Model.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDepotActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Spinner typeSpinner;
    private EditText depotNameEditText;
    private EditText priceEditText;
    private EditText discountEditText; // Chưa có trong layout bạn gửi, cần thêm vào XML nếu dùng
    private EditText importDateEditText;
    private EditText descriptionEditText;
    private ImageView depotImageView;
    private ImageButton addImageButton;
    private Button addDepotButton;
    private ImageButton backButton;
    private Button addColorQuantityButton;
    private LinearLayout colorQuantityContainer;

    private Uri selectedImageUri;
    private List<ColorQuantity> colorSizeQuantityList;
    private List<Type> typeList;
    private Integer selectedTypeId;
    private int depotId = -1; // Để xác định edit hay add new

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_admin);

        // Khởi tạo các view
        typeSpinner = findViewById(R.id.typeSpinner);
        depotNameEditText = findViewById(R.id.depotNameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        // discountEditText = findViewById(R.id.discountEditText); // Thêm vào layout nếu cần
        importDateEditText = findViewById(R.id.importDateEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        depotImageView = findViewById(R.id.depotImageView);
        addImageButton = findViewById(R.id.addImageButton);
        addDepotButton = findViewById(R.id.addDepotButton);
        backButton = findViewById(R.id.backButton);
        addColorQuantityButton = findViewById(R.id.addColorQuantityButton);
        colorQuantityContainer = findViewById(R.id.colorQuantityContainer);

        // Khởi tạo danh sách
        colorSizeQuantityList = new ArrayList<>();
        typeList = new ArrayList<>();

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("depot_id")) {
            depotId = intent.getIntExtra("depot_id", -1);
            Depot depot = (Depot) intent.getSerializableExtra("depot");
            if (depot != null) {
                fillDepotData(depot); // Điền dữ liệu Depot nếu có
                loadColorQuantitiesFromDepot(depot);
            }
        }

        // Load danh sách Type từ API
        loadTypes();

        // Thiết lập Spinner cho Type
        ArrayAdapter<Type> typeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                typeList
        );
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTypeId = typeList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTypeId = null;
            }
        });

        // Load ColorQuantity nếu là edit
        if (depotId != -1) {

        }

        // Xử lý nút Back
        backButton.setOnClickListener(v -> finish());

        // Xử lý chọn hình ảnh
        addImageButton.setOnClickListener(v -> {
            Intent imageIntent = new Intent(Intent.ACTION_PICK);
            imageIntent.setType("image/*");
            startActivityForResult(imageIntent, PICK_IMAGE_REQUEST);
        });

        // Xử lý chọn ngày nhập hàng
        importDateEditText.setOnClickListener(v -> {
            LocalDate today = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? LocalDate.now() : null;
            if (today != null) {
                DatePickerDialog datePicker = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    datePicker = new DatePickerDialog(
                            AddDepotActivity.this,
                            (view, year, month, dayOfMonth) -> {
                                LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    importDateEditText.setText(selectedDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
                                }
                            },
                            today.getYear(),
                            today.getMonthValue() - 1,
                            today.getDayOfMonth()
                    );
                }
                datePicker.show();
            }
        });

        // Xử lý thêm Color, Size, và Quantity
        addColorQuantityButton.setOnClickListener(v -> addColorQuantityField(null));

        // Xử lý nút Add Depot
        addDepotButton.setOnClickListener(v -> addDepot());
    }

    private void loadTypes() {
        TypeApi apiService = ApiClient.getRetrofitInstance().create(TypeApi.class);
        Call<List<Type>> call = apiService.getAllType(); // Giả sử API trả về danh sách Type theo JSON bạn cung cấp
        call.enqueue(new Callback<List<Type>>() {
            @Override
            public void onResponse(@NonNull Call<List<Type>> call, @NonNull Response<List<Type>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    typeList.clear();
                    typeList.addAll(response.body()); // Thêm danh sách Type từ API vào typeList
                    ArrayAdapter<Type> adapter = (ArrayAdapter<Type>) typeSpinner.getAdapter();
                    adapter.notifyDataSetChanged(); // Cập nhật Spinner với dữ liệu mới

                    // Nếu đang chỉnh sửa Depot, chọn Type tương ứng
                    if (depotId != -1) {
                        Intent intent = getIntent();
                        Depot depot = (Depot) intent.getSerializableExtra("depot");
                        if (depot != null && depot.getType() != null) {
                            for (int i = 0; i < typeList.size(); i++) {
                                if (typeList.get(i).getId() == depot.getType().getId()) {
                                    typeSpinner.setSelection(i); // Chọn Type khớp với Depot
                                    selectedTypeId = typeList.get(i).getId();
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(AddDepotActivity.this,
                            "Không thể tải danh sách danh mục: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Type>> call, @NonNull Throwable t) {
                Toast.makeText(AddDepotActivity.this,
                        "Lỗi khi tải danh mục: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadColorQuantitiesFromDepot(Depot depot) {
        // Kiểm tra nếu Depot có danh sách stock
        if (depot.getStock() != null && !depot.getStock().isEmpty()) {
            List<ColorQuantity> stock = depot.getStock();  // Lấy stock từ Depot
            for (ColorQuantity cq : stock) {
                addColorQuantityField(cq); // Hiển thị từng ColorQuantity trong UI
            }
        } else {
            Toast.makeText(AddDepotActivity.this, "No color quantities available.", Toast.LENGTH_SHORT).show();
        }
    }


    private void fillDepotData(Depot depot) {
        depotNameEditText.setText(depot.getName());
        priceEditText.setText(String.valueOf(depot.getPrice()));
        importDateEditText.setText(depot.getImportDate()); // Giả sử Depot có field này
        descriptionEditText.setText(depot.getDescription());
        // Nếu Depot có image URL, bạn cần load nó (dùng Glide/Picasso)
        // selectedImageUri = Uri.parse(depot.getImageUrl());
        // depotImageView.setImageURI(selectedImageUri);

        // Chọn Type trong Spinner
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getId() == depot.getType().getId()) { // Giả sử Depot có typeId
                typeSpinner.setSelection(i);
                break;
            }
        }
    }

    private void addColorQuantityField(ColorQuantity cq) {
        View colorQuantityView = LayoutInflater.from(this)
                .inflate(R.layout.item_color_quantity_admin, colorQuantityContainer, false);

        EditText colorEditText = colorQuantityView.findViewById(R.id.colorEditText);
        EditText sizeEditText = colorQuantityView.findViewById(R.id.sizeEditText);
        EditText quantityEditText = colorQuantityView.findViewById(R.id.quantityEditText);
        ImageButton removeButton = colorQuantityView.findViewById(R.id.removeColorQuantityButton);

        // Nếu có ColorQuantity (edit mode), điền dữ liệu
        if (cq != null) {
            colorEditText.setText(cq.getColor());
            sizeEditText.setText(cq.getSize());
            quantityEditText.setText(String.valueOf(cq.getQuantity()));
        }

        removeButton.setOnClickListener(v -> colorQuantityContainer.removeView(colorQuantityView));
        colorQuantityContainer.addView(colorQuantityView);
    }

    private void addDepot() {
        String name = depotNameEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();
        String importDate = importDateEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        // Kiểm tra các trường bắt buộc
        if (name.isEmpty() || price.isEmpty() || importDate.isEmpty() || selectedTypeId == null) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal priceValue;
        try {
            priceValue = new BigDecimal(price);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy danh sách Color, Size, và Quantity
        colorSizeQuantityList.clear();
        for (int i = 0; i < colorQuantityContainer.getChildCount(); i++) {
            View view = colorQuantityContainer.getChildAt(i);
            EditText colorEditText = view.findViewById(R.id.colorEditText);
            EditText sizeEditText = view.findViewById(R.id.sizeEditText);
            EditText quantityEditText = view.findViewById(R.id.quantityEditText);

            String color = colorEditText.getText().toString().trim();
            String size = sizeEditText.getText().toString().trim();
            String quantity = quantityEditText.getText().toString().trim();

            if (!color.isEmpty() && !size.isEmpty() && !quantity.isEmpty()) {
                colorSizeQuantityList.add(new ColorQuantity(size, Integer.parseInt(quantity), color));
            }
        }

        if (colorSizeQuantityList.isEmpty()) {
            Toast.makeText(this, "Please add at least one color, size, and quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo Intent để trả dữ liệu về Activity trước đó
        Intent resultIntent = new Intent();
        resultIntent.putExtra("depotId", depotId); // Trả depotId để biết là edit hay add
        resultIntent.putExtra("typeId", selectedTypeId);
        resultIntent.putExtra("depotName", name);
        resultIntent.putExtra("price", priceValue);
        resultIntent.putExtra("importDate", importDate);
        resultIntent.putExtra("description", description);
        resultIntent.putExtra("imageUri", selectedImageUri != null ? selectedImageUri.toString() : null);
        resultIntent.putExtra("colorSizeQuantityList", (java.io.Serializable) colorSizeQuantityList);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            depotImageView.setImageURI(selectedImageUri);
        }
    }
}