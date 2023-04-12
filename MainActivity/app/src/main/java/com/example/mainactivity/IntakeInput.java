package com.example.mainactivity;

/*  IntakeInput CLASS:  used in an ArrayList to keep data/details of raw intake values inputted by the
*                       user into SQLite database*/

public class IntakeInput {
    private int id; //input id (auto-incremented)
    private String inputDate; // format YYYY-M-DD
    private String name; //name of input (given by user)
    private int kcal;  //calories etc etc
    private int protein;
    private int fat;
    private int carb;
    private int foodAmt;
    private String foodUnit; // Serving(s), gram(s), mL, cup(s)
    private int mealType; // 0 = breakfast 1 = lunch 2 = dinner 3 = snacks

// CONSTRUCTORS

    public IntakeInput(int id, String name, String inputDate, int kcal, int protein, int fat, int carb, int foodAmt, String foodUnit, int mealType) {
        this.id = id;
        this.inputDate = inputDate;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.foodAmt = foodAmt;
        this.mealType = mealType;
        this.foodUnit = foodUnit;
    }

    public IntakeInput(int id, String name, String inputDate, int kcal, int foodAmt, String foodUnit, int mealType) {
        this.id = id;
        this.inputDate = inputDate;
        this.name = name;
        this.kcal = kcal;
        this.foodAmt = foodAmt;
        this.foodUnit = foodUnit;
        this.mealType = mealType;
    }

//    public IntakeInput() {
//    }

    public String toString() {
        return "IntakeInput{" +
                "id=" + id +
                ", inputDate='" + inputDate + '\'' +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", foodAmt=" + foodAmt +
                ", foodUnit='" + foodUnit + '\'' +
                ", mealType=" + mealType +
                '}';
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getFoodAmt() {
        return foodAmt;
    }

    public void setFoodAmt(int foodAmt) {
        this.foodAmt = foodAmt;
    }

    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }
}
