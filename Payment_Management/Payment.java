package Payment_Management;

import Database.DatabaseUtils;
import Driver.DateTime;
import Promotion_Management.Promotion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Payment {
    protected int paymentId;
    protected static int nextPaymentId;
    protected int bookingId;
    protected double paymentAmount;
    protected String currency;
    protected String paymentMethod;
    protected String paymentDate;
    protected String paymentTime;
    protected String paymentStatus;

    public Payment() {
    }

    public Payment(int bookingId, double paymentAmount, String currency, String paymentMethod, String paymentDate, String paymentTime, String paymentStatus) {
        this.paymentId = ++nextPaymentId;
        this.bookingId = bookingId;
        this.paymentAmount = paymentAmount;
        this.currency = currency;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    static {
        Object[] params = null;

        ResultSet rs;

        try {
            rs = DatabaseUtils.selectQueryById("COUNT(*)", "PAYMENT", null, params);

            // 获取整数结果
            if (rs.next()) {
                nextPaymentId = rs.getInt(1);
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void pay();

    public void addPayment() {
        String sql = "INSERT INTO PAYMENT (BOOKING_ID, PAYMENT_METHOD, PAYMENT_AMOUNT, CURRENCY, PAYMENT_DATE, PAYMENT_TIME, PAYMENT_STATUS) VALUES (?,?,?,?,?,?,?)";
        Object[] params = {bookingId, paymentMethod, paymentAmount, currency, paymentDate, paymentTime, paymentStatus};

        try {
            int insert = DatabaseUtils.insertQuery(sql, params);

            if(insert == 1) {
                System.out.println("Insert payment successfully.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}