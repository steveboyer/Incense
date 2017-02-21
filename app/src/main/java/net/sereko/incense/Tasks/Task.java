package net.sereko.incense.Tasks;

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

    private ArrayList<CompletedTask> completed;

    public Task(){
        this.name = "Fed Cat";
        this.type = "Cat";
        this.created = Calendar.getInstance();
        this.completed = new ArrayList<>();

        CompletedTask task = new CompletedTask();
        task.setFoodType("Wet");
        task.setAmount(1.0);

        CompletedTask task1 = new CompletedTask();
        task1.setFoodType("Dry");
        task1.setAmount(1.3);

        completed.add(task);
        completed.add(task1);
    }

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
