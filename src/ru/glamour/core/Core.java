package ru.glamour.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import ru.glamour.utils.DateTime;
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
    
    private ArrayList<JSONObject> getValuesByKey(String key){
        try {
            ArrayList<JSONObject> list = new ArrayList<>();
            List<SQONItem> items = sqon.getValues(key);
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
        return getValuesByKey("customer");
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
        return getValuesByKey("equipment");
    }

    public boolean setWorkHoursByDay(String value) {
        return sqon.addOrModify("workHoursByDay", value);
    }
    
    public JSONObject getWorkHoursByDay() {
        JSONObject json = new JSONObject();
        if(getValuesByKey("equipment").isEmpty()){
            return json;
        } else {
            return getValuesByKey("equipment").get(0);
        }
    }

    public boolean setOperations(String value) {
        return sqon.addOrModify("opertaions", value);
    }
    
    public JSONObject getOperations() {
        JSONObject json = new JSONObject();
        if(getValuesByKey("opertaions").isEmpty()){
            return json;
        } else {
            return getValuesByKey("opertaions").get(0);
        }
    }

    public boolean setBooking(Date date, String value) {
        int id = -1;
        boolean success = false;

        int _dateStart = (int)(DateTime.getDateBeginOfDay(date).getTime() / 1000);
        int _dateEnd = (int)(DateTime.getDateEndOfDay(date).getTime() / 1000);
        List<SQONItem> items = sqon.getValuesByKeyAndDateInterval("booking", _dateStart, _dateEnd);

        if (items.isEmpty()) {
            id = sqon.insert("booking", value, _dateStart);
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
    public ArrayList<JSONObject> computeSalary(){
        ArrayList<JSONObject> list = new ArrayList<>();
        // TODO
        return list;
    }
    
    // salary distribution by operation by employee
    public int addSalaryDistribution(String value) {
        return sqon.insert("salaryDistribution", value);
    }

    public boolean updateSalaryDistribution(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeSalaryDistribution(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllSalaryDistribution() {
        return getValuesByKey("salaryDistribution");
    }
    
    // add goods
    public int addGoods(String value) {
        return sqon.insert("goods", value);
    }

    public boolean updateGoods(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeGoods(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllGoods() {
        return getValuesByKey("goods");
    }
    
    // goods in - out
    public int addGoodsOrder(String value) {
        return sqon.insert("goods", value);
    }

    public boolean updateGoodsOrder(int rowid, String value) {
        return sqon.update(rowid, value);
    }

    public boolean removeGoodsOrder(int rowid) {
        return sqon.delete(rowid);
    }

    public ArrayList<JSONObject> getAllGoodsOrder() {
        return getValuesByKey("goods");
    }
    
    public ArrayList<JSONObject> getReports(int ... reportType) {
        // TODO
        return null;
    }
    
    
}
