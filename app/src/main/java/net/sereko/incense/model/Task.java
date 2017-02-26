package net.sereko.incense.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by steve on 2/15/17.
 */

public class Task {
    private String name;
    private String type;
    private Integer id;
    private Calendar created;

    public Task(){
        this.name = "Fed Cat";
        this.type = "Cat";
        this.created = Calendar.getInstance();
        this.completed = new ArrayList<>();
    }

    public ArrayList<CompletedTask> getCompleted() {
        return completed;
    }

    public void setCompleted(ArrayList<CompletedTask> completed) {
        this.completed = completed;
    }

    private ArrayList<CompletedTask> completed;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addCompletedTask(){
        CompletedTask t = new CompletedTask();

        completed.add(t);
    }

    @Override
    public String toString(){
        return name;
    }

    class CompletedTask {

        private Calendar calendar;
        private String foodType;
        private Double amount;


        public CompletedTask(){
            calendar = Calendar.getInstance();
        }

        public CompletedTask(Calendar cal){
            this.calendar = cal;
        }


        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Calendar getCalendar() {

            return calendar;
        }

        public void setCalendar(Calendar calendar) {
            this.calendar = calendar;
        }


    }
}
