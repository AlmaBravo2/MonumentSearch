package com.iei.apiCarga.Models;

public class ParamsDTO {
    private boolean todos;
    private boolean cv;
    private boolean cyl;
    private boolean eus;

    public ParamsDTO() {
    }

    public ParamsDTO(boolean todos, boolean cv, boolean cyl, boolean eus) {
        this.todos = todos;
        this.cv = cv;
        this.cyl = cyl;
        this.eus = eus;
    }

    public boolean isTodos() {
        return todos;
    }

    public void setTodos(boolean todos) {
        this.todos = todos;
    }

    public boolean isCv() {
        return cv;
    }

    public void setCv(boolean cv) {
        this.cv = cv;
    }

    public boolean isCyl() {
        return cyl;
    }

    public void setCyl(boolean cyl) {
        this.cyl = cyl;
    }

    public boolean isEus() {
        return eus;
    }

    public void setEus(boolean eus) {
        this.eus = eus;
    }
}
