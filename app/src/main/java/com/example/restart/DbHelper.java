package com.example.restart;

import static com.example.restart.ImageHelper.byteArrayToBitmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.restart.NewFeed.OnlyMeModal;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    Context context;

    // Database name and version
    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USER = "user";
    private static final String TABLE_PROPERTY = "property";

    // Common column names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_ID_PROPERTY = "ID";

    // User table column names
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_CON_PASSWORD = "con_password";
    private static final String COLUMN_USER_FULL_NAME = "full_name";
    private static final String COLUMN_USER_PHONE = "phone";
    private static final String COLUMN_USER_PROFILE = "profile";

    // Property table column names
    private static final String COLUMN_PROPERTY_TYPE = "property_type";
    private static final String COLUMN_PROPERTY_BEDROOM_TYPE = "bedroom_type";
    private static final String COLUMN_PROPERTY_PRICE = "price";
    private static final String COLUMN_PROPERTY_FURNITURE = "furniture";
    private static final String COLUMN_PROPERTY_REMARK = "remark";
    private static final String COLUMN_PROPERTY_IMAGE = "property_image";
    private static final String COLUMN_PROPERTY_FOREIGN_KEY_USER_ID = "user_id";

    private static final String COLUMN_PROPERTY_DATE = "date_upload";

    // Table creation SQL statements
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_USER_USERNAME + " TEXT," +
            COLUMN_USER_PASSWORD + " TEXT," +
            COLUMN_USER_CON_PASSWORD + " TEXT," +
            COLUMN_USER_FULL_NAME + " TEXT," +
            COLUMN_USER_PHONE + " TEXT," +
            COLUMN_USER_PROFILE + " BLOB)";

    private static final String CREATE_TABLE_PROPERTY = "CREATE TABLE " + TABLE_PROPERTY + " (" +
            COLUMN_ID_PROPERTY + " INTEGER PRIMARY KEY," +
            COLUMN_PROPERTY_TYPE + " TEXT," +
            COLUMN_PROPERTY_BEDROOM_TYPE + " TEXT," +
            COLUMN_PROPERTY_PRICE + " REAL," +
            COLUMN_PROPERTY_FURNITURE + " TEXT," +
            COLUMN_PROPERTY_REMARK + " TEXT," +
            COLUMN_PROPERTY_DATE + " TEXT," +
            COLUMN_PROPERTY_IMAGE + " BLOB," +
            COLUMN_PROPERTY_FOREIGN_KEY_USER_ID + " INTEGER," +
            "FOREIGN KEY(" + COLUMN_PROPERTY_FOREIGN_KEY_USER_ID + ") REFERENCES " +
            TABLE_USER + "(" + COLUMN_ID + "))";


    public DbHelper(Context context)
    {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PROPERTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic (if needed)
        // Here, you might drop existing tables and recreate them

        // Drop existing tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPERTY);

        // Recreate the tables
        onCreate(db);
    }

    // Method to check if a username and password already exist
    public boolean isUserExists(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to check if the username and password exist
            String query = "SELECT * FROM " + TABLE_USER +
                    " WHERE " + COLUMN_USER_USERNAME + " = ? AND " +
                    COLUMN_USER_PASSWORD + " = ?";

            cursor = db.rawQuery(query, new String[]{username, password});

            // Check if any rows are returned
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

    // Method to add a UserModel to the "user" table
    public void addUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Put values into ContentValues
        values.put(COLUMN_ID,user.getUserID());

        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_CON_PASSWORD, user.getConPassword());
        values.put(COLUMN_USER_FULL_NAME, user.getFullName());
        values.put(COLUMN_USER_PROFILE, user.getPhoto());
        values.put(COLUMN_USER_PHONE,user.getPhone());


        // Insert into "user" table
        db.insert(TABLE_USER, null, values);

        db.close();

    }
    // Method to check if a phone number already exists
    public boolean isPhoneExists(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to check if the phone number already exists
            String query = "SELECT * FROM " + TABLE_USER +
                    " WHERE " + COLUMN_USER_PHONE + " = ?";

            cursor = db.rawQuery(query, new String[]{phone});

            // Check if any rows are returned
            return cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    // Method to retrieve UserID by username and password
    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to retrieve UserID by username and password
            String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_USER +
                    " WHERE " + COLUMN_USER_USERNAME + " = ? AND " +
                    COLUMN_USER_PASSWORD + " = ?";

            cursor = db.rawQuery(query, new String[]{username, password});

            // Check if any rows are returned
            if (cursor != null && cursor.moveToFirst()) {
                // Return the UserID
                return cursor.getInt(cursor.getColumnIndexOrThrow("_id"));


            } else {
                // Return -1 if no matching user is found or cursor is null
                return -1;
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during database operations
            e.printStackTrace();
            return -1; // or throw a specific exception if needed
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    // Method to check if a username and password combination exists
    public boolean isValidCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to check if the username and password exist
            String query = "SELECT * FROM " + TABLE_USER +
                    " WHERE " + COLUMN_USER_USERNAME + " = ? AND " +
                    COLUMN_USER_PASSWORD + " = ?";

            cursor = db.rawQuery(query, new String[]{username, password});

            // Check if any rows are returned
            return cursor.getCount() > 0;
        } catch (Exception e) {
            // Handle any exceptions that might occur during database operations
            e.printStackTrace();
            return false; // or throw a specific exception if needed
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    public String getFullNameByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to retrieve full_name by UserID
            String query = "SELECT " + COLUMN_USER_FULL_NAME + " FROM " + TABLE_USER +
                    " WHERE " + COLUMN_ID + " = ?";

            cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

            // Check if any rows are returned
            if (cursor != null && cursor.moveToFirst()) {
                // Return the full_name
                return cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_FULL_NAME));
            } else {
                // Return null if no matching user is found or cursor is null
                return null;
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during database operations
            e.printStackTrace();
            return null; // or throw a specific exception if needed
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
    public boolean updateUserData(int userId, String fullName, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_USER + " SET ";

        // Update full name if provided
        if (fullName != null) {
            query += COLUMN_USER_FULL_NAME + " = '" + fullName + "', ";
        }

        // Update profile image if provided
        if (image != null) {
            query += COLUMN_USER_PROFILE + " = ?, ";
        }

        // Remove the trailing comma and space
        query = query.substring(0, query.length() - 2);

        // Add the WHERE clause
        query += " WHERE " + COLUMN_ID + " = " + userId;

        try {
            // Use raw SQL query for the update
            if (image != null) {
                db.execSQL(query, new Object[]{image});
            } else {
                db.execSQL(query);
            }

            // Close the database
            db.close();

            // Return true to indicate success
            return true;
        } catch (SQLException e) {
            // Handle any exceptions, log or print error message
            e.printStackTrace();

            // Return false to indicate failure
            return false;
        }
    }
    public UserModel getUserDataById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_USER_PROFILE, COLUMN_USER_FULL_NAME};
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        UserModel userData = null;

        if (cursor != null && cursor.moveToFirst()) {
            int imageColumnIndex = cursor.getColumnIndex(COLUMN_USER_PROFILE);
            int fullNameColumnIndex = cursor.getColumnIndex(COLUMN_USER_FULL_NAME);

            if (imageColumnIndex >= 0 && fullNameColumnIndex >= 0) {
                byte[] image = cursor.getBlob(imageColumnIndex);
                String fullName = cursor.getString(fullNameColumnIndex);

                // Create a UserData object with retrieved values
                userData = new UserModel(fullName,image);
            } else {
                // Throw an exception if column indices are not found
                throw new RuntimeException("Column indices not found for TABLE_USER columns.");
                // Handle the case where column indices are not found
                // Log or throw an exception, depending on your error handling strategy
            }

            // Create a UserData object with retrieved values


            // Close the cursor
            cursor.close();
        }

        // Close the database
        db.close();

        return userData;
    }
    public boolean insertProperty(PropertyModel propertyModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PROPERTY,propertyModel.getPropertyID());
        values.put(COLUMN_PROPERTY_TYPE, propertyModel.getPropertyType());
        values.put(COLUMN_PROPERTY_BEDROOM_TYPE, propertyModel.getBedroomType());
        values.put(COLUMN_PROPERTY_PRICE, propertyModel.getPrice());
        values.put(COLUMN_PROPERTY_FURNITURE, propertyModel.getFurniture());
        values.put(COLUMN_PROPERTY_REMARK, propertyModel.getRemark());
        values.put(COLUMN_PROPERTY_DATE, propertyModel.getDate());
        values.put(COLUMN_PROPERTY_IMAGE, propertyModel.getPropertyImage());
        values.put(COLUMN_PROPERTY_FOREIGN_KEY_USER_ID, propertyModel.getForeignKeyUserID());

        try {
            long newRowId = db.insert(TABLE_PROPERTY, null, values);

            // Close the database
            db.close();

            // Return true if the insertion was successful
            return newRowId != -1;
        } catch (Exception e) {
            // Handle the exception, log or print error message
            e.printStackTrace();

            // Return false to indicate failure
            return false;
        }
    }
    public List<NewFeedModel> getCombinedData() {
        List<NewFeedModel> combinedDataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                TABLE_PROPERTY + "." + COLUMN_ID_PROPERTY,
                COLUMN_PROPERTY_TYPE,
                COLUMN_PROPERTY_BEDROOM_TYPE,
                COLUMN_PROPERTY_PRICE,
                COLUMN_PROPERTY_FURNITURE,
                COLUMN_PROPERTY_REMARK,
                COLUMN_PROPERTY_DATE,
                COLUMN_PROPERTY_IMAGE,
                TABLE_USER + "." + COLUMN_USER_FULL_NAME,
                TABLE_USER + "." + COLUMN_USER_PHONE,
                TABLE_USER + "." + COLUMN_USER_PROFILE
        };

        // Define the JOIN clause
        String joinClause = TABLE_PROPERTY + " JOIN " + TABLE_USER + " ON " +
                TABLE_PROPERTY + "." + COLUMN_PROPERTY_FOREIGN_KEY_USER_ID + " = " +
                TABLE_USER + "." + COLUMN_ID;

        // Execute the query
        Cursor cursor = db.query(joinClause, columns, null, null, null, null, null);

        // Check if there are any results
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int pIDColumnIndex = cursor.getColumnIndex(COLUMN_ID_PROPERTY);
                int propertyTypeColumn = cursor.getColumnIndex(COLUMN_PROPERTY_TYPE);
                int bedroomColumn = cursor.getColumnIndex(COLUMN_PROPERTY_BEDROOM_TYPE);
                int priceColumn = cursor.getColumnIndex(COLUMN_PROPERTY_PRICE);
                int furnitureColumn = cursor.getColumnIndex(COLUMN_PROPERTY_FURNITURE);
                int remarkColumn = cursor.getColumnIndex(COLUMN_PROPERTY_REMARK);
                int dateColumn = cursor.getColumnIndex(COLUMN_PROPERTY_DATE);
                int imageColumn = cursor.getColumnIndex(COLUMN_PROPERTY_IMAGE);
                int fullNameColumn = cursor.getColumnIndex(COLUMN_USER_FULL_NAME);
                int phoneColumn = cursor.getColumnIndex(COLUMN_USER_PHONE);
                int userProfileColumn = cursor.getColumnIndex(COLUMN_USER_PROFILE);
                if(pIDColumnIndex >= 0 && propertyTypeColumn >= 0 && bedroomColumn >= 0 && priceColumn >= 0 && furnitureColumn >= 0 && remarkColumn >= 0 && dateColumn >= 0 && imageColumn >= 0 && fullNameColumn >= 0 && phoneColumn >= 0 && userProfileColumn >=0)
                {
                    byte[] userProfile = cursor.getBlob(userProfileColumn);
                    byte[] uploadedImage = cursor.getBlob(imageColumn);
                    Bitmap profilePicture = ImageHelper.byteArrayToBitmap(userProfile);
                    Bitmap uploadedPicture =  ImageHelper.byteArrayToBitmap(uploadedImage);
                    int propertyID = cursor.getInt(pIDColumnIndex);
                    String propertyType = cursor.getString(propertyTypeColumn);
                    String bedroomType = cursor.getString(bedroomColumn);
                    String price = String.valueOf(cursor.getFloat(priceColumn));
                    String furniture = cursor.getString(furnitureColumn);
                    String remark = cursor.getString(remarkColumn);
                    String date = cursor.getString(dateColumn);
                    String fullName = cursor.getString(fullNameColumn);
                    String phone = cursor.getString(phoneColumn);
                    NewFeedModel newFeedModel = new NewFeedModel(propertyID,propertyType,bedroomType,price,furniture,remark,fullName,phone,date,profilePicture,uploadedPicture);
                    combinedDataList.add(newFeedModel);

                }else{
                    throw new RuntimeException("Column indices not found for TABLE_USER columns.");
                }

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return combinedDataList;
    }
    public List<NewFeedModel> getCombinedDataForUser(int userId) {
        List<NewFeedModel> combinedDataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve
        String[] columns = {
                TABLE_PROPERTY + "." + COLUMN_ID_PROPERTY,
                COLUMN_PROPERTY_TYPE,
                COLUMN_PROPERTY_BEDROOM_TYPE,
                COLUMN_PROPERTY_PRICE,
                COLUMN_PROPERTY_FURNITURE,
                COLUMN_PROPERTY_REMARK,
                COLUMN_PROPERTY_DATE,
                COLUMN_PROPERTY_IMAGE,
                TABLE_USER + "." + COLUMN_USER_FULL_NAME,
                TABLE_USER + "." + COLUMN_USER_PHONE,
                TABLE_USER + "." + COLUMN_USER_PROFILE
        };

        // Define the JOIN clause
        String joinClause = TABLE_PROPERTY + " JOIN " + TABLE_USER + " ON " +
                TABLE_PROPERTY + "." + COLUMN_PROPERTY_FOREIGN_KEY_USER_ID + " = " +
                TABLE_USER + "." + COLUMN_ID;

        // Define the WHERE clause to filter by user ID
        String whereClause = TABLE_USER + "." + COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(userId)};

        // Execute the query
        Cursor cursor = db.query(joinClause, columns, whereClause, whereArgs, null, null, null);

        // Check if there are any results
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int pIDColumnIndex = cursor.getColumnIndex(COLUMN_ID_PROPERTY);
                int propertyTypeColumn = cursor.getColumnIndex(COLUMN_PROPERTY_TYPE);
                int bedroomColumn = cursor.getColumnIndex(COLUMN_PROPERTY_BEDROOM_TYPE);
                int priceColumn = cursor.getColumnIndex(COLUMN_PROPERTY_PRICE);
                int furnitureColumn = cursor.getColumnIndex(COLUMN_PROPERTY_FURNITURE);
                int remarkColumn = cursor.getColumnIndex(COLUMN_PROPERTY_REMARK);
                int dateColumn = cursor.getColumnIndex(COLUMN_PROPERTY_DATE);
                int imageColumn = cursor.getColumnIndex(COLUMN_PROPERTY_IMAGE);
                int fullNameColumn = cursor.getColumnIndex(COLUMN_USER_FULL_NAME);
                int phoneColumn = cursor.getColumnIndex(COLUMN_USER_PHONE);
                int userProfileColumn = cursor.getColumnIndex(COLUMN_USER_PROFILE);
                // ... (same processing logic as in the previous method)
                if(pIDColumnIndex >= 0 && propertyTypeColumn >= 0 && bedroomColumn >= 0 && priceColumn >= 0 && furnitureColumn >= 0 && remarkColumn >= 0 && dateColumn >= 0 && imageColumn >= 0 && fullNameColumn >= 0 && phoneColumn >= 0 && userProfileColumn >=0)
                {
                    byte[] userProfile = cursor.getBlob(userProfileColumn);
                    byte[] uploadedImage = cursor.getBlob(imageColumn);
                    Bitmap profilePicture = ImageHelper.byteArrayToBitmap(userProfile);
                    Bitmap uploadedPicture =  ImageHelper.byteArrayToBitmap(uploadedImage);
                    int propertyID = cursor.getInt(pIDColumnIndex);
                    String propertyType = cursor.getString(propertyTypeColumn);
                    String bedroomType = cursor.getString(bedroomColumn);
                    String price = String.valueOf(cursor.getFloat(priceColumn));
                    String furniture = cursor.getString(furnitureColumn);
                    String remark = cursor.getString(remarkColumn);
                    String date = cursor.getString(dateColumn);
                    String fullName = cursor.getString(fullNameColumn);
                    String phone = cursor.getString(phoneColumn);
                    NewFeedModel newFeedModel = new NewFeedModel(propertyID,propertyType,bedroomType,price,furniture,remark,fullName,phone,date,profilePicture,uploadedPicture);
                    combinedDataList.add(newFeedModel);

                }else{
                    throw new RuntimeException("Column indices not found for TABLE_USER columns.");
                }

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return combinedDataList;
    }
    public boolean updateProperty(int propertyId, String propertyType, String bedroomType, float price, String furniture, String remark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_PROPERTY_TYPE, propertyType);
        values.put(COLUMN_PROPERTY_BEDROOM_TYPE, bedroomType);
        values.put(COLUMN_PROPERTY_PRICE, price);
        values.put(COLUMN_PROPERTY_FURNITURE, furniture);
        values.put(COLUMN_PROPERTY_REMARK, remark);

        String whereClause = COLUMN_ID_PROPERTY + " = ?";
        String[] whereArgs = {String.valueOf(propertyId)};
        // updatequery = "update " + TABLE_PROPERTY + " set " ;

        try {
            // Perform the update and check if it was successful
            int rowsAffected = db.update(TABLE_PROPERTY, values, whereClause, whereArgs);
            db.close();
            return rowsAffected > 0; // Returns true if at least one row was affected (update successful)
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }
    public boolean deleteProperty(int propertyId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_ID_PROPERTY + " = ?";
        String[] whereArgs = {String.valueOf(propertyId)};

        try {
            int rowsAffected = db.delete(TABLE_PROPERTY, whereClause, whereArgs);
            db.close();
            return rowsAffected > 0; // Returns true if at least one row was affected (deletion successful)
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        }
    }


}
