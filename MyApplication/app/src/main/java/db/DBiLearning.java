package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Controll.LearController;
import aplicatie.Copil;
import aplicatie.Rezultat;
import aplicatie.User;


public class DBiLearning extends SQLiteOpenHelper implements LearController {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "iLearningApplication.db";


    private static final String SQL_CREATE_COPIL ="CREATE TABLE copil (id INTEGER PRIMARY KEY, idParinte INTEGER,  idCopil INTEGER, nivel TEXT)";
    private static final String SQL_CREATE_USER = "CREATE TABLE user (id INTEGER PRIMARY KEY, nume TEXT, username TEXT, parola TEXT, tip TEXT)";
    private static final String SQL_CREATE_REZULTAT = "CREATE TABLE rezultat (id INTEGER PRIMARY KEY, idCopil INTEGER, idTest TEXT, raspuns TEXT, dificultate TEXT, nota TEXT)";

    private static final String SQL_DELETE_COPIL = "DROP TABLE IF EXISTS copil ";
    private static final String SQL_DELETE_USER = "DROP TABLE IF EXISTS user" ;
    private static final String SQL_DELETE_REZULTAT = "DROP TABLE IF EXISTS rezultat";



    public DBiLearning(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COPIL);
        db.execSQL(SQL_CREATE_REZULTAT);
        db.execSQL(SQL_CREATE_USER);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COPIL);
        db.execSQL(SQL_DELETE_REZULTAT);
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    @Override
    public List<User> getAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {" id ", " nume ", " username "," parola ", "tip "};

        String sortOrder =  "nume " + " DESC";

        Cursor cursor = db.query(
                "user",// The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nume = cursor.getString(cursor.getColumnIndex("nume"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String pass = cursor.getString(cursor.getColumnIndex("parola"));
            String tip = cursor.getString(cursor.getColumnIndex("tip"));
            User user = new User(nume,username,pass,tip, id);
            users.add(user);
        }
        cursor.close();
        return users;
    }

    @Override
    public List<Rezultat> getAllResults() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {" id ", " idTest ", " idCopil "," dificultate ", " raspuns ", " nota "};

        String sortOrder =  " id " + " DESC";

        Cursor cursor = db.query(
                "rezultat",// The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Rezultat> results = new ArrayList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String idTest = cursor.getString(cursor.getColumnIndex("idTest"));
            int idCopil = cursor.getInt(cursor.getColumnIndex("idCopil"));
            String raspuns = cursor.getString(cursor.getColumnIndex("raspuns"));
            String dificultate = cursor.getString(cursor.getColumnIndex("dificultate"));
            String nota = cursor.getString(cursor.getColumnIndex("nota"));
            Rezultat rezultat = new Rezultat(id,idCopil,idTest,raspuns,nota,dificultate);
            results.add(rezultat);
        }
        cursor.close();
        return results;
    }

    @Override
    public List<Copil> getAllChildren() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {" id ", " idParinte ", " idCopil "," nivel "};

        String sortOrder =  "id " + " DESC";

        Cursor cursor = db.query(
                "copil",// The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<Copil> copii = new ArrayList<>();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int idParinte = cursor.getInt(cursor.getColumnIndex("idParinte"));
            int idCopil = cursor.getInt(cursor.getColumnIndex("idCopil"));
            String rezultate = cursor.getString(cursor.getColumnIndex("nivel"));
            Copil copil = new Copil(id,idParinte,idCopil,rezultate);
            copii.add(copil);
        }
        cursor.close();
        return copii;
    }

    @Override
    public User getByIdUser(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {" nume ", " username "," parola " ," tip "};

        String selection = "id " + " = ?";
        String[] selectionArgs = {Integer.toString(id)};

        String sortOrder = " id " + " DESC";

        Cursor cursor = db.query(
                "user",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            String nume = cursor.getString(cursor.getColumnIndex("nume"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String pass = cursor.getString(cursor.getColumnIndex("parola"));
            String tip = cursor.getString(cursor.getColumnIndex("tip"));
            User user = new User(nume, username, pass, tip, id);
            user.setId(id);
            users.add(user);
        }
        cursor.close();
        if (users.size() == 1) {
            return users.get(0);
        }
        return new User("","","","",0);

    }

    @Override
    public Copil getByIdCopil(int id, String column) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {" id "," idParinte "," idCopil" ," nivel "};

        String selection = column + " = ?";
        String[] selectionArgs = {Integer.toString(id)};

        String sortOrder = column + " DESC";

        Cursor cursor = db.query(
                "copil",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Copil> copii = new ArrayList<>();
        while (cursor.moveToNext()) {
            int  idC = cursor.getInt(cursor.getColumnIndex("id"));
            int idParinte = cursor.getInt(cursor.getColumnIndex("idParinte"));
            int idCopil = cursor.getInt(cursor.getColumnIndex("idCopil"));
            String rezultate = cursor.getString(cursor.getColumnIndex("nivel"));
            Copil copil = new Copil(idC, idParinte,idCopil, rezultate);
            copii.add(copil);
        }
        cursor.close();
        if (copii.size() == 1) {
            return copii.get(0);
        }
        return new Copil(0,0,0,"");
    }


    @Override
    public Rezultat getByIdRezultat(int id, String column) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {" id ", " idTest ", " idCopil "," dificultate ", " raspuns ", " nota "};

        String selection = column+ " = ?";
        String[] selectionArgs = {Integer.toString(id)};

        String sortOrder = column + " DESC";

        Cursor cursor = db.query(
                "rezultat",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Rezultat> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            int  idR = cursor.getInt(cursor.getColumnIndex("id"));
            String idTest = cursor.getString(cursor.getColumnIndex("idTest"));
            int idCopil = cursor.getInt(cursor.getColumnIndex("idCopil"));
            String raspuns = cursor.getString(cursor.getColumnIndex("raspuns"));
            String dificultate = cursor.getString(cursor.getColumnIndex("dificultate"));
            String nota = cursor.getString(cursor.getColumnIndex("nota"));
            Rezultat rezultat = new Rezultat(idR,idCopil,idTest,raspuns,nota,dificultate);
            results.add(rezultat);
        }
        cursor.close();
        if (results.size() == 1) {
            return results.get(0);
        }
        return new Rezultat(0,0,"","","","");
    }


    public List<Rezultat> getResults(int id, String column) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {" id ", " idTest ", " idCopil "," dificultate ", " raspuns ", " nota "};

        String selection = column+ " = ?";
        String[] selectionArgs = {Integer.toString(id)};

        String sortOrder = column + " DESC";

        Cursor cursor = db.query(
                "rezultat",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List<Rezultat> results = new ArrayList<>();
        while (cursor.moveToNext()) {
            int  idR = cursor.getInt(cursor.getColumnIndex("id"));
            String idTest = cursor.getString(cursor.getColumnIndex("idTest"));
            int idCopil = cursor.getInt(cursor.getColumnIndex("idCopil"));
            String raspuns = cursor.getString(cursor.getColumnIndex("raspuns"));
            String nota = cursor.getString(cursor.getColumnIndex("nota"));
            String dificultate = cursor.getString(cursor.getColumnIndex("dificultate"));
            Rezultat rezultat = new Rezultat(idR,idCopil,idTest,raspuns,nota,dificultate);
            results.add(rezultat);
        }
        cursor.close();
        return results;
    }

    @Override
    public void addUser(User user) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        if (getByName(user.getNameUser()) == 1) {
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(" username ", user.getUsername());
            values.put(" tip ", user.getTip());
            values.put(" nume ", user.getNameUser());
            values.put(" parola ", user.getParola());

            db.insert(" user ", null, values);
        }

    }

    @Override
    public void addCopil(Copil copil) {
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(" idParinte ", copil.getIdParitne());
        values.put(" idCopil", copil.getIdCopil());
        values.put(" nivel ",copil.getRezultate());
        db.insert(" copil ", null, values);
    }

    @Override
    public void addRezultat(Rezultat rezultat) {
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(" idCopil ", rezultat.getIdCopil());
        values.put(" idTest ", rezultat.getIdTest());
        values.put(" raspuns ", rezultat.getRaspuns());
        values.put(" dificultate ", rezultat.getDificultate());
        values.put(" nota ", rezultat.getNota());

        db.insert(" rezultat ", null, values);
    }

    @Override
    public String getMaxId(String tableName) {
        String id="";
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {" COUNT ( id ) "};

        Cursor cursor = db.query(
                "user",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        if (cursor.moveToNext()) {
            id = cursor.getString(0);
        }
        cursor.close();
        return id ;
    }


    public int getByName (String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"id "," nume ", " username ", " parola "," tip "};
        String selection = " nume " + " = ?";
        String[] selectionArgs = {name};
        String sortOrder = " nume  " + " DESC";

        Cursor cursor = db.query(
                "user",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            int Id = cursor.getInt(cursor.getColumnIndex("id"));
            String nume = cursor.getString(cursor.getColumnIndex("nume"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String pass = cursor.getString(cursor.getColumnIndex("parola"));
            String tip = cursor.getString(cursor.getColumnIndex("tip"));
            User user = new User(nume, username, pass, tip, Id);
            users.add(user);
        }
        cursor.close();
        if (users.size()  > 0) {
            return 0;
        }

        return 1;
    }

    public User getUserByName (String name){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"id "," nume ", " username ", " parola "," tip "};
        String selection = " nume " + " = ?";
        String[] selectionArgs = {name};
        String sortOrder = " nume  " + " DESC";

        Cursor cursor = db.query(
                "user",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            int Id = cursor.getInt(cursor.getColumnIndex("id"));
            String nume = cursor.getString(cursor.getColumnIndex("nume"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String pass = cursor.getString(cursor.getColumnIndex("parola"));
            String tip = cursor.getString(cursor.getColumnIndex("tip"));
            User user = new User(nume, username, pass, tip, Id);
            users.add(user);
        }
        cursor.close();
        if (users.size()  ==  0) {
            return new User("","","","",0);
        }
        return users.get(0);
    }

    public User getByUsername (String username){
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"id "," nume ", " parola "," tip "};
        String selection = " username " + " = ?";
        String[] selectionArgs = {username};
        String sortOrder = " username  " + " DESC";

        Cursor cursor = db.query(
                "user",   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            int Id = cursor.getInt(cursor.getColumnIndex("id"));
            String nume = cursor.getString(cursor.getColumnIndex("nume"));
            String pass = cursor.getString(cursor.getColumnIndex("parola"));
            String tip = cursor.getString(cursor.getColumnIndex("tip"));
            User user = new User(nume, username, pass, tip, Id);
            users.add(user);
        }
        cursor.close();
        if (users.size()  == 1) {
           return users.get(0);
        }
        else
            return new User("","","","",0);
    }

    public void delete(int id, String table) {
        SQLiteDatabase db = getWritableDatabase();
        String selection =" id " + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        db.delete(table, selection, selectionArgs);
    }

    public void updateCopil(int id, Copil copil) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("id", copil.getId());
        values.put("idParinte", copil.getIdParitne());
        values.put("idCopil", copil.getIdCopil());
        values.put("nivel", copil.getRezultate());

        // Which row to update, based on the title
        String selection = " id " + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        db.update(
                "copil",
                values,
                selection,
                selectionArgs);
    }
    public void updateUser(int id, User user) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("nume", user.getNameUser());
        values.put("username", user.getUsername());
        values.put("parola", user.getParola());
        values.put("tip", user.getTip());

        // Which row to update, based on the title
        String selection = " id " + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        db.update(
                "user",
                values,
                selection,
                selectionArgs);
    }

    public void updateRezultat(int id, Rezultat rezultat) {
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put("id", rezultat.getId());
        values.put("idCopil", rezultat.getIdCopil());
        values.put("idTest", rezultat.getIdTest());
        values.put("dificultate", rezultat.getDificultate());
        values.put("raspuns", rezultat.getRaspuns());
        values.put("nota", rezultat.getNota());

        // Which row to update, based on the title
        String selection = " id " + " = ?";
        String[] selectionArgs = { Integer.toString(id) };

        db.update(
                "rezultat",
                values,
                selection,
                selectionArgs);
    }
}
