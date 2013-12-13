package ru.glamour.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import ru.zubanoff.sqlitejson.SQONHandler;
import ru.zubanoff.sqlitejson.SQONItem;

/**
 *
 * @author tuganov
 */
public class Core {

    private final SQONHandler sqon;

    public Core() {
        sqon = new SQONHandler("jdbc:sqlite:d:\\Projects\\NetBeansProjects\\SQON\\db\\json.db");
    }

    public int addEmployee(String value) {
        return sqon.insert("employee", value);
    }

    public boolean updateEmployee(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeEmployee(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllEmployee() {
        try {
            ArrayList<JSONObject> list = new ArrayList<>();
            List<SQONItem> items = sqon.getValues("employee");
            for (SQONItem item : items) {
                JSONObject jsonObject = new JSONObject(item.getJSON());
                list.add(jsonObject);
            }

            return list;

        } catch (JSONException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public int addCustomer(String value) {
        return sqon.insert("customer", value);
    }

    public boolean updateCustomer(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeCustomer(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllCustomer() {
        try {
            ArrayList<JSONObject> list = new ArrayList<>();
            List<SQONItem> items = sqon.getValues("customer");
            for (SQONItem item : items) {
                JSONObject jsonObject = new JSONObject(item.getJSON());
                list.add(jsonObject);
            }

            return list;

        } catch (JSONException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public int addEquipment(String value) {
        return sqon.insert("equipment", value);
    }

    public boolean updateEquipment(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeEquipment(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllEquipment() {
        try {
            ArrayList<JSONObject> list = new ArrayList<>();
            List<SQONItem> items = sqon.getValues("equipment");
            for (SQONItem item : items) {
                JSONObject jsonObject = new JSONObject(item.getJSON());
                list.add(jsonObject);
            }

            return list;

        } catch (JSONException e) {
            Logger.getGlobal().log(Level.SEVERE, null, e);
            return new ArrayList<>();
        }
    }

    public boolean setWorkHoursByDay(String value) {
        return sqon.addOrModify("workHoursByDay", value);
    }

    public boolean setOperations(String value) {
        return sqon.addOrModify("opertaions", value);
    }

    public boolean setBooking(int dateStart, int dateEnd, String value) {
        int id = -1;
        boolean success = false;

        List<SQONItem> items = sqon.getValuesByKeyAndDateInterval("booking", dateStart, dateEnd);

        if (items.isEmpty()) {
            id = sqon.insert("booking", value);
        } else {
            success = sqon.update(items.get(0).getRowid(), value);
        }

        return id >= 0 || success != false;
    }

    public List<JSONObject> getBookingByDates(int dateStart, int dateEnd) {
        try {
            List<SQONItem> items = sqon.getValuesByKeyAndDateInterval("booking", dateStart, dateEnd);
            ArrayList<JSONObject> booking = new ArrayList<>();
            for (SQONItem item : items) {
                booking.add(new JSONObject(item.getJSON()));
            }

            return booking;

        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, null, e);
            return new ArrayList<>();
        }
    }
    
    // sales
    
    // salary distribution by operation by employee
    
    // goods in - out

}
