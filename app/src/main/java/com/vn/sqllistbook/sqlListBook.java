package com.vn.sqllistbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class sqlListBook extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String CUSTOMER_TITLE = "CUSTOMER_TITLE";
    public static final String CUSTOMER_AUTHOR = "CUSTOMER_AUTHOR";
    public static final String CUSTOMER_PAGES = "CUSTOMER_PAGES";
    Context context;

    // Khai bao Màn hình nhận dữ liệu, tên cơ sở dữ liệu
    public sqlListBook(@Nullable Context context) {
        super(context, "listBook.db", null, 1);
        this.context = context;
    }

    @Override
    //Khởi tạo SQL với tên bảng và các dữ liệu trong bảng
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + CUSTOMER_TABLE + " (" + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_TITLE + " TEXT, " + CUSTOMER_AUTHOR + " TEXT, " + CUSTOMER_PAGES + " INT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Them dữ liệu vào bảng SQL, Dữ liệu người dùng nhập được chuyển thành dạng object và được thêm vào bảng sql
    public void addOne(objectBook obBook){
        SQLiteDatabase sqlBook = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_TITLE, obBook.getTitle());
        cv.put(CUSTOMER_AUTHOR, obBook.getAuthor());
        cv.put(CUSTOMER_PAGES, obBook.getPages());
        long insert = sqlBook.insert(CUSTOMER_TABLE, null, cv);
        if(insert == -1){
            Toast.makeText(context, "Lỗi tạo dữ liệu", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Tạo dữ liệu thành công", Toast.LENGTH_LONG).show();

        }
    }

    // Lấy toàn bộ dữ liệu trong cơ sở dữ liệu ra và chuyển nó thành dạng hướng đối tượng và thêm vào một List có kiểu objectBook (List<objectBook>)
    public List<objectBook> getEveryone() {
        List<objectBook> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase dbListBook = this.getWritableDatabase();
        Cursor cursor = dbListBook.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            //Lap qua con tro(ket qua truy vấn) và tạo các đối tượng khách hàng mới. Đặt chúng vào danh sách trả về.
            do {
                int customerID = cursor.getInt(0);
                String customerTitle = cursor.getString(1);
                String customerAuthor = cursor.getString(2);
                int customerPages = cursor.getInt(3);
                //Chuyển các biến input đầu vào thành dạng hướng đối tường trước khi cho vào danh sách
                objectBook obBook = new objectBook(customerID, customerTitle, customerAuthor, customerPages);
                //Thêm dữ liều đầu vào vừa chuyển sang hướng đói tượng vào danh sách
                returnList.add(obBook);
            } while (cursor.moveToNext());
        } else {
            // failure, do not add anything to the list

            return returnList;

        }
        cursor.close();
        dbListBook.close();
        return returnList;
    }

    void updateData(objectBook objBook){

        SQLiteDatabase dbBook = this.getWritableDatabase();
        ContentValues cvBook = new ContentValues();
        cvBook.put(CUSTOMER_TITLE, objBook.getTitle());
        cvBook.put(CUSTOMER_AUTHOR, objBook.getAuthor());
        cvBook.put(CUSTOMER_PAGES, objBook.getPages());
        long result = dbBook.update(CUSTOMER_TABLE, cvBook, "CUSTOMER_ID=?", new String[]{String.valueOf(objBook.getId())});
        Toast.makeText(context, objBook.toString(), Toast.LENGTH_SHORT).show();
        if(result == -1 ) {
            Toast.makeText(context, "Lỗi thay đổi dữ liệu. ", Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(context, "Thay đổi dữ liệu thành công. ", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, objBook.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
